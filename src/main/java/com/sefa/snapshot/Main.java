package com.sefa.snapshot;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import com.sefa.snapshot.comparison.SnapshotComparator;
import com.sefa.snapshot.model.FileChange;
import com.sefa.snapshot.model.FileMetadata;

public class Main {
    public static void main(String[] args) {
        Map<String, FileMetadata> oldFiles = new HashMap<>();
        Map<String, FileMetadata> currentFiles = new HashMap<>();

        oldFiles.put(
            "A.txt",
            new FileMetadata("A.txt", 121, "111")
        );
        oldFiles.put(
            "B.txt",
            new FileMetadata("B.txt", 239, "222")
        );
        oldFiles.put(
            "C.txt",
            new FileMetadata("C.txt", 100, "333")
        );

        currentFiles.put(
            "A.txt",
            new FileMetadata("A.txt", 121, "111")
        );
        currentFiles.put(
            "B.txt",
            new FileMetadata("B.txt", 245, "999")
        );
        currentFiles.put(
            "D.txt",
            new FileMetadata("D.txt", 167, "555")
        );
        
        
       
        SnapshotComparator comparator = new SnapshotComparator();

        List<FileChange> changes = comparator.compare(oldFiles, currentFiles);

        System.out.println("------------------------------------------------------");
        for (FileChange change : changes) {
            System.out.println(change.getPath() + " -> "+ change.getChangeType());
        }

    }
}