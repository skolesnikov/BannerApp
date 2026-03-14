package com.bannerapp.font;

import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Provides ASCII-art glyph data for the banner generator.
 *
 * <p>Each character is stored as a {@code String[]} of exactly
 * {@link #FONT_HEIGHT} rows.  Every string in the array is one
 * horizontal line of the rendered big letter.  Widths may vary
 * between characters; A-Z and 0-9 are all 7 characters wide while
 * punctuation marks may be narrower.
 *
 * <p>Only uppercase letters A-Z are stored; the caller is responsible
 * for converting lowercase input before calling {@link #getGlyph(char)}.
 *
 * <p>To add a new font in the future, implement a second {@code @Component}
 * with the same interface and inject it by qualifier.
 */
@Component
public class AsciiFont {

    /** Number of text rows that make up every glyph in this font. */
    public static final int FONT_HEIGHT = 7;

    private final Map<Character, String[]> fontMap;

    public AsciiFont() {
        fontMap = buildFontMap();
        validateGlyphs();
    }

    // ---------------------------------------------------------------
    // Public API
    // ---------------------------------------------------------------

    /**
     * Returns the glyph for the given character.
     *
     * <p>The returned array always has exactly {@link #FONT_HEIGHT} rows.
     * If the character is not in the font, a placeholder "unknown" glyph
     * is returned so the program never crashes on unsupported input.
     *
     * @param c the character to look up (must already be upper-case for letters)
     * @return a {@code String[]} of length {@value #FONT_HEIGHT}
     */
    public String[] getGlyph(char c) {
        return fontMap.getOrDefault(c, placeholder());
    }

    /**
     * Returns an unmodifiable view of the entire font map.
     * Intended for testing and for tools that want to enumerate
     * all supported characters.
     */
    public Map<Character, String[]> getFontMap() {
        return Collections.unmodifiableMap(fontMap);
    }

    // ---------------------------------------------------------------
    // Validation
    // ---------------------------------------------------------------

    /**
     * Ensures every glyph defined in {@link #buildFontMap()} has the
     * correct number of rows.  Called once at construction time so
     * coding mistakes are caught at startup rather than silently
     * producing misaligned output.
     */
    private void validateGlyphs() {
        fontMap.forEach((ch, rows) -> {
            if (rows.length != FONT_HEIGHT) {
                throw new IllegalStateException(
                        "Font definition error: glyph for '" + ch + "' has "
                        + rows.length + " row(s), expected " + FONT_HEIGHT);
            }
        });
    }

    // ---------------------------------------------------------------
    // Placeholder for unsupported characters
    // ---------------------------------------------------------------

    private static String[] placeholder() {
        return new String[]{
            " ##### ",
            " #   # ",
            " # # # ",
            " # # # ",
            " # # # ",
            " #   # ",
            " ##### "
        };
    }

    // ---------------------------------------------------------------
    // Font data — hand-crafted 7-row, block-style ASCII font
    //
    // Convention:
    //   '#' = filled pixel
    //   ' ' = empty pixel
    //
    // All letters A-Z and digits 0-9 are 7 characters wide.
    // Space is 5 characters wide.
    // Punctuation widths vary (4 or 7 characters wide).
    //
    // One column of spaces is appended as a separator between glyphs
    // by BannerService — no extra trailing padding is needed here.
    // ---------------------------------------------------------------

    private Map<Character, String[]> buildFontMap() {
        Map<Character, String[]> m = new HashMap<>();

        // ============================================================
        //  Letters A – Z
        // ============================================================

        m.put('A', new String[]{
            "  ###  ",
            " #   # ",
            " #   # ",
            " ##### ",
            " #   # ",
            " #   # ",
            " #   # "
        });

        m.put('B', new String[]{
            " ####  ",
            " #   # ",
            " #   # ",
            " ####  ",
            " #   # ",
            " #   # ",
            " ####  "
        });

        m.put('C', new String[]{
            "  #### ",
            " #     ",
            " #     ",
            " #     ",
            " #     ",
            " #     ",
            "  #### "
        });

        m.put('D', new String[]{
            " ####  ",
            " #   # ",
            " #   # ",
            " #   # ",
            " #   # ",
            " #   # ",
            " ####  "
        });

        m.put('E', new String[]{
            " ##### ",
            " #     ",
            " #     ",
            " ####  ",
            " #     ",
            " #     ",
            " ##### "
        });

        m.put('F', new String[]{
            " ##### ",
            " #     ",
            " #     ",
            " ####  ",
            " #     ",
            " #     ",
            " #     "
        });

        m.put('G', new String[]{
            "  #### ",
            " #     ",
            " #     ",
            " #  ## ",
            " #   # ",
            " #   # ",
            "  #### "
        });

        m.put('H', new String[]{
            " #   # ",
            " #   # ",
            " #   # ",
            " ##### ",
            " #   # ",
            " #   # ",
            " #   # "
        });

        m.put('I', new String[]{
            " ##### ",
            "   #   ",
            "   #   ",
            "   #   ",
            "   #   ",
            "   #   ",
            " ##### "
        });

        m.put('J', new String[]{
            " ##### ",
            "     # ",
            "     # ",
            "     # ",
            "     # ",
            " #   # ",
            "  ###  "
        });

        m.put('K', new String[]{
            " #   # ",
            " #  #  ",
            " # #   ",
            " ##    ",
            " # #   ",
            " #  #  ",
            " #   # "
        });

        m.put('L', new String[]{
            " #     ",
            " #     ",
            " #     ",
            " #     ",
            " #     ",
            " #     ",
            " ##### "
        });

        m.put('M', new String[]{
            " #   # ",
            " ## ## ",
            " # # # ",
            " #   # ",
            " #   # ",
            " #   # ",
            " #   # "
        });

        m.put('N', new String[]{
            " #   # ",
            " ##  # ",
            " # # # ",
            " #  ## ",
            " #   # ",
            " #   # ",
            " #   # "
        });

        m.put('O', new String[]{
            "  ###  ",
            " #   # ",
            " #   # ",
            " #   # ",
            " #   # ",
            " #   # ",
            "  ###  "
        });

        m.put('P', new String[]{
            " ####  ",
            " #   # ",
            " #   # ",
            " ####  ",
            " #     ",
            " #     ",
            " #     "
        });

        m.put('Q', new String[]{
            "  ###  ",
            " #   # ",
            " #   # ",
            " #   # ",
            " # # # ",
            " #  ## ",
            "  #### "
        });

        m.put('R', new String[]{
            " ####  ",
            " #   # ",
            " #   # ",
            " ####  ",
            " # #   ",
            " #  #  ",
            " #   # "
        });

        m.put('S', new String[]{
            "  #### ",
            " #     ",
            " #     ",
            "  ###  ",
            "     # ",
            "     # ",
            " ####  "
        });

        m.put('T', new String[]{
            " ##### ",
            "   #   ",
            "   #   ",
            "   #   ",
            "   #   ",
            "   #   ",
            "   #   "
        });

        m.put('U', new String[]{
            " #   # ",
            " #   # ",
            " #   # ",
            " #   # ",
            " #   # ",
            " #   # ",
            "  ###  "
        });

        m.put('V', new String[]{
            " #   # ",
            " #   # ",
            " #   # ",
            " #   # ",
            " #   # ",
            "  # #  ",
            "   #   "
        });

        m.put('W', new String[]{
            " #   # ",
            " #   # ",
            " #   # ",
            " # # # ",
            " # # # ",
            " ## ## ",
            " #   # "
        });

        m.put('X', new String[]{
            " #   # ",
            " #   # ",
            "  # #  ",
            "   #   ",
            "  # #  ",
            " #   # ",
            " #   # "
        });

        m.put('Y', new String[]{
            " #   # ",
            " #   # ",
            "  # #  ",
            "   #   ",
            "   #   ",
            "   #   ",
            "   #   "
        });

        m.put('Z', new String[]{
            " ##### ",
            "     # ",
            "    #  ",
            "   #   ",
            "  #    ",
            " #     ",
            " ##### "
        });

        // ============================================================
        //  Digits 0 – 9
        // ============================================================

        m.put('0', new String[]{
            "  ###  ",
            " #   # ",
            " #  ## ",
            " # # # ",
            " ##  # ",
            " #   # ",
            "  ###  "
        });

        m.put('1', new String[]{
            "   #   ",
            "  ##   ",
            "   #   ",
            "   #   ",
            "   #   ",
            "   #   ",
            " ##### "
        });

        m.put('2', new String[]{
            "  ###  ",
            " #   # ",
            "     # ",
            "    #  ",
            "   #   ",
            "  #    ",
            " ##### "
        });

        m.put('3', new String[]{
            "  ###  ",
            " #   # ",
            "     # ",
            "  ###  ",
            "     # ",
            " #   # ",
            "  ###  "
        });

        m.put('4', new String[]{
            "    #  ",
            "   ##  ",
            "  # #  ",
            " #  #  ",
            " ##### ",
            "    #  ",
            "    #  "
        });

        m.put('5', new String[]{
            " ##### ",
            " #     ",
            " #     ",
            " ####  ",
            "     # ",
            " #   # ",
            "  ###  "
        });

        m.put('6', new String[]{
            "  ###  ",
            " #     ",
            " #     ",
            " ####  ",
            " #   # ",
            " #   # ",
            "  ###  "
        });

        m.put('7', new String[]{
            " ##### ",
            "     # ",
            "    #  ",
            "   #   ",
            "  #    ",
            "  #    ",
            "  #    "
        });

        m.put('8', new String[]{
            "  ###  ",
            " #   # ",
            " #   # ",
            "  ###  ",
            " #   # ",
            " #   # ",
            "  ###  "
        });

        m.put('9', new String[]{
            "  ###  ",
            " #   # ",
            " #   # ",
            "  #### ",
            "     # ",
            "     # ",
            "  ###  "
        });

        // ============================================================
        //  Space  (5 wide — creates visible word gap)
        // ============================================================

        m.put(' ', new String[]{
            "     ",
            "     ",
            "     ",
            "     ",
            "     ",
            "     ",
            "     "
        });

        // ============================================================
        //  Punctuation
        // ============================================================

        // Period  (4 wide)
        m.put('.', new String[]{
            "    ",
            "    ",
            "    ",
            "    ",
            "    ",
            " ## ",
            " ## "
        });

        // Comma  (4 wide)
        m.put(',', new String[]{
            "    ",
            "    ",
            "    ",
            "    ",
            " ## ",
            " ## ",
            " #  "
        });

        // Exclamation mark  (4 wide)
        m.put('!', new String[]{
            " ## ",
            " ## ",
            " ## ",
            " ## ",
            "    ",
            " ## ",
            " ## "
        });

        // Question mark  (7 wide)
        m.put('?', new String[]{
            "  ###  ",
            " #   # ",
            "     # ",
            "   ##  ",
            "   #   ",
            "       ",
            "   #   "
        });

        // Hyphen / minus  (7 wide)
        m.put('-', new String[]{
            "       ",
            "       ",
            "       ",
            " ##### ",
            "       ",
            "       ",
            "       "
        });

        return m;
    }
}
