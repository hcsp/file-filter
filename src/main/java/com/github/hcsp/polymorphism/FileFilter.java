package com.github.hcsp.polymorphism;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
     * A function that filters files by extension in the specified root directory.
     *
     * @param rootDirectory The root directory to start filtering from
     * @param extension     The file extension to filter by
     * @return A list of file names with the specified extension
     */
    public static List<String> filter(Path rootDirectory, String extension) throws IOException {
        try (Stream<Path> paths = Files.walk(rootDirectory)) {
            return paths.filter(Files::isRegularFile)
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .filter(name -> name.endsWith(extension))
                    .collect(Collectors.toList());
        }
    }
}
