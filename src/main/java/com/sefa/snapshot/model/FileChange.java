package com.sefa.snapshot.model;

public class FileChange {
    private final String path;
    private final ChangeType changeType;
    
    public FileChange(String path, ChangeType changeType){
        this.path = path;
        this.changeType = changeType;
    }

    public String getPath(){
        return this.path;
    }
    public ChangeType getChangeType(){
        return this.changeType;
    }
}
