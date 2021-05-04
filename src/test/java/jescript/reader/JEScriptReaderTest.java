package jescript.reader;


import org.junit.BeforeClass;
import org.junit.Test;

public class JEScriptReaderTest {

    static JEScriptReader jeScriptReader;
    static boolean retracted = false;

    @BeforeClass
    public static void setUP() {
        String testScriptData = "test reader \n" +
                "this is test!";
        jeScriptReader = new JEScriptReader(testScriptData);
    }

    @Test
    public void testReader() {
        while (true) {
            String readNextChar = jeScriptReader.readNextChar();
            if (readNextChar.equals("-1"))
                break;
            System.out.print(readNextChar);
        }
        System.out.println();
    }

    @Test
    public void testChar(){
        System.out.println("e".matches("[a-zA-Z]"));
    }

}