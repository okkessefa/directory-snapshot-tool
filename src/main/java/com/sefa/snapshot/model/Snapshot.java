package com.sefa.snapshot.model;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


public class Snapshot {
    private final LocalDateTime createdAt;
    private final Map<String, FileMetadata> files;

    @JsonCreator
    public Snapshot(
        @JsonProperty("createdAt") LocalDateTime createdAt, 
        @JsonProperty("files") Map<String, FileMetadata> files
    ){
        this.createdAt = createdAt;
        this.files = new HashMap<>(files);
    }

    public LocalDateTime getCreatedAt(){
        return this.createdAt;
    }

    public Map<String, FileMetadata> getFiles(){
        return this.files;
    }
}
