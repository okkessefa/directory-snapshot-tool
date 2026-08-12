package com.sefa.snapshot.scanner;

import java.security.NoSuchAlgorithmException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.stream.Stream;
import java.io.IOException;

import com.sefa.snapshot.hashing.FileHasher;
import com.sefa.snapshot.model.FileMetadata;

public class DirectoryScanner {
    
    public Map<String, FileMetadata> scan(Path root) throws IOException ,NoSuchAlgorithmException{
        Map<String, FileMetadata> files = new HashMap<>();
        FileHasher hasher = new FileHasher();

        try(Stream<Path> paths = Files.walk(root)) {
            List<Path> regularFiles = paths.filter(Files::isRegularFile).toList();

            for (Path path : regularFiles) {
                Path relativePath = root.relativize(path);
                long size = Files.size(path);
                String hash = hasher.hash(path);

                FileMetadata metadata = new FileMetadata(
                    relativePath.toString(), 
                    size, 
                    hash);
                
                files.put(relativePath.toString(), metadata);
            }
        }

        return files;
    }

}
