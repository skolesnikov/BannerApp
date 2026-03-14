package com.bannerapp.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

/**
 * Writes text content to a file in the current working directory.
 *
 * <p>Always uses UTF-8 encoding and overwrites any existing file
 * at the target path — consistent with the FIGlet-style workflow
 * of regenerating the banner on every run.
 *
 * <p>All I/O errors are propagated as checked {@link IOException}s
 * so the caller ({@link com.bannerapp.runner.BannerRunner}) can
 * decide how to report them to the user.
 */
@Service
public class FileWriterService {

    /**
     * Writes {@code content} to the file identified by {@code filename}.
     *
     * <p>The file path is resolved relative to the JVM's current working
     * directory (i.e. wherever the user invokes {@code java -jar}).
     *
     * @param content  the text to write; must not be {@code null}
     * @param filename the target file name (e.g. {@code "banner.txt"})
     * @throws IOException if the file cannot be created or written
     */
    public void writeToFile(String content, String filename) throws IOException {
        Path outputPath = Path.of(filename);

        Files.writeString(
                outputPath,
                content,
                StandardCharsets.UTF_8,
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING   // overwrite on each run
        );
    }
}
