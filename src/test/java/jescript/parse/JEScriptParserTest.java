package jescript.parse;

import jescript.reader.JEScriptReader;
import jescript.scanner.JEScriptScanner;
import jescript.token.JEScriptToken;
import org.junit.Test;

public class JEScriptParserTest {

    private static JEScriptReader jeScriptReader;
    private static JEScriptScanner jeScriptScanner;
    private static JEScriptParser jeScriptParser;

    @Test
    public void testSetUP() {
        String testScriptData = "";
        jeScriptReader = new JEScriptReader(testScriptData);
        jeScriptScanner = new JEScriptScanner(jeScriptReader);
        jeScriptParser = new JEScriptParser(jeScriptScanner);
    }

    @Test
    public void testParser() {
        String testScriptData = "++ --" + System.lineSeparator()
                + "// wdadwa ++ --" + System.lineSeparator()
                + "--" + System.lineSeparator()
                + "/* dadw ++ -- */" + System.lineSeparator()
                + " ++ --";
        jeScriptReader = new JEScriptReader(testScriptData);
        jeScriptScanner = new JEScriptScanner(jeScriptReader);
        jeScriptParser = new JEScriptParser(jeScriptScanner);
        while (true) {
            if (jeScriptParser.headToken().equals(JEScriptToken.Tokens.PLUS_PLUS_TOKEN))
                System.out.println("PLUS PLUS TOKEN");
            if (jeScriptParser.headToken().equals(JEScriptToken.Tokens.MINUS_MINUS_TOKEN))
                System.out.println("MINUS MINUS TOKEN");
            JEScriptToken.Tokens token = jeScriptParser.nextToken();
            if (token.equals(JEScriptToken.Tokens.EOS))
                break;
        }
    }

}