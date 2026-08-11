package com.sefa.snapshot;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import com.sefa.snapshot.comparison.SnapshotComparator;
import com.sefa.snapshot.model.FileChange;

public class Main {
    public static void main(String[] args) {
        Map<String, String> oldFiles = new HashMap<>();
        Map<String, String> currentFiles = new HashMap<>();

        oldFiles.put("A.txt", "111");
        oldFiles.put("B.txt", "222");
        oldFiles.put("C.txt", "333");
        
        currentFiles.put("A.txt", "111");
        currentFiles.put("B.txt", "999");
        currentFiles.put("D.txt", "444");

        SnapshotComparator comparator = new SnapshotComparator();

        List<FileChange> changes = comparator.compare(oldFiles, currentFiles);

        System.out.println("------------------------------------------------------");
        for (FileChange change : changes) {
            System.out.println(change.getPath() + " -> "+ change.getChangeType());
        }

    }
}