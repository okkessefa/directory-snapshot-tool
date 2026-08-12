package com.sefa.snapshot.application;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import com.sefa.snapshot.comparison.SnapshotComparator;
import com.sefa.snapshot.model.FileChange;
import com.sefa.snapshot.model.FileMetadata;
import com.sefa.snapshot.model.Snapshot;
import com.sefa.snapshot.persistence.SnapshotRepository;
import com.sefa.snapshot.report.ConsoleReporter;
import com.sefa.snapshot.scanner.DirectoryScanner;

public class SnapshotApplication {
    private static final Path SNAPSHOT_PATH = Path.of("snapshot.json");
    public void run(String[] args) throws Exception {
        if(args.length < 2){
            System.out.println("Usage: snapshot <directory> | compare <directory>");
            return;
        }
        String command = args[0];
        Path directory = Path.of(args[1]);
        if(command.equals("snapshot")){
            System.out.println("Snapshot command selected");
            this.createSnapshot(directory);
        }else if(command.equals("compare")){
            System.out.println("Compare command selected");
            this.compareSnapshot(directory);
        }else{ 
            System.out.println( "Unknown command: "+command);
        }
    }
    private void createSnapshot(Path directory)  throws Exception{
        DirectoryScanner scanner = new DirectoryScanner();
        Map<String, FileMetadata> files = scanner.scan(directory);
        Snapshot snapshot = new Snapshot(LocalDateTime.now(), files);
        SnapshotRepository snapshotRepository = new SnapshotRepository();
        snapshotRepository.save(snapshot, SNAPSHOT_PATH);
    }
    private void compareSnapshot(Path directory) throws Exception {
        SnapshotRepository snapshotRepository = new SnapshotRepository();
        Snapshot oldSnapshot = snapshotRepository.load(SNAPSHOT_PATH);
        Map<String, FileMetadata> oldFiles = oldSnapshot.getFiles();
        DirectoryScanner scanner = new DirectoryScanner();
        Map<String, FileMetadata> currentFiles = scanner.scan(directory);
        SnapshotComparator comparator = new SnapshotComparator();
        List<FileChange> changes = comparator.compare(oldFiles, currentFiles);
        ConsoleReporter reporter = new ConsoleReporter();
        reporter.print(changes);
    }
}