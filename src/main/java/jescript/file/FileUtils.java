package jescript.file;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;

public class FileUtils {

    public void removeLine(File file, String deleteContent) throws IOException {
        List<String> out = Files.lines(file.toPath())
                .filter(line -> !line.contains(deleteContent))
                .collect(Collectors.toList());
        Files.write(file.toPath(), out, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    public boolean removeUsingNewFile(File file, String needDeleteString) throws IOException {
        boolean successful = false;
        File tempFile = new File("temp" + file.getName());
        BufferedReader reader = new BufferedReader(new FileReader(file));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
        String currentLine;
        while ((currentLine = reader.readLine()) != null) {
            String[] splitString = currentLine.trim().split(" ");
            if (splitString[0].equals(needDeleteString))
                continue;
            writer.write(currentLine + System.getProperty("line.separator"));
        }
        writer.close();
        reader.close();
        boolean isDeleteSuccessful = file.delete();
        if (isDeleteSuccessful)
            successful = tempFile.renameTo(file);
        return successful;
    }
}
