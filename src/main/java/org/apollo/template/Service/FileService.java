package org.apollo.template.Service;

import java.io.*;

public class FileService {

    private File file;
    private PrintWriter printWriter;
    private FileOutputStream fileOutputStream;

    public FileService(String filePath) {
        // creates File obj. with file path as ref.
        file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.printf("Error creating file at [%s]", filePath);
            }
        }
    }

    public void writeToFile(String text) {
        try {
            printWriter = new PrintWriter(new FileWriter(file));
            printWriter.println(text);
            printWriter.flush();
        } catch (IOException e) {
            System.out.println("Error writing to the file");
        } finally {
            if (printWriter != null) {
                printWriter.close();
            }
        }
    }

    public void writeToFile(String text, boolean append) {
        try {
            fileOutputStream = new FileOutputStream(file, append);
            printWriter = new PrintWriter(fileOutputStream);
            printWriter.println(text);
            printWriter.flush();
        } catch (IOException e) {
            System.out.println("Error writing to the file");
        } finally {
            try {
                if (printWriter != null) {
                    printWriter.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                System.out.println("Error closing PrintWriter or FileOutputStream");
            }
        }
    }
}
