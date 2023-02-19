import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;

public class lex {

    public static ArrayList<Token> getToken(String program) {
        ArrayList<Token> TokenArray = new ArrayList<Token>();
        Pattern WHITESPACE = Pattern.compile("\\s");
        Pattern ALPHANUMERIC = Pattern.compile("[A-Za-z0-9]");
        Pattern NUMERIC = Pattern.compile("[0-9]");
        Token token = new Token();
        token.setSource("");
        token.setType("");
        String temp;

        for (int i = 0; i < program.length(); i++) {

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

            if (tokewhiteFound == true) {
                token = new Token();
                token.setSource("");
                token.setType("");
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

    public static void main(String[] args) {

        String program = "PROGRAM Sample (Input, Output); CONST x  = 15; BEGIN END;";
        ArrayList<Token> TokenArray = getToken(program);

        for (Token token2 : TokenArray) {
            System.out.print(token2.getSource() + "  ");
            System.out.println(token2.getType());
        }

    }

}

class Token {
    private String source;
    private String type;

    public void setSource(String s) {
        source = s;
    }

    public void setType(String t) {
        type = t;
    }

    public String getSource() {
        return source;
    }

    public String getType() {
        return type;
    }
}




