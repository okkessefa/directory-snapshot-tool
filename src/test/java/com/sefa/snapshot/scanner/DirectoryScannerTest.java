package com.sefa.snapshot.scanner;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import com.sefa.snapshot.model.FileMetadata;

public class DirectoryScannerTest {
    @TempDir
    Path tempDir;

    @Test
    void shouldScanRegularFilesRecursively() throws Exception {
        
        // ARRANGE
        Path fileA = tempDir.resolve("A.txt");
        Files.writeString(fileA, "Hello");

        Path subFolder = tempDir.resolve("sub-folder");
        Files.createDirectories(subFolder);

        Path fileB = subFolder.resolve("B.txt");
        Files.writeString(fileB, "hello");

        //ACT
        DirectoryScanner scanner = new DirectoryScanner();
        Map<String, FileMetadata> result = scanner.scan(tempDir);
        System.out.println(result.keySet());
        
        assertTrue(result.containsKey("A.txt"));
        String nestedPath =
                Path.of("sub-folder", "B.txt").toString();
        assertTrue(result.containsKey(nestedPath));

    }


}
