package jescript.reader;


import org.junit.BeforeClass;
import org.junit.Test;

public class JEScriptReaderTest {

    static JEScriptReader jeScriptReader;
    static boolean retracted = false;

    @BeforeClass
    public static void setUP() {
        String testScriptData = "test reader \n" +
                "this is test";
        jeScriptReader = new JEScriptReader(testScriptData);
    }

    @Test
    public void testReader() {
        while (true) {
            int readCharIntegerType = jeScriptReader.nextChar();
            if (readCharIntegerType == -1)
                break;

            char readChar = (char) readCharIntegerType;
            if (readChar == '!' && !retracted) {
                jeScriptReader.retract();
                retracted = true;
            }
            System.out.print(readChar);
        }
        System.out.println();
    }

}