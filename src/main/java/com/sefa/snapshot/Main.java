package com.sefa.snapshot;

// import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
// import java.util.HashMap;
// import java.util.Iterator;
import java.util.Map;
import java.util.List;

import com.sefa.snapshot.comparison.SnapshotComparator;
// import com.sefa.snapshot.hashing.FileHasher;
import com.sefa.snapshot.model.FileChange;
import com.sefa.snapshot.model.FileMetadata;
import com.sefa.snapshot.model.Snapshot;
import com.sefa.snapshot.persistence.SnapshotRepository;
import com.sefa.snapshot.report.ConsoleReporter;
import com.sefa.snapshot.scanner.DirectoryScanner;

public class Main {
    public static void main(String[] args) throws Exception {
        if(args.length < 2){
            System.out.println("Usage: snapshot <directory> | compare <directory>");
            return;
        }
        String command = args[0];
        Path directory = Path.of(args[1]);
        
        if(command.equals("snapshot")){
            System.out.println("Snapshot command selected");
            DirectoryScanner scanner = new DirectoryScanner();
            Map<String, FileMetadata> files = scanner.scan(directory);
            Snapshot snapshot = new Snapshot(LocalDateTime.now(), files);
            SnapshotRepository snapshotRepository = new SnapshotRepository();
            snapshotRepository.save(snapshot, Path.of("snapshot.json"));
        }else if(command.equals("compare")){
            System.out.println("Compare command selected");
            SnapshotRepository snapshotRepository = new SnapshotRepository();
            Snapshot oldSnapshot = snapshotRepository.load(Path.of("snapshot.json"));
            Map<String, FileMetadata> oldFiles = oldSnapshot.getFiles();
            DirectoryScanner scanner = new DirectoryScanner();
            Map<String, FileMetadata> currentFiles = scanner.scan(directory);
            SnapshotComparator comparator = new SnapshotComparator();
            List<FileChange> changes = comparator.compare(oldFiles, currentFiles);
            ConsoleReporter reporter = new ConsoleReporter();
            reporter.print(changes);
        }else{ 
            System.out.println( "Unknown command: "+command);
        }
    }
}