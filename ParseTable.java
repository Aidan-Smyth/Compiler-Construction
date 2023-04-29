import java.util.ArrayList;
import java.util.Arrays;

public class ParseTable {

    /**
     * Write a description of class MakeBigTable here.
     *
     * @author (your name)
     * @version (a version number or a date)
     */

    // instance variables - replace the example below with your own
    String[][] productions = { { "Program", "Header", "Declarations", "Block", "." },
            { "Header", "program", "id", ";" },
            { "Declarations", "VariableDeclarationSection", "ProcedureDeclarations" },
            { "VariableDeclarationSection", "VAR", "VariableDeclarations" },
            { "VariableDeclarationSection", "<epsilon>" },
            { "VariableDeclarations", "VariableDeclaration", "VariableDeclarations’" },
            { "VariableDeclarations’", "VariableDeclaration", "VariableDeclarations’" },
            { "VariableDeclarations’", "<epsilon>" },
            { "VariableDeclaration", "IdentifierList", ":", "Type", ";" },
            { "IdentifierList", "id", "IdentifierList’" },
            { "IdentifierList’", ",", "id", "IdentifierList’" },
            { "IdentifierList’", "<epsilon>" },
            { "Type", "integer" },
            { "Type", "real" },
            { "ProcedureDeclarations", "ProcedureHeader", "Declarations", "Block", ";" },
            { "ProcedureHeader", "procedure", "id", ";" },
            { "Block", "begin", "Statements", "end" },
            { "Statements", "Statement", "Statements’" },
            { "Statements’", ";", "Statement", "Statements’" },
            { "Statements’", "<epsilon>" },
            { "Statement", "id", "Statement’" },
            { "Statement", "Block" },
            { "Statement", "if", "Expression", "then", "Statement", "ElseClause" },
            { "Statement", "while", "Expression", "do", "Statement" },
            { "Statement", "<epsilon>" },
            { "Statement’", ":=", "Expression" },
            { "Statement’", "()" },
            { "ElseClause", "else", "Statement" },
            { "ElseClause", "<epsilon>" },
            { "Expression", "SimpleExpression", "Expression’" },
            { "Expression’", "relop", "SimpleExpression" },
            { "Expression’", "<epsilon>" },
            { "SimpleExpression", "Term", "SimpleExpression’" },
            { "SimpleExpression’", "addop", "Term", "SimpleExpression’" },
            { "SimpleExpression’", "<epsilon>" },
            { "Term", "Factor", "Term’" },
            { "Term’", "mulop", "Factor", "Term’" },
            { "Term’", "<epsilon>" },
            { "Factor", "id" },
            { "Factor", "num" },
            { "Factor", "(", "Expression", ")" },
            { "Factor", "not", "Factor" } };

    int[][] bigTable = new int[25][24];
    ArrayList<String> tableRows_Terminals = new ArrayList<String>(
            Arrays.asList("program", "id", "VAR", "integer", "real", "procedure", ";", "begin", "end", "if", "then",
                    "while", "do", "else", "relop", "addop", "mulop", "num", "(", ")", "not", ",", ":", ":="));

    ArrayList<String> tableColumns_NonTerminals = new ArrayList<>(Arrays.asList(
            "Program", "Header", "Declarations", "VariableDeclarationSection",
            "VariableDeclarations", "VariableDeclarations'", "VariableDeclaration",
            "IdentifierList", "IdentifierList'", "Type", "ProcedureDeclarations",
            "ProcedureHeader", "Block", "Statements", "Statements'", "Statement",
            "Statement'", "ElseClause", "Expression", "Expression'", "SimpleExpression",
            "SimpleExpression'", "Term", "Term'", "Factor"));

