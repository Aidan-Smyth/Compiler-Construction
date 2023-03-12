import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.*;

public class lex {

    public static ArrayList<Token> getToken(String program) {

        // array to hold tokens
        ArrayList<Token> TokenArray = new ArrayList<Token>();

        // reg ex patterns to match
        Pattern WHITESPACE = Pattern.compile("\\s");
        Pattern ALPHANUMERIC = Pattern.compile("[A-Za-z0-9]");
        Pattern NUMERIC = Pattern.compile("[0-9]");

        // token to be stored in array
        Token token = new Token();
        token.setSource("");
        token.setType("");

        String temp;

        for (int i = 0; i < program.length(); i++) {

            // determining what reg ex pattern the current char matches
            Matcher tokeword = ALPHANUMERIC.matcher(String.valueOf(program.charAt(i)));
            Matcher tokenumber = NUMERIC.matcher(String.valueOf(program.charAt(i)));
            Matcher tokewhite = WHITESPACE.matcher(String.valueOf(program.charAt(i)));

            // System.out.println(String.valueOf(program.charAt(i)));
            boolean tokewordFound = tokeword.find();
            // System.out.println(" tokeword " + tokewordFound);
            boolean tokenumberFound = tokenumber.find();
            // System.out.println(" tokenumber " + tokenumberFound);
            boolean tokewhiteFound = tokewhite.find();
            // System.out.println(" tokewhite " + tokewhiteFound);

            // if char = white space
            if (tokewhiteFound == true) {
                token = new Token();
                token.setSource("");
                token.setType("");

                // if char = letter or number
            } else if (tokewordFound == true) {
                if (token.getType() != "tokword" && token.getType() != "toknumber") {
                    token = new Token();
                    token.setSource("");
                    token.setType(tokenumberFound ? "toknumber" : "tokword");
                    TokenArray.add(token);
                }
                temp = token.getSource() + String.valueOf(program.charAt(i));
                token.setSource(temp);
            } else {
                // if char = operator
                if (token.getType() != "tokop" || token.getSource().length() > 0) {
                    token = new Token();
                    token.setSource(String.valueOf(program.charAt(i)));
                    token.setType("tokop");
                    TokenArray.add(token);
                }
            }

        }
        return TokenArray;
    }

    public static ArrayList<Token> getSymbols(ArrayList<Token> TokenArray) {
        SymbolTable symbolTable = new SymbolTable();

        for (int i = 0; i < TokenArray.size(); i++) {
            if (symbolTable.containsSymbol(TokenArray.get(i).getSource().toLowerCase())) {
                TokenArray.get(i)
                        .setSymbolTablePointer(symbolTable.getSymbol(TokenArray.get(i).getSource().toLowerCase()));
            }
            // } else {

            // if (TokenArray.get(i).getType() == "tokword") {
            // Attribute identifierAttr = new Attribute();
            // identifierAttr.setSymbolType("tokidentifer");
            // identifierAttr.setDataType("none");
            // identifierAttr.setTokenClass("tokidentifer");
            // symbolTable.addToAttributeTable("tokidentifier", identifierAttr);
            // Symbol identifierSymbol = new Symbol("identifier", "tokidentifier",
            // "variable", "integer", "0", "global",
            // identifierAttr);
            // symbolTable.addSymbol("integer", identifierSymbol);
            // }
            // }
        }

        return TokenArray;

    }

    public static void main(String[] args) {

        String program = "PROGRAM Sample (Input, Output); CONST x  = 15; BEGIN END;";
        ArrayList<Token> TokenArray = getToken(program);

        // for (Token token2 : TokenArray) {
        // System.out.print(token2.getSource() + " ");
        // System.out.println(token2.getType());
        // }

        SymbolTable symbolTable = new SymbolTable();
        // symbolTable.printSymbolTable();

        TokenArray = getSymbols(TokenArray);
        for (Token token2 : TokenArray) {
            System.out.printf("%-10s %-15s ", token2.getSource(), token2.getType());
            if (token2.getSymbolTablePointer() != null) {
                Symbol sym = new Symbol(token2.getSymbolTablePointer());
                System.out.println(sym.getTokenClass());
            } else {
                System.out.println("Symbol not defined");
            }
        }
    }

}
