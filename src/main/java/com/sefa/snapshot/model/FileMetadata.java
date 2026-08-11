package com.sefa.snapshot.model;

public class FileMetadata {
    private final String path;
    private final long size;
    private final String hash;

    public FileMetadata(String path, long size ,String hash){
        this.path = path;
        this.size = size;
        this.hash = hash;
    }
    public String getPath(){
        return this.path;
    }
    
    public long getSize(){
        return this.size;
    }
    
    public String getHash(){
        return this.hash;
    }
}