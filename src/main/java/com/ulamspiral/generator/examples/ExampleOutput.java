package com.ulamspiral.generator.examples;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Helper for example classes
 */
final class ExampleOutput {

    private static final Path DIRECTORY = Path.of("generated-examples");

    private ExampleOutput() {}

    static File resolve(String fileName) throws IOException {
        Files.createDirectories(DIRECTORY);
        return DIRECTORY.resolve(fileName).toFile();
    }
}
