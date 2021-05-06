package jescript.parse;

import jescript.scanner.JEScriptScanner;
import jescript.token.JEScriptToken;

public class JEScriptParser {

    private final JEScriptScanner jeScriptScanner;
    private final JEScriptToken currentToken;
    private final JEScriptToken headToken;
    private boolean isTokenCanUse = true;

    public JEScriptParser(JEScriptScanner jeScriptScanner) {
        this.jeScriptScanner = jeScriptScanner;
        this.currentToken = new JEScriptToken(JEScriptToken.Tokens.EOS);
        this.headToken = new JEScriptToken(JEScriptToken.Tokens.EOS);
    }

    public JEScriptToken.Tokens nextToken() {
        if (isTokenCanUse) {
            JEScriptToken.Tokens token = jeScriptScanner.nextToken();
            while (token.equals(JEScriptToken.Tokens.LINE_COMMENT_TOKEN) || token.equals(JEScriptToken.Tokens.BLOCK_COMMENT_TOKEN))
                token = jeScriptScanner.nextToken();
            currentToken.setType(token);
            currentToken.setText(jeScriptScanner.getJeScriptToken().getText());
            return token;
        } else {
            currentToken.setType(headToken.getType());
            currentToken.setText(headToken.getText());
            isTokenCanUse = true;
            return currentToken.getType();
        }
    }

    public JEScriptToken.Tokens headToken() {
        if (isTokenCanUse) {
            JEScriptToken.Tokens token = jeScriptScanner.nextToken();
            while (token.equals(JEScriptToken.Tokens.LINE_COMMENT_TOKEN) || token.equals(JEScriptToken.Tokens.BLOCK_COMMENT_TOKEN))
                token = jeScriptScanner.nextToken();
            headToken.setType(token);
            headToken.setText(jeScriptScanner.getJeScriptToken().getText());
            isTokenCanUse = false;
            return token;
        } else
            return headToken.getType();
    }
}
