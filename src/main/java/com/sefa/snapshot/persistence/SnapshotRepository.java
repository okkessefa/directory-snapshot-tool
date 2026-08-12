package com.sefa.snapshot.persistence;

import java.io.IOException;
import java.nio.file.Path;

import com.sefa.snapshot.model.Snapshot;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.databind.SerializationFeature;

public class SnapshotRepository {
    private final ObjectMapper mapper;

    public SnapshotRepository(){
        this.mapper = new ObjectMapper();
        this.mapper.registerModule(new JavaTimeModule());
        this.mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }
    public void save(Snapshot snapshot, Path outPath) throws IOException{
        mapper.writerWithDefaultPrettyPrinter().writeValue(outPath.toFile(), snapshot);
    }
    
    public Snapshot load(Path inputPath) throws IOException{
        return mapper.readValue(
            inputPath.toFile(), 
            Snapshot.class
        );
    }
}
