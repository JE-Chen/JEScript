package jescript.scanner;

import jescript.reader.JEScriptReader;
import jescript.token.JEScriptToken;

public class JEScriptScanner {

    private JEScriptReader jeScriptReader;
    private JEScriptToken jeScriptToken;
    private int currentScanLine = 0;
    private state scannerState = state.STATE_START;

    public JEScriptScanner(JEScriptReader jeScriptReader) {
        this.jeScriptReader = jeScriptReader;
        this.jeScriptToken = new JEScriptToken(JEScriptToken.Tokens.EOS, "default");
    }

    private JEScriptToken.Tokens makeNextToken(JEScriptToken.Tokens token, String text) {
        jeScriptToken.setType(token);
        jeScriptToken.setText(text);
        return jeScriptToken.getType();
    }

    public JEScriptToken.Tokens nextToken() {
        while (true) {
            switch (scannerState) {
                case STATE_START:
                    String readNextChar = jeScriptReader.readNextChar();
                    switch (readNextChar) {
                        case ":":
                            return this.makeNextToken(JEScriptToken.Tokens.COLON, "");
                        case ";":
                            return this.makeNextToken(JEScriptToken.Tokens.SEMICOLON, "");
                        case "(":
                            return this.makeNextToken(JEScriptToken.Tokens.LEFT_BRACES, "");
                        case ")":
                            return this.makeNextToken(JEScriptToken.Tokens.RIGHT_BRACES, "");
                        case "{":
                            return this.makeNextToken(JEScriptToken.Tokens.LEFT_PARENTHESES, "");
                        case "}":
                            return this.makeNextToken(JEScriptToken.Tokens.RIGHT_PARENTHESES, "");
                        case "%":
                            return this.makeNextToken(JEScriptToken.Tokens.MOD, "");
                        case "-1":
                            return this.makeNextToken(JEScriptToken.Tokens.EOS, "");
                        case "\r":
                        case "\n":
                            this.currentScanLine++;
                        default:
                    }
            }
        }
    }

    private enum state {
        STATE_START
    }

}
