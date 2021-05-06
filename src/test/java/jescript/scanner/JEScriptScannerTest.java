package jescript.scanner;

import jescript.reader.JEScriptReader;
import jescript.token.JEScriptToken;
import org.junit.Test;

public class JEScriptScannerTest {

    private static JEScriptReader jeScriptReader;
    private static JEScriptScanner jeScriptScanner;

    @Test
    public void testSetUP() {
        System.out.println("------------------------------");
        System.out.println("testSetUP start");
        System.out.println("------------------------------");
        String testScriptData = "";
        jeScriptReader = new JEScriptReader(testScriptData);
        jeScriptScanner = new JEScriptScanner(jeScriptReader);
        System.out.println("------------------------------");
        System.out.println("testSetUP end");
        System.out.println("------------------------------");
        System.out.println();
        System.out.println();
    }

    @Test
    public void testScannerWithRandString() {
        System.out.println("------------------------------");
        System.out.println("testScannerWithRandString start");
        System.out.println("------------------------------");
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
        System.out.println();
        System.out.println();
    }

    @Test
    public void testScannerWithRandToken() {
        System.out.println("------------------------------");
        System.out.println("testScannerWithRandToken start");
        System.out.println("------------------------------");
        String testScriptData = "++if(){}else{#!-+};+= -= * //adawdwadwa"
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
        System.out.println();
        System.out.println();
    }

}