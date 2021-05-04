package jescript.scanner;

import jescript.reader.JEScriptReader;
import jescript.token.JEScriptToken;


public class JEScriptScanner {

    private final JEScriptReader jeScriptReader;
    private final JEScriptToken jeScriptToken;

    private int currentScanLine = 0;

    private state scannerState = state.STATE_START;

    private StringBuffer stringBuffer = new StringBuffer();

    public JEScriptScanner(JEScriptReader jeScriptReader) {
        this.jeScriptReader = jeScriptReader;
        jeScriptToken = new JEScriptToken(JEScriptToken.Tokens.EOS);
    }

    private JEScriptToken.Tokens makeNextToken(JEScriptToken.Tokens token) {
        jeScriptToken.setType(token);
        stringBuffer = new StringBuffer();
        return jeScriptToken.getType();
    }

    private JEScriptToken.Tokens makeNextToken(JEScriptToken.Tokens token, String text) {
        jeScriptToken.setType(token);
        jeScriptToken.setText(text);
        stringBuffer = new StringBuffer();
        return jeScriptToken.getType();
    }

    public JEScriptToken.Tokens nextToken() {
        while (true) {
            switch (scannerState) {
                case STATE_START:
                    String readNextChar = jeScriptReader.readNextChar();
                    if (readNextChar.matches("[a-zA-Z]")) {
                        this.scannerState = state.IDENTIFIER_STATE;
                        stringBuffer.append(readNextChar);
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
                                return makeNextToken(JEScriptToken.Tokens.PLUS_TOKEN);
                            case "-":
                                return makeNextToken(JEScriptToken.Tokens.MINUS_TOKEN);
                            case "%":
                                return makeNextToken(JEScriptToken.Tokens.MOD_TOKEN);
                            case "*":
                                return makeNextToken(JEScriptToken.Tokens.MULT_TOKEN);
                            case ">":
                                return makeNextToken(JEScriptToken.Tokens.GREATER_TOKEN);
                            case "<":
                                return makeNextToken(JEScriptToken.Tokens.LESS_TOKEN);
                            case "=":
                                return makeNextToken(JEScriptToken.Tokens.ASSIGN_TOKEN);
                            case "#":
                                return makeNextToken(JEScriptToken.Tokens.LINE_COMMENT_TOKEN);
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
                        stringBuffer.append(readNextChar);
                    } else {
                        if (readNextChar.equals("-1"))
                            return makeNextToken(JEScriptToken.Tokens.EOS);
                        jeScriptReader.retractBack(1);
                        scannerState = state.STATE_START;
                        String text = stringBuffer.toString();
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
                                return makeNextToken(JEScriptToken.Tokens.PRINT_TOKEN);
                            default:
                                return makeNextToken(JEScriptToken.Tokens.IDENTIFIER_TOKEN, text);
                        }
                    }
            }
        }
    }

    private enum state {
        STATE_START,
        IDENTIFIER_STATE,
    }

}
