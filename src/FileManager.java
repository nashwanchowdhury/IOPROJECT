import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;
public class FileManager {
    public static void main(String[] args) {
//        Display the contents of a specified directory, including file names, file sizes, and last modified dates
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the path of the directory you would like to see: ");
        String response = scan.nextLine();

        try {
            Path input = Paths.get(response);
            if (!Files.isDirectory(input)) {
                System.out.println("Invalid directory path.");
                return;
            }
            boolean exit = false;
            while (!exit) {
                System.out.println("\nSelect an option");
                System.out.println("1. Display directory contents");
                System.out.println("2. Copy a file");
                System.out.println("3. Move a file");
                System.out.println("4. Delete a file");
                System.out.println("5. Create a directory");
                System.out.println("6. Delete a directory");
                System.out.println("7. Search for a specific file");
                System.out.println("8. Exit");

                int choice = scan.nextInt();
                scan.nextLine();

                switch (choice) {
                    case 1:
                        displayDirectory(input);
                        break;
                    case 2:
                        System.out.println("Enter the file name that is to be copied: ");
                        String sourceFile = scan.nextLine();
                        System.out.println("Enter the file name for the copied file: ");
                        String outputfile = scan.nextLine();

                        break;
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                    case 8:

                }


            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    //function used to display the contents of a specified directory
    private static void displayDirectory(Path input) {
        try { DirectoryStream<Path> stream = Files.newDirectoryStream(input);
            System.out.println("Contents: ");
            for (Path entry: stream) {
                BasicFileAttributes attributes = Files.readAttributes(entry, BasicFileAttributes.class);
                String type = attributes.isDirectory() ? "DIR" : "FILE";
                long size = attributes.size();
                LocalDateTime lastModified = LocalDateTime.ofInstant(attributes.lastModifiedTime().toInstant(), ZoneOffset.UTC);
                System.out.println("%-10s %-10d %-20s %s%n", type, size, lastModified.format(formatter), entry.getFileName() );
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

        //function used to copy file
    private static void copyFile(Path source, Path target) {
        try {
            Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("File copied successfully.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    //function used to delete file
    private static void deleteFile(Path file) {
        try {
            Files.delete(file);
            System.out.println("File deleted successfully.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    //function used to move file
    private static void moveFile(Path source, Path target) {
        try {
            Files.move(source, target, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("File moved successfully");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    //function used to create new directory
        private static void createDirectory (Path input) {
            try {
                Files.createDirectory(input);
                System.out.println("Directory created successfully");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    //function used to delete specified directory
        private static void deleteDirectory (Path input) {
            try {
                Files.delete(input);
                System.out.println("Directory deleted successfully");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    //function used to search directories for a keyword
            private static void searchDirectories (Path input, String keyword){
                try (DirectoryStream<Path> stream = Files.newDirectoryStream(input, keyword)) {
                    System.out.println("Your results: ");
                    for (Path entry : stream) {
                        System.out.println(entry.getFileName());
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
}











