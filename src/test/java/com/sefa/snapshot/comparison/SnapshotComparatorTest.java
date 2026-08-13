package com.sefa.snapshot.comparison;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.sefa.snapshot.model.ChangeType;
import com.sefa.snapshot.model.FileChange;
import com.sefa.snapshot.model.FileMetadata;

public class SnapshotComparatorTest {
    @Test
    void shouldMarkSameFileAsUnchanged() throws Exception {
        Map<String, FileMetadata> oldFiles = new HashMap<>();
        Map<String, FileMetadata> currentFiles = new HashMap<>();

        oldFiles.put("A.txt", 
            new FileMetadata("A.txt", 120, "111")
        );

        currentFiles.put("A.txt", 
            new FileMetadata("A.txt", 120, "111")
        );

        SnapshotComparator comparator = new SnapshotComparator();
        List<FileChange> changes = comparator.compare(oldFiles, currentFiles);
        assertEquals(1, changes.size());
        assertEquals(ChangeType.UNCHANGED, changes.get(0).getChangeType());
    }
    @Test 
    void shouldMarkNewFileAsAdded() throws Exception {
        Map<String, FileMetadata> oldFiles = new HashMap<>();
        Map<String, FileMetadata> currentFiles = new HashMap<>();


        currentFiles.put("A.txt", 
            new FileMetadata("A.txt", 120, "111")
        );

        SnapshotComparator comparator = new SnapshotComparator();
        List<FileChange> changes = comparator.compare(oldFiles, currentFiles);
        assertEquals(1, changes.size());
        assertEquals(ChangeType.ADDED, changes.get(0).getChangeType());
    }
    @Test 
    void shouldMarkMissingFileAsDeleted() throws Exception {
        Map<String, FileMetadata> oldFiles = new HashMap<>();
        Map<String, FileMetadata> currentFiles = new HashMap<>();

        oldFiles.put("A.txt", 
            new FileMetadata("A.txt", 120, "111")
        );


        SnapshotComparator comparator = new SnapshotComparator();
        List<FileChange> changes = comparator.compare(oldFiles, currentFiles);
        assertEquals(1, changes.size());
        assertEquals(ChangeType.DELETED, changes.get(0).getChangeType());
    }
    @Test 
    void shouldMarkChangedHashAsModified() throws Exception {
        Map<String, FileMetadata> oldFiles = new HashMap<>();
        Map<String, FileMetadata> currentFiles = new HashMap<>();

        oldFiles.put("A.txt", 
            new FileMetadata("A.txt", 120, "111")
        );

        currentFiles.put("A.txt", 
            new FileMetadata("A.txt", 178, "222")
        );

        SnapshotComparator comparator = new SnapshotComparator();
        List<FileChange> changes = comparator.compare(oldFiles, currentFiles);
        assertEquals(1, changes.size());
        assertEquals(ChangeType.MODIFIED, changes.get(0).getChangeType());
    }
}
