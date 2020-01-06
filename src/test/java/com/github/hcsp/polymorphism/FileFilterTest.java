package com.github.hcsp.polymorphism;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FileFilterTest {
    @Test
    public void test() throws IOException {
        Path testPath =
                Paths.get(System.getProperty("basedir", System.getProperty("user.dir")))
                        .resolve("test-root");
        Assertions.assertEquals(
                asSet("1.txt", "5.txt", "6.txt"),
                new HashSet<>(FileFilter.filter(testPath, ".txt")));
        Assertions.assertEquals(
                asSet("2.csv", "3.csv", "4.csv", "7.csv"),
                new HashSet<>(FileFilter.filter(testPath, ".csv")));
    }

    private Set<String> asSet(String... elements) {
        return new HashSet<>(Arrays.asList(elements));
    }
}