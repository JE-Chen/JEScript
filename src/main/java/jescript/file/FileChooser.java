package jescript.file;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class FileChooser{

    public static void main(String[] argv) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.chooseDir();
    }

    public File chooseFile() {
        File selectedFile = null;
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Choose data file", "txt", "json", "je"));
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();
            System.out.println("選擇檔案" + selectedFile.getName());
        }
        return selectedFile;
    }

    public File chooseDir() {
        File dirFile;
        JFileChooser dirChooser = new JFileChooser();
        dirChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnValue = dirChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION)
            dirFile = dirChooser.getSelectedFile();
        else
            dirFile = dirChooser.getCurrentDirectory();
        System.out.println("選擇資料夾" + dirFile.getName());
        return dirFile;
    }

}
