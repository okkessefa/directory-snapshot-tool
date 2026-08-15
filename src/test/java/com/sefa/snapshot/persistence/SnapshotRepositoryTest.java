package com.sefa.snapshot.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import com.sefa.snapshot.model.FileMetadata;
import com.sefa.snapshot.model.Snapshot;

public class SnapshotRepositoryTest {
    
    @TempDir
    Path tempDir;

    @Test
    void shouldSaveAndLoadSnapshot() throws Exception {
        Map<String, FileMetadata> files = new HashMap<>();
        files.put("A.txt", new FileMetadata("A.txt",5, "111"));
        LocalDateTime createdAt = LocalDateTime.of(2026, 8, 15, 15, 30);
        Snapshot originalSnapshot = new Snapshot(createdAt, files);
        SnapshotRepository repository = new SnapshotRepository();
        Path snapshotPAth = tempDir.resolve("Snapshot.json");
        
        repository.save(originalSnapshot, snapshotPAth);
        Snapshot loadedSnapshot = repository.load(snapshotPAth);

        assertEquals(originalSnapshot.getCreatedAt(), loadedSnapshot.getCreatedAt());
        assertEquals(1, loadedSnapshot.getFiles().size());
        assertTrue(loadedSnapshot.getFiles().containsKey("A.txt"));
        FileMetadata loadedMetadata = loadedSnapshot.getFiles().get("A.txt");

        assertEquals("A.txt", loadedMetadata.getPath());
        assertEquals(5, loadedMetadata.getSize());
        assertEquals("111", loadedMetadata.getHash());
        
    }
}
