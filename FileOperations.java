import java.io.IOException;
import java.nio.file.*;

public class FileOperations {
    public static void main(String[] args) throws IOException {
        Path dirPath = Paths.get("payroll-files");
        Path filePath = Paths.get("payroll-files/payroll.txt");
        // Check file exists
        System.out.println("File exists: " + Files.exists(filePath));
        // Create directory
        if (!Files.exists(dirPath)) {
            Files.createDirectory(dirPath);
            System.out.println("Directory created");
        }
        // Create empty file
        if (!Files.exists(filePath)) {
            Files.createFile(filePath);
            System.out.println("File created");
        }
        // List files
        Files.list(dirPath).forEach(System.out::println);
        // Delete file
        Files.deleteIfExists(filePath);
        System.out.println("File exists after delete: " + Files.exists(filePath));
    }
}
