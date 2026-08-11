package com.sefa.snapshot.comparison;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import com.sefa.snapshot.model.ChangeType;
import com.sefa.snapshot.model.FileChange;

public class SnapshotComparator {
    
    public  SnapshotComparator(){

    }

    public List<FileChange> compare(Map<String, String> oldFiles, Map<String, String> currentFiles){
        List<FileChange> changes = new ArrayList<>();
        Set<String> allFiles = new HashSet<>();

        allFiles.addAll(oldFiles.keySet());
        allFiles.addAll(currentFiles.keySet());

        for (String file : allFiles) {
            ChangeType changeType;
            if(oldFiles.containsKey(file) && !currentFiles.containsKey(file)){
                changeType = ChangeType.DELETED;
            }
            else if(!oldFiles.containsKey(file) && currentFiles.containsKey(file)){
                changeType = ChangeType.ADDED;
            }
            else{
                if(Objects.equals(oldFiles.get(file) , currentFiles.get(file))){
                    changeType = ChangeType.UNCHANGED;
                }
                else{
                    changeType = ChangeType.MODIFIED;
                }
            }
            FileChange change = new FileChange(file, changeType);
            changes.add(change);
        }

        return changes;
    }
    public void displayChanges(List<FileChange> changes){
        System.out.println("------------------------------------------------------");
        for (FileChange change : changes) {
            System.out.println(change.getPath() + " -> "+ change.getChangeType());
        }
        System.out.println("------------------------------------------------------");
    }
}
