package com.sefa.snapshot.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class FileMetadata {
    private final String path;
    private final long size;
    private final String hash;

    @JsonCreator
    public FileMetadata(
        @JsonProperty("path") String path, 
        @JsonProperty("size") long size,
        @JsonProperty("hash") String hash
    ){
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