// Jason.cpp -	A table-driven parser for the Jason programming
//		language.
//		Last revised 2/14/2005
//

#include <scan.h>

// const int	NumNonterms = 6, NumProductions = 7;

// The list of nonterminals in the Jason LL(1) grammar
enum nontermtype
{
	NTProgram,
	NTHeader,
	NTDeclSec,
	NTVarDecls,
	NTVarDecl,
	NTDataType,
	NTIdList,
	NTMoreIdList,
	NTProcDecls,
	NTProcDecl,
	NTProcHeader,
	NTParamList,
	NTParamDecls,
	NTMoreParamDecls,
	NTParamDecl,
	NTBlock,
	NTStatements,
	NTMoreStatements,
	NTStatement,
	NTElseClause,
	NTArglist,
	NTArgs,
	NTMoreArgs,
	NTCondition,
	NTRelOp,
	NTExpression,
	NTMoreExpression,
	NTTerm,
	NTMoreTerm,
	NTFactor,
	NTAddOp,
	NTMultOp
};

//	The nonterminals as character strings, for
//	when it is necessary or helpful to print them
//	for debugging or verification purposes
char *nontermstrings[] = {"Program", "Header", "DeclSec", "VarDecls",
						  "VarDecl", "DataType", "IdList", "MoreIdList", "ProcDecls",
						  "ProcDecl", "ProcHeader", "ParamList", "ParamDecls",
						  "MoreParamDecls", "ParamDecl", "Block", "Statements",
						  "MoreStatements", "Statement", "ElseClause", "Arglist",
						  "Args", "MoreArgs", "Condition", "RelOp", "Expression",
						  "MoreExpression", "Term", "MoreTerm", "Factor",
						  "AddOp", "MultOp"};

