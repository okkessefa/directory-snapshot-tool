package com.sefa.snapshot.report;

import java.util.List;

import com.sefa.snapshot.model.FileChange;

public class ConsoleReporter {
    
    public void print(List<FileChange> changes) {
        for (FileChange change : changes) {
                System.out.println(
                    change.getPath() 
                    + " -> "
                    + change.getChangeType()
                );
            }
    }

}
