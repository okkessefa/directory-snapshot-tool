package com.sefa.snapshot;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.List;

import com.sefa.snapshot.comparison.SnapshotComparator;
import com.sefa.snapshot.hashing.FileHasher;
import com.sefa.snapshot.model.FileChange;
import com.sefa.snapshot.model.FileMetadata;
import com.sefa.snapshot.scanner.DirectoryScanner;

public class Main {
    public static void main(String[] args) throws Exception {
        // FileHasher hasher = new FileHasher();
        // Path path = Path.of("sample.txt");
        // long size = Files.size(path);
        // String hash = hasher.hash(path);

        // FileMetadata metadata = new FileMetadata(path.toString(), size, hash);

        // System.out.println("Path: "+ metadata.getPath());
        // System.out.println("Size: "+ metadata.getSize());
        // System.out.println("Hash: "+ metadata.getHash());

        DirectoryScanner scanner = new DirectoryScanner();
        // scanner.scan(Path.of("scanner-test"));

        Map<String, FileMetadata> result = scanner.scan(Path.of("scanner-test"));

        result.forEach((key, value) -> 
            System.out.println(value.getPath() + "\nSize: " + value.getSize() + "\nHash: " + value.getHash())
        );
    }
}
// 2cf24dba5fb0a30e26e83b2ac5b9e29e1b161e5c1fa7425e73043362938b9824
// 185f8db32271fe25f561a6fc938b2e264306ec304eda518007d1764826381969