//	The production table for predictive parsing.
//	The nonzero entries are the production numbers for a
//	particular nonterminal matched with a lookahead token
//	Zero entries means that there is no such production
//	and it is a parsing error.
const int prodtable[][numtokens + 3] = {
	/*Program*/ {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0,
				 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				 0},
	/*Header*/ {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	/*DeclSect*/
	{3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 3, 0, 0, 3, 0, 0, 0,
	 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	 0},
	/*VarDecls*/
	{5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 5, 0, 0, 4, 0, 0, 0,
	 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	 0},

	/*VarDecl*/ {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 0, 0, 0, 0, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	/*DataType*/ {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 0, 0, 0, 0, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	/*IdList*/ {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9, 0},
	/*MoreIdList*/
	{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	 0, 0, 0, 0, 0, 0, 0, 11, 10, 0, 0, 0, 0, 0, 0, 0, 0,
	 0},
	/*ProcDecls*/
	{13, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 12, 0, 0, 0, 0, 0, 0,
	 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	 0},
	/*ProcDecl*/
	{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 14, 0, 0, 0, 0, 0, 0,
	 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	 0},
	/*ProcHeader*/
	{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 15, 0, 0, 0, 0, 0, 0,
	 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	 0},
	/*ParamList*/
	{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	 0, 0, 0, 0, 0, 0, 0, 17, 0, 0, 0, 0, 0, 16, 0, 0, 0,
	 0},
	/*ParamDecls*/
	{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 18, 0, 0, 0, 0, 18, 0, 0, 0,
	 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	 0},
	/*MoreParmDcls*/
	{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	 0, 0, 0, 0, 0, 0, 0, 19, 0, 0, 0, 0, 0, 0, 20, 0, 0,
	 0},
	/*ParamDecl*/
	{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 21, 0, 0, 0, 0, 21, 0, 0, 0,
	 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	 0},
	/*Block*/ {22, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	/*Statements*/
	{0, 23, 0, 0, 0, 0, 0, 0, 0, 23, 0, 0, 0, 0, 23, 0, 23, 0, 23,
	 23, 23, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	 0},
	/*MoreStatmnts*/
	{0, 0, 0, 0, 25, 25, 25, 25, 25, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	 0, 0, 0, 0, 0, 0, 0, 24, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	 0},
	/*Statement*/
	{0, 32, 0, 0, 33, 33, 33, 33, 33, 29, 0, 0, 0, 0, 26, 0, 27, 0, 31,
	 30, 28, 0, 0, 0, 0, 0, 33, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	 0},
	/*ElseClause*/
	{0, 0, 0, 0, 34, 0, 35, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	 0},
	/*ArgList*/ {0, 0, 0, 0, 37, 37, 37, 37, 37, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 37, 0, 0, 0, 0, 0, 36, 0, 0, 0, 0, 0},
	/*Args*/ {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 38, 0},
	/*MoreArgs.*/
	{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	 0, 0, 0, 0, 0, 0, 0, 0, 39, 0, 0, 0, 0, 0, 40, 0, 0,
	 0},
	/*Condition*/
	{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 41,
	 41},
	/*RelOp*/ {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 42, 0, 0, 0, 44, 45, 43, 0, 0, 0, 0, 0},
	/*Expression*/
	{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 46,
	 46},
	/*MoreExpr.*/
	{0, 0, 0, 48, 48, 48, 48, 48, 48, 0, 0, 0, 0, 0, 0, 0, 0, 48, 0,
	 0, 0, 0, 47, 47, 0, 48, 48, 0, 0, 48, 48, 48, 0, 0, 0, 0,
	 0},
	/*Term*/ {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 49, 49},
	/*MoreTerm*/
	{0, 0, 0, 51, 51, 51, 51, 51, 51, 0, 0, 0, 0, 0, 0, 0, 0, 51, 0,
	 0, 0, 50, 51, 51, 50, 51, 51, 0, 0, 51, 51, 51, 0, 0, 0, 0,
	 0},
	/*Factor*/ {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 52, 53},

	/*AddOp*/ {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 54, 55, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	/*MultOp*/ {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 56, 0, 0, 57, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};

//  Two type of items appear in a production:
//	a nonterminal or a terminal
enum termtagtype
{
	Nonterm,
	Term
};

struct prodarraytype
{
	enum termtagtype PrTermOrNonterm;
	int PrParseItem;
};

//	The productions of the Jason language complete with
//	their semantic actions.  Only the right-hand sides
//	of these productions appear.  The epsilon-productions
//	are not listed here.
const struct prodarraytype prodarray[] = {
	{Nonterm, NTHeader}, {Nonterm, NTDeclSec}, {Nonterm, NTBlock}, {Term, tokperiod}, {Term, tokprogram}, {Term, tokidentifier}, {Term, toksemicolon}, {Nonterm, NTVarDecls}, {Nonterm, NTProcDecls}, {Nonterm, NTVarDecl}, {Nonterm, NTVarDecls}, {Nonterm, NTDataType}, {Nonterm, NTIdList}, {Term, toksemicolon}, {Term, tokinteger}, {Term, tokreal}, {Term, tokidentifier}, {Nonterm, NTMoreIdList}, {Term, tokcomma}, {Term, tokidentifier}, {Nonterm, NTMoreIdList}, {Nonterm, NTProcDecl}, {Nonterm, NTProcDecls}, {Nonterm, NTProcHeader}, {Nonterm, NTDeclSec}, {Nonterm, NTBlock}, {Term, toksemicolon}, {Term, tokprocedure}, {Term, tokidentifier}, {Nonterm, NTParamList}, {Term, toksemicolon}, {Term, tokopenparen}, {Nonterm, NTParamDecls}, {Term, tokcloseparen}, {Nonterm, NTParamDecl}, {Nonterm, NTMoreParamDecls}, {Term, toksemicolon}, {Nonterm, NTParamDecl}, {Nonterm, NTMoreParamDecls}, {Nonterm, NTDataType}, {Term, tokidentifier}, {Term, tokbegin}, {Nonterm, NTStatements}, {Term, tokend}, {Nonterm, NTStatement}, {Nonterm, NTMoreStatements}, {Term, toksemicolon}, {Nonterm, NTStatement}, {Nonterm, NTMoreStatements}, {Term, tokread}, {Term, tokidentifier}, {Term, tokset}, {Term, tokidentifier}, {Term, tokequals}, {Nonterm, NTExpression}, {Term, tokwrite}, {Term, tokidentifier}, {Term, tokif}, {Nonterm, NTCondition}, {Term, tokthen}, {Nonterm, NTStatements}, {Nonterm, NTElseClause}, {Term, tokendif}, {Term, tokwhile}, {Nonterm, NTCondition}, {Term, tokdo}, {Nonterm, NTStatements}, {Term, tokendwhile}, {Term, tokuntil}, {Nonterm, NTCondition}, {Term, tokdo}, {Nonterm, NTStatements}, {Term, tokenduntil}, {Term, tokcall}, {Term, tokidentifier}, {Nonterm, NTArglist}, {Term, tokelse}, {Nonterm, NTStatements}, {Term, tokopenparen}, {Nonterm, NTArgs}, {Term, tokcloseparen}, {Term, tokidentifier}, {Nonterm, NTMoreArgs}, {Term, tokcomma}, {Term, tokidentifier}, {Nonterm, NTMoreArgs}, {Nonterm, NTExpression}, {Nonterm, NTRelOp}, {Nonterm, NTExpression}, {Term, tokequals}, {Term, toknotequal}, {Term, tokgreater}, {Term, tokless}, {Nonterm, NTTerm}, {Nonterm, NTMoreExpression}, {Nonterm, NTAddOp}, {Nonterm, NTTerm}, {Nonterm, NTMoreExpression}, {Nonterm, NTFactor}, {Nonterm, NTMoreTerm}, {Nonterm, NTMultOp}, {Nonterm, NTFactor}, {Nonterm, NTMoreTerm}, {Term, tokidentifier}, {Term, tokconstant}, {Term, tokplus}, {Term, tokminus}, {Term, tokstar}, {Term, tokslash}};

struct prodindexrec
{
	int prstart, prlength;
};

//	The index to the productions array, showing the
//	starting position of the production and the number of items
//	on its right-hand side. The first item is a dummy to get past the
//	zeroth element of the array and any other second-entries of 0
//	indicate an epsilon production.
const struct prodindexrec prodindex[] = {
	{0, 0}, {0, 4}, {4, 3}, {7, 2}, {9, 2}, {11, 0}, {11, 3}, {14, 1}, {15, 1}, {16, 2}, {18, 3}, {21, 0}, {21, 2}, {23, 0}, {23, 4}, {27, 4}, {31, 3}, {34, 0}, {34, 2}, {36, 3}, {39, 0}, {39, 2}, {41, 3}, {44, 2}, {46, 3}, {49, 0}, {49, 2}, {51, 4}, {55, 2}, {57, 6}, {63, 5}, {68, 5}, {73, 3}, {76, 0}, {76, 2}, {78, 0}, {78, 3}, {81, 0}, {81, 2}, {83, 3}, {86, 0}, {86, 3}, {89, 1}, {90, 1}, {91, 1}, {92, 1}, {93, 2}, {95, 3}, {98, 0}, {98, 2}, {100, 3}, {103, 0}, {103, 1}, {104, 1}, {105, 1}, {106, 1}, {107, 1}, {108, 1}

};

struct parsenoderec
{
	int level;
	enum termtagtype TermOrNonterm;
	int ParseItem, symtabentry;
};

class parser : scanner
{
public:
	parser(int argcount, char *args[]);
	parser(void);
	void parse(void);

private:
	struct parsenoderec *getparsenode(enum termtagtype termtag, int info);
	void processnonterm(struct parsenoderec *thisnode);
	void printnonterm(int i);
	inline int matchtoken(int thistoken, int thattoken)
	{
		return (thistoken == thattoken);
	}
	void error(char message[], int linenum);
	struct parsenoderec *parsetree, *thisnode;
	tokentype thistoken;
	int tabindex, currentlevel, numops;
	stack<struct parsenoderec *> parst;
};

symboltable st;

parser::parser(int argcount, char *args[]) : scanner(argcount, args)
{
	currentlevel = 1;
	thistoken = gettoken(tabindex);
}

parser::parser(void)
{
	currentlevel = 1;
	thistoken = gettoken(tabindex);
}

// Parse() -	This procedure checks the production table to
//		make certain that there is a production for
//		which this nonterminal can be expanded that
//		begins with this token.  If there isn't, this
//		is a fatal syntactic error; the compiler will
//		terminate execution.
//
//		Then it pushes its right sentential form on
//		the stack after linking them to their next
//		node.
void parser::parse(void)
{
	int i, lines = 0;

	parsetree = getparsenode(Nonterm, NTProgram);
	parst.push(parsetree);

	do
	{
		//  Look up the production in the production table.
		//  If not there, terminate with an error message.
		thisnode = parst.top();
		parst.pop();
		for (i = 0; i < thisnode->level; i++)
			cout << "   ";
		cout << thisnode->level << "    ";
		if (thisnode->TermOrNonterm == Term)
			st.printtoken(thisnode->ParseItem);
		else
			printnonterm(thisnode->ParseItem);
		cout << '\n';

		if (thisnode->TermOrNonterm == Term)
		{
			// If its's a terminal, match it to the
			// lookahead and get a new lookahead token

			if (matchtoken(thistoken, thisnode->ParseItem))
				thisnode->symtabentry = tabindex;
			else
				error("Parsing error - token does not required"
					  " terminal",
					  linenum);
			thistoken = gettoken(tabindex);
		}
		//	Expand the nonterminal and push the items on
		//  the right hand side in reverse order
		else
			processnonterm(thisnode);
		if (++lines % 10 == 0)
			getchar();
	} while (!parst.empty());
}

// GetParseNode() -	Get a parse node that will be pushed
//			on the stack and then fill it with the
//			appropriate information.
struct parsenoderec *parser::getparsenode(enum termtagtype termtag, int info)
{
	struct parsenoderec *p;

	p = new struct parsenoderec;
	p->level = currentlevel;
	p->TermOrNonterm = termtag;
	p->ParseItem = info;
	return (p);
}

//
// ProcessNonterm() -	The details of looking up the nonterminal in the
//			production table and pushing items on the stack
void parser::processnonterm(struct parsenoderec *thisnode)
{
	struct parsenoderec *p;
	int prodnum, i;

	//
	//	Look up the nonterminal in the production table.
	//	If the production number is 0, there is no production
	//	and it is a parse error.
	currentlevel = thisnode->level + 1;
	if ((prodnum = prodtable[thisnode->ParseItem][thistoken]) == 0)
	{
		cout << thistoken << '\t';
		st.printtoken(thistoken);
		error("Nonterminal - token mismatch", linenum);
	}

	//
	//	If there is a production, push the items
	//	on the right-hand onto the parse stack in
	//	reverse order.
	if (prodindex[prodnum].prlength != 0)
	{
		i = prodindex[prodnum].prstart		   // {0,4}
			+ prodindex[prodnum].prlength - 1; // i=3    {Term, tokperiod}
		p = getparsenode(prodarray[i].PrTermOrNonterm,
						 prodarray[i].PrParseItem);
		parst.push(p);

		for (i = prodindex[prodnum].prstart + prodindex[prodnum].prlength - 2; i >= prodindex[prodnum].prstart; --i)
		{
			p = getparsenode(prodarray[i].PrTermOrNonterm,
							 prodarray[i].PrParseItem);
			parst.push(p);
		}
	}
}

// PrintNonterm() -	Given its enumeration, print the corresponding
//			nonterminal.
void parser::printnonterm(int i)
{
	cout << nontermstrings[i];
}

// error() -	A catch-all routine for compiling errors.  It prints an
//		error message including line number and then terminates
//		execution.
void parser::error(char message[], int linenum)
{
	cout << message << " on line " << linenum << endl;
	exit(4);
}

//
// main() -	This is a temporary main function to get the parser
//		up and running.  So far, it opens the source code file,
// 		parses, pauses then dumps the symbol table.
int main(int argc, char *argv[])
{
	parser p(argc, argv);

	p.parse();
	getchar();
	st.dump();
	return (0);
}