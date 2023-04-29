
    public class Parser extends Scanner {
        private int currentLevel;
        private ParseNodeRec parseTree, thisNode;
        private TokenType thisToken;
        private int tabIndex, numOps;
        private Stack<ParseNodeRec> parst;
    
        public Parser(int argCount, String[] args) {
            super(argCount, args);
            currentLevel = 1;
            thisToken = getToken(tabIndex);
        }
    
        public Parser() {
            currentLevel = 1;
            thisToken = getToken(tabIndex);
        }
    
        public void parse() {
            int i, lines = 0;
            parsetree = getParsenode(TermTagType.Nonterm, NTProgram);
            parst.push(parsetree);
    
            do {
                // Look up the production in the production table.
                // If not there, terminate with an error message.
                thisNode = parst.pop();
                for (i = 0; i < thisNode.getLevel(); i++)
                    System.out.print("   ");
                System.out.print(thisNode.getLevel() + "    ");
                if (thisNode.getTermOrNonterm() == TermTagType.Term)
                    st.printToken(thisNode.getParseItem());
                else
                    printNonterm(thisNode.getParseItem());
                System.out.println();
    
                if (thisNode.getTermOrNonterm() == TermTagType.Term) {
                    // If it's a terminal, match it to the
                    // lookahead and get a new lookahead token.
                    if (matchToken(thisToken, thisNode.getParseItem()))
                        thisNode.setSymtabEntry(tabIndex);
                    else
                        error("Parsing error - token does not required terminal", linenum);
                    thisToken = getToken(tabIndex);
                } else
                    processNonterm(thisNode);
                if (++lines % 10 == 0)
                    System.in.read();
            } while (!parst.empty());
        }
    
        private ParseNodeRec getParsenode(TermTagType termtag, int info) {
            ParseNodeRec p = new ParseNodeRec();
            p.setLevel(currentLevel);
            p.setTermOrNonterm(termtag);
            p.setParseItem(info);
            return p;
        }
    
        public void processNonTerm(ParseNode thisNode) {
            ParseNode p;
            int prodNum, i;
        
            // Look up the nonterminal in the production table.
            // If the production number is 0, there is no production
            // and it is a parse error.
            currentLevel = thisNode.getLevel() + 1;
            if ((prodNum = prodTable[thisNode.getParseItem()][thisToken]) == 0) {
                System.out.println(thisToken + "\t");
                st.printToken(thisToken);
                error("Nonterminal - token mismatch", lineNumber);
            }
        
            // If there is a production, push the items
            // on the right-hand onto the parse stack in
            // reverse order.
            if (prodIndex[prodNum].getPrlength() != 0) {
                i = prodIndex[prodNum].getPrstart() +
                        prodIndex[prodNum].getPrlength() - 1;
                p = getParsenode(prodArray[i].getPrTermOrNonterm(),
                        prodArray[i].getPrParseItem());
                parst.push(p);
        
                for (i = prodIndex[prodNum].getPrstart() +
                        prodIndex[prodNum].getPrlength() - 2;
                        i >= prodIndex[prodNum].getPrstart(); --i) {
                    p = getParsenode(prodArray[i].getPrTermOrNonterm(),
                            prodArray[i].getPrParseItem());
                    parst.push(p);
                }
            }
        }
        
        // printNonTerm() - Given its enumeration, print the corresponding
        // nonterminal.
        public void printNonTerm(int i) {
            System.out.print(nontermStrings[i]);
        }
        
        // error() - A catch-all routine for compiling errors. It prints an
        // error message including line number and then terminates
        // execution.
        public void error(String message, int lineNumber) {
            System.out.println(message + " on line " + lineNumber);
            System.exit(4);
        }
        
        //
        // main() - This is a temporary main function to get the parser
        // up and running. So far, it opens the source code file,
        // parses, pauses then dumps the symbol table.
        public static void main(String[] args) {
            Parser p = new Parser(args);
        
            p.parse();
            Scanner scanner = new Scanner(System.in);
            scanner.nextLine();
            st.dump();
        }