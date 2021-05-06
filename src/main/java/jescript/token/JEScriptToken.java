package jescript.token;

public class JEScriptToken {

    private Tokens token = Tokens.EOS;
    private String text = "default";

    public JEScriptToken(Tokens token) {
        this.token = token;
    }

    public Tokens getType() {
        return token;
    }

    public void setType(Tokens token) {
        this.token = token;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public enum Tokens {

        // Variable

        VAR_TOKEN, // var

        // Data type

        INTEGER_TOKEN, // int

        // Symbol Operator

        COLON, // :
        SEMICOLON, // ;
        LEFT_PARENTHESES, //(
        RIGHT_PARENTHESES, // )
        LEFT_BRACES, //{
        RIGHT_BRACES, // }

        // Math Operator
        PLUS_TOKEN, // +
        PLUS_PLUS_TOKEN, // ++
        PLUS_ASSIGN_TOKEN, // +=
        MINUS_TOKEN, // -
        MINUS_MINUS_TOKEN, // --
        MINUS_ASSIGN_TOKEN, // -=
        MOD_TOKEN, // %
        MULT_TOKEN, // *
        DIV_TOKEN, // /
        GREATER_TOKEN,// >
        GREATER_EQUAL_TOKEN, // >=
        LESS_TOKEN, // <
        LESS_EQUAL_TOKEN, // <=


        // Assign Operator

        ASSIGN_TOKEN, // =

        // Compare Operator

        EQUAL_TOKEN, // ==

        // Logic Operator
        IF_TOKEN, // if
        ELSE_TOKEN, // else
        WHILE_TOKEN, // while
        BOOLEAN_TOKEN, //true, false, TRUE, FALSE
        AND_TOKEN, // &&
        OR_TOKEN, // ||
        NOT_TOKEN, // !

        // Comment Operator
        LINE_COMMENT_TOKEN, // //
        BLOCK_COMMENT_TOKEN, // /*

        // Bit Operator

        // Print
        PRINT_TOKEN, // print

        // Another
        IDENTIFIER_TOKEN, // undefined

        // END
        EOS
    }

}
