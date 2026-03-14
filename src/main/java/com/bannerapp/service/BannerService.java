package com.bannerapp.service;

import com.bannerapp.font.AsciiFont;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Generates a multi-line ASCII-art banner from an input string.
 *
 * <h2>Algorithm</h2>
 * <ol>
 *   <li>Convert the input to upper-case (the font only stores A-Z).</li>
 *   <li>Look up the glyph ({@code String[]}) for every character.
 *       Unsupported characters fall back to a placeholder glyph.</li>
 *   <li>Build the output line by line: for row {@code r}, concatenate
 *       {@code glyph[r]} for every character, separated by one space
 *       column.  Trailing whitespace is stripped per line.</li>
 *   <li>Return the complete banner as a single {@code String} where
 *       lines are separated by {@code \n}.  The string ends with a
 *       trailing {@code \n} so it prints cleanly with
 *       {@code System.out.print()}.</li>
 * </ol>
 *
 * <h2>Extending with additional fonts</h2>
 * Inject a different {@link AsciiFont} implementation (or add a font
 * parameter to this service) to support multiple styles in the future.
 */
@Service
public class BannerService {

    /** Number of blank columns inserted between adjacent glyphs. */
    private static final int GLYPH_SEPARATOR_WIDTH = 1;

    private final AsciiFont asciiFont;

    public BannerService(AsciiFont asciiFont) {
        this.asciiFont = asciiFont;
    }

    /**
     * Converts {@code text} into a multi-line ASCII-art banner string.
     *
     * @param text the text to render; may contain mixed case, digits,
     *             spaces, and the punctuation marks defined in
     *             {@link AsciiFont}
     * @return a non-null {@code String} ending with {@code '\n'};
     *         returns a single newline if {@code text} is blank
     */
    public String generateBanner(String text) {
        if (text == null || text.isBlank()) {
            return "\n";
        }

        // Upper-case conversion: letters become A-Z; digits and
        // punctuation are unaffected by toUpperCase().
        String normalised = text.toUpperCase();

        // Collect the glyph for every character.
        List<String[]> glyphs = new ArrayList<>(normalised.length());
        for (char c : normalised.toCharArray()) {
            glyphs.add(asciiFont.getGlyph(c));
        }

        // Build the banner row by row.
        StringBuilder banner = new StringBuilder();
        String separator = " ".repeat(GLYPH_SEPARATOR_WIDTH);

        for (int row = 0; row < AsciiFont.FONT_HEIGHT; row++) {
            StringBuilder line = new StringBuilder();

            for (int i = 0; i < glyphs.size(); i++) {
                if (i > 0) {
                    line.append(separator);
                }
                line.append(glyphs.get(i)[row]);
            }

            // Strip trailing spaces so the output is clean when viewed
            // in an editor or compared with diff.
            banner.append(line.toString().stripTrailing());
            banner.append('\n');
        }

        return banner.toString();
    }
}
