package test;
import jescript.file.FileChooser;
import jescript.file.FileInput;
import jescript.reader.JEScriptReader;
import jescript.scanner.JEScriptScanner;
import jescript.token.JEScriptToken;
import java.io.File;
import java.io.IOException;

public class ReadAndProcessScript {
    public static void main(String[] argv){
        String scriptString = "";
        FileChooser fileChooser = new FileChooser();
        File selectFile = fileChooser.chooseFile();
        FileInput fileInput = new FileInput();
        try {
            scriptString = fileInput.readFile(selectFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        JEScriptReader jeScriptReader = new JEScriptReader(scriptString);
        JEScriptScanner jeScriptScanner = new JEScriptScanner(jeScriptReader);
        while (true) {
            JEScriptToken.Tokens token = jeScriptScanner.nextToken();
            if (token.equals(JEScriptToken.Tokens.EOS))
                break;
            System.out.println(token);
        }
        System.out.println(jeScriptScanner.getScriptStringBufferString());
    }
}