    /**
     * Constructor for objects of class MakeBigTable
     * 
     * @return
     */
    public void MakeBigTable() {
        // Sets each value to 0 as the default so the rest is what we worry about
        for (int i = 0; i < bigTable.length; i++) {
            for (int j = 0; j < bigTable[i].length; j++) {
                bigTable[i][j] = 0;
            }
        }

        // Program
        bigTable[0][0] = 1;// program

        // Header
        bigTable[1][0] = 2; // program

        // Declarations
        bigTable[2][2] = 3; // VAR
        bigTable[2][5] = 3; // procedure

        // VariableDeclarationSection
        bigTable[3][2] = 4; // VAR
        bigTable[3][5] = 5; // procedure

        // VariableDeclarations
        bigTable[4][1] = 6; // id

        // VariableDeclarations'
        bigTable[5][1] = 7; // id
        bigTable[5][5] = 8;

        // VariableDeclaration
        bigTable[6][1] = 9; // id

        // IdentifierList
        bigTable[7][1] = 10; // id

        // IdentifierList'
        bigTable[8][21] = 11;
        bigTable[8][22] = 12;

        // Type
        bigTable[9][3] = 13;
        bigTable[9][4] = 14;

        // ProcedureDeclarations
        bigTable[10][5] = 15;

        // ProcedureHeader
        bigTable[11][5] = 16;

        // Block
        bigTable[12][7] = 17;

        // Statements
        bigTable[13][1] = 18;
        bigTable[13][7] = 18;
        bigTable[13][9] = 18;
        bigTable[13][11] = 18;
        bigTable[13][13] = 18;

        // Statements'
        bigTable[14][6] = 19;
        bigTable[14][8] = 20;

        // Statement
        bigTable[15][1] = 21;
        bigTable[15][7] = 22;
        bigTable[15][9] = 23;
        bigTable[15][11] = 24;
        bigTable[15][13] = 25;

        // Statement'
        bigTable[16][18] = 27;
        bigTable[16][23] = 26;

        // ElseClause
        bigTable[17][13] = 28;
        // bigTable[17][13] = 29;
        // THIS IS A PROBLEM UH OH

        // Expression
        bigTable[18][1] = 30;
        bigTable[18][7] = 30;
        bigTable[18][17] = 30;
        bigTable[18][18] = 30;
        bigTable[18][20] = 30;

        // Expression'
        bigTable[19][1] = 32;
        bigTable[19][7] = 32;
        bigTable[19][9] = 32;
        bigTable[19][10] = 32;
        bigTable[19][11] = 32;
        bigTable[19][12] = 32;
        bigTable[19][13] = 32;
        bigTable[19][14] = 31;

        // SimpleExpression
        bigTable[20][1] = 33;
        bigTable[20][15] = 34;
        bigTable[20][17] = 33;
        bigTable[20][18] = 33;
        bigTable[20][20] = 33;

        // SimpleExpression'
        bigTable[21][1] = 36;
        bigTable[21][7] = 36;
        bigTable[21][9] = 36;
        bigTable[21][10] = 36;
        bigTable[21][11] = 36;
        bigTable[21][12] = 36;
        bigTable[21][13] = 36;
        bigTable[21][15] = 35;

        // Term
        bigTable[22][1] = 37;
        bigTable[22][17] = 37;
        bigTable[22][18] = 37;
        bigTable[22][20] = 37;

        // Term'
        bigTable[23][1] = 39;
        bigTable[23][7] = 39;
        bigTable[23][9] = 39;
        bigTable[23][10] = 39;
        bigTable[23][11] = 39;
        bigTable[23][12] = 39;
        bigTable[23][13] = 39;
        bigTable[23][15] = 39;
        bigTable[23][16] = 38;

        // Factor
        bigTable[24][1] = 40;
        bigTable[24][17] = 41;
        bigTable[24][18] = 41;
        bigTable[24][20] = 41;

    }

    public String[] getProduction(String topSymbol, Token currentToken) {
        int nonTerminalIndex = tableColumns_NonTerminals.indexOf(topSymbol);
        int terminalIndex = tableRows_Terminals.indexOf(currentToken.getSource());
        int productionIndex = bigTable[nonTerminalIndex +1][terminalIndex+1];
        if (productionIndex == 0) {
            // There is no production rule for this combination of topSymbol and
            // currentToken
            return null;
        } else {
            return productions[productionIndex - 1];
        }
    }

}
