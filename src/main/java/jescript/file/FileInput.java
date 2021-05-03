package jescript.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileInput{

    public String readFile(File readFile) throws IOException {
        StringBuilder readDataStringBuilder = new StringBuilder();
        String temp;
        BufferedReader bufferedReader = new BufferedReader(new FileReader(readFile));
        while ((temp = bufferedReader.readLine()) != null) {
            readDataStringBuilder.append(temp).append("\n\r");
        }
        bufferedReader.close();
        return readDataStringBuilder.toString();
    }

}
