package jescript.token;

public class JEScriptToken {

    private Tokens token;
    private String text;

    public JEScriptToken(Tokens token, String text) {
        this.token = token;
        this.text = text;
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
        EOS,
        COLON, //:
        SEMICOLON, //;
        LEFT_PARENTHESES, RIGHT_PARENTHESES, //()
        LEFT_BRACES, RIGHT_BRACES, //{}
        MOD //%
    }

}
