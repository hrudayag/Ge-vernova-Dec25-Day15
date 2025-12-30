import java.io.IOException;
import java.nio.file.*;
public class DirectoryWatchService {
    public static void main(String[] args) throws IOException, InterruptedException {
        WatchService watchService = FileSystems.getDefault().newWatchService();
        Path path = Paths.get("payroll-files");
        path.register(watchService,
                StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_DELETE);
        System.out.println("Watching directory...");

        while (true) {
            WatchKey key = watchService.take();

            for (WatchEvent<?> event : key.pollEvents()) {
                System.out.println("Event: " + event.kind() + " File: " + event.context());
            }
            key.reset();
        }
    }
}