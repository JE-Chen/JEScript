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
        String testScriptData = "";
        jeScriptReader = new JEScriptReader(testScriptData);
        jeScriptScanner = new JEScriptScanner(jeScriptReader);
    }

    @Test
    public void testScannerWithRandString() {
        System.out.println("testScannerWithRandString start");
        String testScriptData = "awd:dwlalm;zcmzm(poipo)ewaeewa{dawlda};wkdmaw%151;";
        jeScriptReader = new JEScriptReader(testScriptData);
        jeScriptScanner = new JEScriptScanner(jeScriptReader);
        while (true) {
            JEScriptToken.Tokens token = jeScriptScanner.nextToken();
            if (token.equals(JEScriptToken.Tokens.EOS))
                break;
            System.out.println(token);
        }
        System.out.println("------------------------------");
        System.out.println("testScannerWithRandString end");
        System.out.println("------------------------------");
    }

    @Test
    public void testScannerWithRandToken() {
        System.out.println("testScannerWithRandToken start");
        String testScriptData = "if(){}else{#!-+};+= -= * //adawdwadwa"
                + System.lineSeparator() + "//dwadwawda"
                + System.lineSeparator() + "dwadadwa"
                + System.lineSeparator() + "/ * +";
        jeScriptReader = new JEScriptReader(testScriptData);
        jeScriptScanner = new JEScriptScanner(jeScriptReader);
        while (true) {
            JEScriptToken.Tokens token = jeScriptScanner.nextToken();
            if (token.equals(JEScriptToken.Tokens.EOS))
                break;
            System.out.println(token);
        }
        System.out.println("------------------------------");
        System.out.println("testScannerWithRandToken end");
        System.out.println("------------------------------");
    }

}