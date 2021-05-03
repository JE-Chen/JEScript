package jescript.scanner;

import jescript.reader.JEScriptReader;
import jescript.token.JEScriptToken;
import org.junit.BeforeClass;
import org.junit.Test;

public class JEScriptScannerTest {

    static JEScriptReader jeScriptReader;
    static JEScriptScanner jeScriptScanner;

    @BeforeClass
    public static void setUP() {
        String testScriptData = "awd:dwlalm;zcmzm(poipo)ewaeewa{dawlda};wkdmaw%151";
        jeScriptReader = new JEScriptReader(testScriptData);
        jeScriptScanner = new JEScriptScanner(jeScriptReader);
    }

    @Test
    public void testScanner() {
        while (true) {
            JEScriptToken.Tokens token = jeScriptScanner.nextToken();
            if (token.equals(JEScriptToken.Tokens.EOS))
                break;
            System.out.println(token);
        }
    }

}