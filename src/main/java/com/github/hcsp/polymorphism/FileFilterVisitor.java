package com.github.hcsp.polymorphism;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class FileFilterVisitor extends SimpleFileVisitor<Path> {

    public String extension;
    private List<String> filterNames = new ArrayList<>();

    // 构造器，将extension的值传到这个地方
    public FileFilterVisitor(String extension) {
        this.extension = extension;
    }

    public List<String> getFilterNames() {
        return filterNames;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        // 扩展名过滤
        if (file.getFileName().toString().endsWith(extension)) {
            filterNames.add(file.getFileName().toString());
        }
        return FileVisitResult.CONTINUE;
    }
}
