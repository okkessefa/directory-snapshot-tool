package com.sefa.snapshot;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import com.sefa.snapshot.comparison.SnapshotComparator;
import com.sefa.snapshot.hashing.FileHasher;
import com.sefa.snapshot.model.FileChange;
import com.sefa.snapshot.model.FileMetadata;

public class Main {
    public static void main(String[] args) throws Exception {
        // Map<String, FileMetadata> oldFiles = new HashMap<>();
        // Map<String, FileMetadata> currentFiles = new HashMap<>();

        // oldFiles.put(
        //     "A.txt",
        //     new FileMetadata("A.txt", 121, "111")
        // );
        // oldFiles.put(
        //     "B.txt",
        //     new FileMetadata("B.txt", 239, "222")
        // );
        // oldFiles.put(
        //     "C.txt",
        //     new FileMetadata("C.txt", 100, "333")
        // );

        // currentFiles.put(
        //     "A.txt",
        //     new FileMetadata("A.txt", 121, "111")
        // );
        // currentFiles.put(
        //     "B.txt",
        //     new FileMetadata("B.txt", 245, "999")
        // );
        // currentFiles.put(
        //     "D.txt",
        //     new FileMetadata("D.txt", 167, "555")
        // );
        
        
       
        // SnapshotComparator comparator = new SnapshotComparator();

        // List<FileChange> changes = comparator.compare(oldFiles, currentFiles);

        // System.out.println("------------------------------------------------------");
        // for (FileChange change : changes) {
        //     System.out.println(change.getPath() + " -> "+ change.getChangeType());
        // }
        System.out.println("------------------------------------------------------");

        Path path = Path.of("sample.txt");
        FileHasher hasher = new FileHasher();
        String hash = hasher.hash(path);
        System.out.println("Hash -> " + hash);
    }
}
// 2cf24dba5fb0a30e26e83b2ac5b9e29e1b161e5c1fa7425e73043362938b9824
// 185f8db32271fe25f561a6fc938b2e264306ec304eda518007d1764826381969