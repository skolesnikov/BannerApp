package com.bannerapp.runner;

import com.bannerapp.service.BannerService;
import com.bannerapp.service.FileWriterService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Main command-line entry point for the banner generator.
 *
 * <p>Spring Boot calls {@link #run(String...)} after the application
 * context is fully initialised and passes the raw program arguments.
 * The runner:
 * <ol>
 *   <li>Validates that at least one argument was supplied.</li>
 *   <li>Joins all arguments with a single space (so both
 *       {@code "Hello World"} and {@code Hello World} work).</li>
 *   <li>Delegates banner generation to {@link BannerService}.</li>
 *   <li>Prints the result to the console.</li>
 *   <li>Writes an identical copy to {@code banner.txt} in the current
 *       working directory via {@link FileWriterService}.</li>
 * </ol>
 */
@Component
public class BannerRunner implements CommandLineRunner {

    private static final String OUTPUT_FILE = "banner.txt";

    private final BannerService bannerService;
    private final FileWriterService fileWriterService;

    public BannerRunner(BannerService bannerService,
                        FileWriterService fileWriterService) {
        this.bannerService = bannerService;
        this.fileWriterService = fileWriterService;
    }

    @Override
    public void run(String... args) {
        if (args.length == 0) {
            printUsage();
            return;
        }

        // Join all tokens so both quoted and unquoted input works:
        //   java -jar BannerApp.jar "Hello World"   → args[0] = "Hello World"
        //   java -jar BannerApp.jar Hello World     → joined to "Hello World"
        String inputText = String.join(" ", args);

        if (inputText.isBlank()) {
            System.err.println("Error: input text is blank.");
            printUsage();
            return;
        }

        String banner = bannerService.generateBanner(inputText);

        // ── Console output ──────────────────────────────────────────
        // The banner string already ends with a newline, so use print
        // rather than println to avoid a double blank line.
        System.out.print(banner);

        // ── File output ─────────────────────────────────────────────
        try {
            fileWriterService.writeToFile(banner, OUTPUT_FILE);
            System.out.println("Banner written to: " + OUTPUT_FILE);
        } catch (IOException e) {
            System.err.println("Warning: could not write banner to '"
                    + OUTPUT_FILE + "': " + e.getMessage());
        }
    }

    // ---------------------------------------------------------------

    private static void printUsage() {
        System.out.println();
        System.out.println("  BannerApp — FIGlet-style ASCII art banner generator");
        System.out.println();
        System.out.println("  Usage:");
        System.out.println("    java -jar BannerApp.jar \"<text>\"");
        System.out.println();
        System.out.println("  Examples:");
        System.out.println("    java -jar BannerApp.jar \"Hello World\"");
        System.out.println("    java -jar BannerApp.jar \"Hello 123!\"");
        System.out.println();
        System.out.println("  Supported characters:");
        System.out.println("    A-Z (or a-z, converted to upper-case), 0-9,");
        System.out.println("    space, . , ! ? -");
        System.out.println("    (unsupported characters render as a placeholder box)");
        System.out.println();
    }
}
