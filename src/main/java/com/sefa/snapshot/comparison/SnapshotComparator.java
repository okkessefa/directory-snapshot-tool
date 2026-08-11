package com.sefa.snapshot.comparison;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import com.sefa.snapshot.model.ChangeType;
import com.sefa.snapshot.model.FileChange;
import com.sefa.snapshot.model.FileMetadata;

public class SnapshotComparator {

    public List<FileChange> compare(Map<String, FileMetadata> oldFiles, Map<String, FileMetadata> currentFiles){
        List<FileChange> changes = new ArrayList<>();
        Set<String> allFiles = new HashSet<>();

        allFiles.addAll(oldFiles.keySet());
        allFiles.addAll(currentFiles.keySet());

        for (String file : allFiles) {
            ChangeType changeType;
            FileMetadata oldMetaData = oldFiles.get(file);
            FileMetadata currenMetadata = currentFiles.get(file);
            if(oldFiles.containsKey(file) && !currentFiles.containsKey(file)){
                changeType = ChangeType.DELETED;
            }
            else if(!oldFiles.containsKey(file) && currentFiles.containsKey(file)){
                changeType = ChangeType.ADDED;
            }
            else if(!Objects.equals(oldMetaData.getHash() , currenMetadata.getHash())){
                changeType = ChangeType.MODIFIED;
            }
            else{
                changeType = ChangeType.UNCHANGED;
            }
            
            changes.add(new FileChange(file, changeType));
        }

        return changes;
    }
    
}
