package com.sefa.snapshot.hashing;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;
import java.util.HexFormat;

public class FileHasher {
    
    public String hash(Path path) throws IOException, NoSuchAlgorithmException {
        byte[] fileBytes = Files.readAllBytes(path);
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = digest.digest(fileBytes);
        return HexFormat.of().formatHex(hashBytes);
        
    }
}
