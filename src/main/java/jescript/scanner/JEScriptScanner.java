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
        this.jeScriptToken = new JEScriptToken(JEScriptToken.Tokens.EOS);
    }

    private JEScriptToken.Tokens makeNextToken(JEScriptToken.Tokens token) {
        jeScriptToken.setType(token);
        return jeScriptToken.getType();
    }

    private JEScriptToken.Tokens makeNextToken(JEScriptToken.Tokens token, String text) {
        jeScriptToken.setType(token);
        jeScriptToken.setText(text);
        return jeScriptToken.getType();
    }

    public JEScriptToken.Tokens nextToken() {
        while (true) {
            StringBuilder stringBuilder = new StringBuilder();
            switch (scannerState) {
                case STATE_START:
                    String readNextChar = jeScriptReader.readNextChar();
                    if (readNextChar.matches("[a-zA-Z]")) {
                        this.scannerState = state.IDENTIFIER_STATE;
                        stringBuilder.append(readNextChar);
                    } else {
                        switch (readNextChar) {
                            case ":":
                                return makeNextToken(JEScriptToken.Tokens.COLON);
                            case ";":
                                return makeNextToken(JEScriptToken.Tokens.SEMICOLON);
                            case "(":
                                return makeNextToken(JEScriptToken.Tokens.LEFT_BRACES);
                            case ")":
                                return makeNextToken(JEScriptToken.Tokens.RIGHT_BRACES);
                            case "{":
                                return makeNextToken(JEScriptToken.Tokens.LEFT_PARENTHESES);
                            case "}":
                                return makeNextToken(JEScriptToken.Tokens.RIGHT_PARENTHESES);
                            case "+":

                            case "%":
                                return makeNextToken(JEScriptToken.Tokens.MOD_TOKEN);
                            case "-1":
                                return makeNextToken(JEScriptToken.Tokens.EOS);
                            case "\r":
                            case "\n":
                                currentScanLine++;
                            default:
                        }
                        break;
                    }
                case IDENTIFIER_STATE:
                    readNextChar = jeScriptReader.readNextChar();
                    if (readNextChar.matches("[a-zA-Z]")) {
                        stringBuilder.append(readNextChar);
                    } else {
                        jeScriptReader.retractBack(1);
                        scannerState = state.STATE_START;
                        String text = stringBuilder.toString();
                        switch (text) {
                            case "var":
                                return makeNextToken(JEScriptToken.Tokens.VAR_TOKEN);
                            case "int":
                            case "bool":
                                return makeNextToken(JEScriptToken.Tokens.INTEGER_TOKEN, text);
                            case "False":
                            case "false":
                            case "True":
                            case "true":
                                return makeNextToken(JEScriptToken.Tokens.BOOLEAN_TOKEN, text);
                            case "if":
                                return makeNextToken(JEScriptToken.Tokens.IF_TOKEN);
                            case "else":
                                return makeNextToken(JEScriptToken.Tokens.ELSE_TOKEN);
                            case "while":
                                return makeNextToken(JEScriptToken.Tokens.WHILE_TOKEN);
                            case "print":
                                return  makeNextToken(JEScriptToken.Tokens.PRINT_TOKEN);
                            case "-1":
                                return makeNextToken(JEScriptToken.Tokens.EOS);
                            default:
                                return makeNextToken(JEScriptToken.Tokens.IDENTIFIER_TOKEN, text);
                        }
                    }
                    break;
            }
        }
    }

    private enum state {
        STATE_START,
        IDENTIFIER_STATE,
    }

}
