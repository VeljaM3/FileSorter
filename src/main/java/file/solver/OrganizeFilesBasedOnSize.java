package file.solver;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import javax.swing.JTextArea;

public class OrganizeFilesBasedOnSize {

    public static void createFolders(String folderPath) {
        File largeFilesFolder = new File(folderPath + File.separator + "LargeFiles");
        File mediumFilesFolder = new File(folderPath + File.separator + "MediumFiles");
        File smallFilesFolder = new File(folderPath + File.separator + "SmallFiles");

        if (!largeFilesFolder.exists()) {
            largeFilesFolder.mkdir();
        }
        if (!mediumFilesFolder.exists()) {
            mediumFilesFolder.mkdir();
        }
        if (!smallFilesFolder.exists()) {
            smallFilesFolder.mkdir();
        }
    }

    public static void printFileNameAndSize(String folderPath, JTextArea outputArea) {
        File folder = new File(folderPath);
        if (folder.isDirectory()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    double fileSizeInMB = (double) file.length() / (1024 * 1024);
                    outputArea.append("File : " + file.getName() + ", File Size is : " + String.format("%.2f", fileSizeInMB) + " MB\n");
                }
            }
        }
    }

    public static void moveFile(File file, String destinationFolder, JTextArea outputArea) {
        try {
            if (!file.exists()) {
                outputArea.append("File does not exist: " + file.getName() + "\n");
                return;
            }
            Path sourcePath = file.toPath();
            Path destinationPath = new File(destinationFolder + File.separator + file.getName()).toPath();
            Files.move(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
            outputArea.append("Moved file: " + file.getName() + " to " + destinationFolder + "\n");
        } catch (Exception e) {
            e.printStackTrace();
            outputArea.append("Error moving file: " + file.getName() + "\n");
        }
    }

    public static void segregateTheFilesAndMoveToFolder(String folderPath, JTextArea outputArea) {
        File folder = new File(folderPath);
        if (folder.isDirectory()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        long fileSizeInMB = file.length() / (1024 * 1024);
                        if (fileSizeInMB > 100) {
                            moveFile(file, folderPath + File.separator + "LargeFiles", outputArea);
                        } else if (fileSizeInMB > 10 && fileSizeInMB <= 100) {
                            moveFile(file, folderPath + File.separator + "MediumFiles", outputArea);
                        } else if (fileSizeInMB <= 10) {
                            moveFile(file, folderPath + File.separator + "SmallFiles", outputArea);
                        }
                    }
                }
            }
        }
    }
}
