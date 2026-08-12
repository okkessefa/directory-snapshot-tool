package com.sefa.snapshot.report;

import java.util.List;

import com.sefa.snapshot.model.FileChange;

public class ConsoleReporter {
    
    public void print(List<FileChange> changes) {
        int added = 0;
        int deleted = 0;
        int modified = 0;
        int unchanged = 0;
        System.out.println("--------------------");
        System.out.println("File                ");
        System.out.println("--------------------");
        for (FileChange change : changes) {
            switch (change.getChangeType()) {
                case ADDED:
                    added++;
                    break;
            
                case MODIFIED:
                    modified++;
                    break;
            
                case UNCHANGED:
                    unchanged++;
                    break;
            
                case DELETED:
                    deleted++;
                    break;
            }
            System.out.println(
                change.getPath() 
                + " -> "
                + change.getChangeType()
            );
        }
        System.out.println("--------------------");
        System.out.println("Directory comparison");
        System.out.println("--------------------");
        System.out.println("Deleted: \t" + deleted);
        System.out.println("Added:  \t" + added);
        System.out.println("Modified:  \t" + modified);
        System.out.println("Unchanged:  \t" + unchanged);
    }

}
