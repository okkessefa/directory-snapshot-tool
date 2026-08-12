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
import com.sefa.snapshot.scanner.DirectoryScanner;

public class Main {
    public static void main(String[] args) throws Exception {

        DirectoryScanner scanner = new DirectoryScanner();

        // Map<String, FileMetadata> oldResult = scanner.scan(Path.of("scanner-test"));
        
        // System.out.println("First scan finished");
        // System.out.println("Modify the files, then press the Enter. ");

        // System.in.read();
        
        // Map<String, FileMetadata> currentResult = scanner.scan(Path.of("scanner-test"));

        // SnapshotComparator comparator = new SnapshotComparator();

        // List<FileChange> changes = comparator.compare(oldResult, currentResult);

        // for (FileChange change : changes) {
        //     System.out.println(
        //         change.getPath() + " -> " + change.getChangeType()
        //     );
        // }

        Map<String, FileMetadata> files = scanner.scan(Path.of("scanner-test"));

        Snapshot snapshot = new Snapshot(LocalDateTime.now(), files);

        SnapshotRepository snapshotRepository = new SnapshotRepository();

        snapshotRepository.save(snapshot, Path.of("snapshot.json"));
        Snapshot loaded = snapshotRepository.load(Path.of("snapshot.json"));

        System.out.println(loaded.getCreatedAt());

        for(FileMetadata metadata : loaded.getFiles().values()){
            System.out.println(
                metadata.getPath() 
                + " | "
                + metadata.getSize() 
                + " | " 
                + metadata.getHash()
            );
        }

        // result.forEach((key, value) -> 
        //     System.out.println(value.getPath() + "\nSize: " + value.getSize() + "\nHash: " + value.getHash())
        // );
    }
}
// 2cf24dba5fb0a30e26e83b2ac5b9e29e1b161e5c1fa7425e73043362938b9824
// 185f8db32271fe25f561a6fc938b2e264306ec304eda518007d1764826381969