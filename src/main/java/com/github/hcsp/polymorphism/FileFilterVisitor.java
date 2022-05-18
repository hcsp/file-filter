package com.github.hcsp.polymorphism;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class FileFilterVisitor extends SimpleFileVisitor<Path> {
    private final String extension;
    private final List<String> filterNames = new ArrayList<>();

    public FileFilterVisitor(String extension){
        this.extension = extension;
    }

    public List<String> getFilterNames() {
        return filterNames;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if(file.getFileName().toString().endsWith(extension)){
            filterNames.add(file.getFileName().toString());
        }
        return FileVisitResult.CONTINUE;
    }
}
