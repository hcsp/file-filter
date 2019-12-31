package com.github.hcsp.polymorphism;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class FileFilter {
    public static void main(String[] args) throws IOException {
        Path projectDir = Paths.get(System.getProperty("user.dir"));
        Path testRootDir = projectDir.resolve("test-root");
        if (!testRootDir.toFile().isDirectory()) {
            throw new IllegalStateException(testRootDir.toAbsolutePath().toString() + "不存在！");
        }

        List<String> filteredFileNames = filter(testRootDir, ".csv");
        System.out.println(filteredFileNames);
    }

    /**
     * 实现一个按照扩展名过滤文件的功能
     *
     * @param rootDirectory 要过滤的文件夹
     * @param extension     要过滤的文件扩展名，例如 .txt
     * @return 所有该文件夹（及其后代子文件夹中）匹配指定扩展名的文件的名字
     */
    public static List<String> filter(Path rootDirectory, String extension) throws IOException {
//        FileFilterVisitor fileFilterVisitor = new FileFilterVisitor(extension);
//        Files.walkFileTree(rootDirectory,fileFilterVisitor);
//        return fileFilterVisitor.getNameList();
        List<String> nameList = new ArrayList<>();
        Files.walkFileTree(rootDirectory, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                if (file.getFileName().toString().endsWith(extension)) {
                    nameList.add(file.getFileName().toString());
                }
                return FileVisitResult.CONTINUE;
            }
        });
        return nameList;
    }
}

//class FileFilterVisitor extends SimpleFileVisitor<Path> {
//    String extension;
//    List<String> nameList = new ArrayList<>();
//
//    public FileFilterVisitor(String extension) {
//        this.extension = extension;
//    }
//
//    public List<String> getNameList() {
//        return nameList;
//    }
//
//    @Override
//    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
//        if (file.getFileName().toString().endsWith(extension)) {
//            nameList.add(file.getFileName().toString());
//        }
//        return FileVisitResult.CONTINUE;
//    }
// }
