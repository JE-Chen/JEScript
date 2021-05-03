package jescript.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileOutput {

    public void writeFile(File writeFile, String writeData) throws IOException {
        assert writeFile.exists() || writeFile.createNewFile();
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(writeFile));
        bufferedWriter.write(writeData);
        bufferedWriter.close();
    }

}
