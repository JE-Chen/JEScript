package jescript.scanner;

import jescript.reader.JEScriptReader;
import jescript.token.JEScriptToken;


public class JEScriptScanner {

    private final JEScriptReader jeScriptReader;
    private final JEScriptToken jeScriptToken;

    private int currentScanLine = 0;

    private state scannerState = state.START_STATE;

    private StringBuffer stringBuffer = new StringBuffer();

    private StringBuffer scriptStringBuffer = new StringBuffer();

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

    public JEScriptToken getJeScriptToken() {
        return jeScriptToken;
    }

    public JEScriptToken.Tokens nextToken() {
        while (true) {
            switch (scannerState) {
                case START_STATE:
                    String readNextChar = jeScriptReader.readNextChar();
                    String nextChar = "";
                    if (readNextChar.matches("[a-zA-Z]")) {
                        this.scannerState = state.IDENTIFIER_STATE;
                        stringBuffer.append(readNextChar);
                        scriptStringBuffer.append(readNextChar);
                    } else {
                        if (readNextChar.equals("\n") || readNextChar.equals("\r")) {
                            currentScanLine++;
                        }
                        if (!readNextChar.equals("/") && !readNextChar.equals("-1"))
                            scriptStringBuffer.append(readNextChar);
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
                                nextChar = jeScriptReader.readNextChar();
                                if (nextChar.equals("+")) {
                                    scriptStringBuffer.append(nextChar);
                                    return makeNextToken(JEScriptToken.Tokens.PLUS_PLUS_TOKEN);
                                }
                                else if (nextChar.equals("=")) {
                                    scriptStringBuffer.append(nextChar);
                                    return makeNextToken(JEScriptToken.Tokens.PLUS_ASSIGN_TOKEN);
                                }
                                jeScriptReader.retractBack(1);
                                return makeNextToken(JEScriptToken.Tokens.PLUS_TOKEN);
                            case "-":
                                nextChar = jeScriptReader.readNextChar();
                                if (nextChar.equals("-")) {
                                    scriptStringBuffer.append(nextChar);
                                    return makeNextToken(JEScriptToken.Tokens.MINUS_MINUS_TOKEN);
                                }
                                else if (nextChar.equals("=")) {
                                    scriptStringBuffer.append(nextChar);
                                    return makeNextToken(JEScriptToken.Tokens.MINUS_ASSIGN_TOKEN);
                                }
                                jeScriptReader.retractBack(1);
                                return makeNextToken(JEScriptToken.Tokens.MINUS_TOKEN);
                            case "%":
                                return makeNextToken(JEScriptToken.Tokens.MOD_TOKEN);
                            case "*":
                                return makeNextToken(JEScriptToken.Tokens.MULT_TOKEN);
                            case "/":
                                scannerState = state.SLASH_STATE;
                                break;
                            case ">":
                                nextChar = jeScriptReader.readNextChar();
                                if (nextChar.equals("=")) {
                                    scriptStringBuffer.append(nextChar);
                                    return makeNextToken(JEScriptToken.Tokens.GREATER_EQUAL_TOKEN);
                                }
                                jeScriptReader.retractBack(1);
                                return makeNextToken(JEScriptToken.Tokens.GREATER_TOKEN);
                            case "<":
                                nextChar = jeScriptReader.readNextChar();
                                if (nextChar.equals("=")) {
                                    scriptStringBuffer.append(nextChar);
                                    return makeNextToken(JEScriptToken.Tokens.LESS_EQUAL_TOKEN);
                                }
                                jeScriptReader.retractBack(1);
                                return makeNextToken(JEScriptToken.Tokens.LESS_TOKEN);
                            case "=":
                                nextChar = jeScriptReader.readNextChar();
                                if (nextChar.equals("=")) {
                                    scriptStringBuffer.append(nextChar);
                                    return makeNextToken(JEScriptToken.Tokens.EQUAL_TOKEN);
                                }
                                jeScriptReader.retractBack(1);
                                return makeNextToken(JEScriptToken.Tokens.ASSIGN_TOKEN);
                            case "-1":
                                return makeNextToken(JEScriptToken.Tokens.EOS);
                            default:
                        }
                        break;
                    }
                    break;
                case IDENTIFIER_STATE:
                    readNextChar = jeScriptReader.readNextChar();
                    if (readNextChar.matches("[a-zA-Z]")) {
                        stringBuffer.append(readNextChar);
                        scriptStringBuffer.append(readNextChar);
                    } else {
                        if (readNextChar.equals("-1"))
                            return makeNextToken(JEScriptToken.Tokens.EOS);
                        jeScriptReader.retractBack(1);
                        scannerState = state.START_STATE;
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
                    break;
                case SLASH_STATE:
                    readNextChar = jeScriptReader.readNextChar();
                    if (readNextChar.equals("/")) {
                        stringBuffer = new StringBuffer();
                        readNextChar = jeScriptReader.readNextChar();
                        if (!readNextChar.equals("\n") && !readNextChar.equals("\r") && !readNextChar.equals("-1")) {
                            while (!readNextChar.equals("\n") && !readNextChar.equals("\r") && !readNextChar.equals("-1")) {
                                stringBuffer.append(readNextChar);
                                readNextChar = jeScriptReader.readNextChar();
                            }
                            jeScriptReader.retractBack(1);
                        }
                        String text = stringBuffer.toString();
                        stringBuffer = new StringBuffer();
                        scannerState = state.START_STATE;
                        return makeNextToken(JEScriptToken.Tokens.LINE_COMMENT_TOKEN, text);
                    } else if (readNextChar.equals("*")) {
                        stringBuffer = new StringBuffer();
                        boolean commentEnd = false;
                        while (!commentEnd) {
                            readNextChar = jeScriptReader.readNextChar();
                            if (!readNextChar.equals("-1")) {
                                if (readNextChar.equals("\n") || readNextChar.equals("\r"))
                                    currentScanLine++;
                                if (readNextChar.equals("*")) {
                                    readNextChar = jeScriptReader.readNextChar();
                                    if (readNextChar.equals("/"))
                                        commentEnd = true;
                                    else
                                        stringBuffer.append("*").append(readNextChar);
                                } else {
                                    stringBuffer.append(readNextChar);
                                }
                            } else
                                commentEnd = true;
                        }
                        scannerState = state.START_STATE;
                        return makeNextToken(JEScriptToken.Tokens.BLOCK_COMMENT_TOKEN, stringBuffer.toString());
                    } else {
                        scriptStringBuffer.append("/");
                        scannerState = state.START_STATE;
                        jeScriptReader.retractBack(1);
                        return makeNextToken(JEScriptToken.Tokens.DIV_TOKEN);
                    }
            }
        }
    }

    private enum state {
        START_STATE,
        IDENTIFIER_STATE,
        SLASH_STATE
    }

    public String getScriptStringBufferString() {
        return scriptStringBuffer.toString();
    }
}
