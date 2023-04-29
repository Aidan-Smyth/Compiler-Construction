import java.text.ParseException;
import java.util.ArrayList;
import java.util.Stack;

public class Parse {

    private int tokenIndex = 0;
    ArrayList<Token> tokens;
    private Token currentToken;
    private ParseTable parseTable = new ParseTable();

    public Stack<String> parse(ArrayList<Token> tokens) {
        this.tokens = tokens;
        parseTable.MakeBigTable();
        // Initialize the stack with the start symbol and the end of input symbol
        Stack<String> stack = new Stack<>();
        stack.push("$"); // end of input symbol
        stack.push("S"); // start symbol

        // Get the first token from the input
        Token currentToken = parseNextToken();

        // Loop until the stack is empty
        while (!stack.empty()) {
            // Get the top symbol from the stack
            String topSymbol = stack.pop();

            // If the top symbol is a non-terminal
            if (isNonTerminal(topSymbol)) {
                // Lookup the production in the parse table
                String[] production = parseTable.getProduction(topSymbol, currentToken);

                // If there is no production for the current input token, error
                if (production == null) {

                } else {

                    // Push the RHS of the production onto the stack in reverse order
                    String[] symbols = production;

                    for (int i = symbols.length - 1; i >= 0; i--) {
                        stack.push(symbols[i]);
                    }
                }
            }
            // If the top symbol is a terminal
            else if (!isNonTerminal(topSymbol)) {
                // If the top symbol matches the current input token, consume it and get the
                // next token
                if (topSymbol.equals(currentToken.getSource())) {
                    currentToken = parseNextToken();
                }
                // Otherwise, error
                else {
                    // throw new RuntimeException("Syntax error");
                }
            }
            // If the top symbol is the end of input symbol
            else if (topSymbol.equals("$")) {
                // If we have consumed all input tokens and the stack is empty, we have
                // successfully parsed the input
                if (currentToken.getSource().equals("$")) {
                    return stack;
                }
                // Otherwise, error
                else {
                    throw new RuntimeException("Syntax error");
                }
            }
        }
        return stack;
    }

    public Token parseNextToken() {
        // Assume the input stream is a List<Token> called "tokens"
        if (tokenIndex < tokens.size()) {
            return tokens.get(tokenIndex++);
        } else {
            // Return an "end of input" token when the input stream is empty
            Token tok = new Token();
            tok.setType("");
            return tok;
        }
    }

    public static boolean isNonTerminal(String topSymbol) {
        // Assume non-terminals start with a capital letter
        return Character.isUpperCase(topSymbol.charAt(0));
    }

}
