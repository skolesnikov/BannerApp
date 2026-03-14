package com.bannerapp.font;

import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Provides ASCII-art glyph data for the banner generator.
 *
 * <p>Uses the Roman FIGlet font by Nick Miners (June 1994).
 * Each character is stored as a {@code String[]} of exactly
 * {@link #FONT_HEIGHT} rows.  Every string in the array is one
 * horizontal line of the rendered big letter.  Widths vary
 * between characters.
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
    public static final int FONT_HEIGHT = 10;

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
            " ##### ",
            "       ",
            "       ",
            "       "
        };
    }

    // ---------------------------------------------------------------
    // Font data — Roman FIGlet font (Nick Miners, June 1994)
    // Height: 10 rows per glyph.  Hard-blanks replaced with spaces.
    // ---------------------------------------------------------------

    private Map<Character, String[]> buildFontMap() {
        Map<Character, String[]> m = new HashMap<>();

        // ============================================================
        //  Letters A – Z
        // ============================================================

        m.put('A', new String[]{
            "      .o.      ",
            "     .888.     ",
            "    .8\"888.    ",
            "   .8' `888.   ",
            "  .88ooo8888.  ",
            " .8'     `888. ",
            "o88o     o8888o",
            "               ",
            "               ",
            "               "
        });

        m.put('B', new String[]{
            "oooooooooo. ",
            "`888'   `Y8b",
            " 888     888",
            " 888oooo888'",
            " 888    `88b",
            " 888    .88P",
            "o888bood8P' ",
            "            ",
            "            ",
            "            "
        });

        m.put('C', new String[]{
            "  .oooooo.  ",
            " d8P'  `Y8b ",
            "888         ",
            "888         ",
            "888         ",
            "`88b    ooo ",
            " `Y8bood8P' ",
            "            ",
            "            ",
            "            "
        });

        m.put('D', new String[]{
            "oooooooooo.  ",
            "`888'   `Y8b ",
            " 888      888",
            " 888      888",
            " 888      888",
            " 888     d88'",
            "o888bood8P'  ",
            "             ",
            "             ",
            "             "
        });

        m.put('E', new String[]{
            "oooooooooooo",
            "`888'     `8",
            " 888        ",
            " 888oooo8   ",
            " 888    \"   ",
            " 888       o",
            "o888ooooood8",
            "            ",
            "            ",
            "            "
        });

        m.put('F', new String[]{
            "oooooooooooo",
            "`888'     `8",
            " 888        ",
            " 888oooo8   ",
            " 888    \"   ",
            " 888        ",
            "o888o       ",
            "            ",
            "            ",
            "            "
        });

        m.put('G', new String[]{
            "  .oooooo.   ",
            " d8P'  `Y8b  ",
            "888          ",
            "888          ",
            "888     ooooo",
            "`88.    .88' ",
            " `Y8bood8P'  ",
            "             ",
            "             ",
            "             "
        });

        m.put('H', new String[]{
            "ooooo   ooooo",
            "`888'   `888'",
            " 888     888 ",
            " 888ooooo888 ",
            " 888     888 ",
            " 888     888 ",
            "o888o   o888o",
            "             ",
            "             ",
            "             "
        });

        m.put('I', new String[]{
            "ooooo",
            "`888'",
            " 888 ",
            " 888 ",
            " 888 ",
            " 888 ",
            "o888o",
            "     ",
            "     ",
            "     "
        });

        m.put('J', new String[]{
            "   oooo",
            "   `888",
            "    888",
            "    888",
            "    888",
            "    888",
            ".o. 88P",
            "`Y888P ",
            "       ",
            "       "
        });

        m.put('K', new String[]{
            "oooo    oooo",
            "`888   .8P' ",
            " 888  d8'   ",
            " 88888[     ",
            " 888`88b.   ",
            " 888  `88b. ",
            "o888o  o888o",
            "            ",
            "            ",
            "            "
        });

        m.put('L', new String[]{
            "ooooo       ",
            "`888'       ",
            " 888        ",
            " 888        ",
            " 888        ",
            " 888       o",
            "o888ooooood8",
            "            ",
            "            ",
            "            "
        });

        m.put('M', new String[]{
            "ooo        ooooo",
            "`88.       .888'",
            " 888b     d'888 ",
            " 8 Y88. .P  888 ",
            " 8  `888'   888 ",
            " 8    Y     888 ",
            "o8o        o888o",
            "                ",
            "                ",
            "                "
        });

        m.put('N', new String[]{
            "ooooo      ooo",
            "`888b.     `8'",
            " 8 `88b.    8 ",
            " 8   `88b.  8 ",
            " 8     `88b.8 ",
            " 8       `888 ",
            "o8o        `8 ",
            "              ",
            "              ",
            "              "
        });

        m.put('O', new String[]{
            "  .oooooo.  ",
            " d8P'  `Y8b ",
            "888      888",
            "888      888",
            "888      888",
            "`88b    d88'",
            " `Y8bood8P' ",
            "            ",
            "            ",
            "            "
        });

        m.put('P', new String[]{
            "ooooooooo.  ",
            "`888   `Y88.",
            " 888   .d88'",
            " 888ooo88P' ",
            " 888        ",
            " 888        ",
            "o888o       ",
            "            ",
            "            ",
            "            "
        });

        m.put('Q', new String[]{
            "  .oooooo.     ",
            " d8P'  `Y8b    ",
            "888      888   ",
            "888      888   ",
            "888      888   ",
            "`88b    d88b   ",
            " `Y8bood8P'Ybd'",
            "               ",
            "               ",
            "               "
        });

        m.put('R', new String[]{
            "ooooooooo.  ",
            "`888   `Y88.",
            " 888   .d88'",
            " 888ooo88P' ",
            " 888`88b.   ",
            " 888  `88b. ",
            "o888o  o888o",
            "            ",
            "            ",
            "            "
        });

        m.put('S', new String[]{
            " .oooooo..o",
            "d8P'    `Y8",
            "Y88bo.     ",
            " `\"Y8888o. ",
            "     `\"Y88b",
            "oo     .d8P",
            "8\"\"88888P' ",
            "           ",
            "           ",
            "           "
        });

        m.put('T', new String[]{
            "ooooooooooooo",
            "8'   888   `8",
            "     888     ",
            "     888     ",
            "     888     ",
            "     888     ",
            "    o888o    ",
            "             ",
            "             ",
            "             "
        });

        m.put('U', new String[]{
            "ooooo     ooo",
            "`888'     `8'",
            " 888       8 ",
            " 888       8 ",
            " 888       8 ",
            " `88.    .8' ",
            "   `YbodP'   ",
            "             ",
            "             ",
            "             "
        });

        m.put('V', new String[]{
            "oooooo     oooo",
            " `888.     .8' ",
            "  `888.   .8'  ",
            "   `888. .8'   ",
            "    `888.8'    ",
            "     `888'     ",
            "      `8'      ",
            "               ",
            "               ",
            "               "
        });

        m.put('W', new String[]{
            "oooooo   oooooo     oooo",
            " `888.    `888.     .8' ",
            "  `888.   .8888.   .8'  ",
            "   `888  .8'`888. .8'   ",
            "    `888.8'  `888.8'    ",
            "     `888'    `888'     ",
            "      `8'      `8'      ",
            "                        ",
            "                        ",
            "                        "
        });

        m.put('X', new String[]{
            "ooooooo  ooooo",
            " `8888    d8' ",
            "   Y888..8P   ",
            "    `8888'    ",
            "   .8PY888.   ",
            "  d8'  `888b  ",
            "o888o  o88888o",
            "              ",
            "              ",
            "              "
        });

        m.put('Y', new String[]{
            "oooooo   oooo",
            " `888.   .8' ",
            "  `888. .8'  ",
            "   `888.8'   ",
            "    `888'    ",
            "     888     ",
            "    o888o    ",
            "             ",
            "             ",
            "             "
        });

        m.put('Z', new String[]{
            " oooooooooooo",
            "d'\"\"\"\"\"\"d888'",
            "      .888P  ",
            "     d888'   ",
            "   .888P     ",
            "  d888'    .P",
            ".8888888888P ",
            "             ",
            "             ",
            "             "
        });

        // ============================================================
        //  Digits 0 – 9
        // ============================================================

        m.put('0', new String[]{
            "  .oooo.  ",
            " d8P'`Y8b ",
            "888    888",
            "888    888",
            "888    888",
            "`88b  d88'",
            " `Y8bd8P' ",
            "          ",
            "          ",
            "          "
        });

        m.put('1', new String[]{
            "  .o ",
            "o888 ",
            " 888 ",
            " 888 ",
            " 888 ",
            " 888 ",
            "o888o",
            "     ",
            "     ",
            "     "
        });

        m.put('2', new String[]{
            "  .oooo.  ",
            ".dP\"\"Y88b ",
            "      ]8P'",
            "    .d8P' ",
            "  .dP'    ",
            ".oP     .o",
            "8888888888",
            "          ",
            "          ",
            "          "
        });

        m.put('3', new String[]{
            "  .oooo.  ",
            ".dP\"\"Y88b ",
            "      ]8P'",
            "    <88b. ",
            "     `88b.",
            "o.   .88P ",
            "`8bd88P'  ",
            "          ",
            "          ",
            "          "
        });

        m.put('4', new String[]{
            "      .o  ",
            "    .d88  ",
            "  .d'888  ",
            ".d'  888  ",
            "88ooo888oo",
            "     888  ",
            "    o888o ",
            "          ",
            "          ",
            "          "
        });

        m.put('5', new String[]{
            "  oooooooo",
            " dP\"\"\"\"\"\"\"",
            "d88888b.  ",
            "    `Y88b ",
            "      ]88 ",
            "o.   .88P ",
            "`8bd88P'  ",
            "          ",
            "          ",
            "          "
        });

        m.put('6', new String[]{
            "    .ooo  ",
            "  .88'    ",
            " d88'     ",
            "d888P\"Ybo.",
            "Y88[   ]88",
            "`Y88   88P",
            " `88bod8' ",
            "          ",
            "          ",
            "          "
        });

        m.put('7', new String[]{
            " ooooooooo",
            "d\"\"\"\"\"\"\"8'",
            "      .8' ",
            "     .8'  ",
            "    .8'   ",
            "   .8'    ",
            "  .8'     ",
            "          ",
            "          ",
            "          "
        });

        m.put('8', new String[]{
            " .ooooo.  ",
            "d88'   `8.",
            "Y88..  .8'",
            " `88888b. ",
            ".8'  ``88b",
            "`8.   .88P",
            " `boood8' ",
            "          ",
            "          ",
            "          "
        });

        m.put('9', new String[]{
            " .ooooo.  ",
            "888' `Y88.",
            "888    888",
            " `Vbood888",
            "      888'",
            "    .88P' ",
            "  .oP'    ",
            "          ",
            "          ",
            "          "
        });

        // ============================================================
        //  Space  (3 wide)
        // ============================================================

        m.put(' ', new String[]{
            "   ",
            "   ",
            "   ",
            "   ",
            "   ",
            "   ",
            "   ",
            "   ",
            "   ",
            "   "
        });

        // ============================================================
        //  Punctuation
        // ============================================================

        // Period  (3 wide)
        m.put('.', new String[]{
            "   ",
            "   ",
            "   ",
            "   ",
            "   ",
            ".o.",
            "Y8P",
            " ' ",
            "   ",
            "   "
        });

        // Comma  (3 wide)
        m.put(',', new String[]{
            "   ",
            "   ",
            "   ",
            "   ",
            "   ",
            ".o.",
            "Y8P",
            " ' ",
            "   ",
            "   "
        });

        // Exclamation mark  (3 wide)
        m.put('!', new String[]{
            ".o.",
            "888",
            "888",
            "Y8P",
            "`8'",
            ".o.",
            "Y8P",
            "   ",
            "   ",
            "   "
        });

        // Question mark  (10 wide)
        m.put('?', new String[]{
            " .oooooo. ",
            "dP'   `Y8b",
            "88o   .d8P",
            "`\"' .d8P' ",
            "   `88'   ",
            "   .o.    ",
            "   Y8P    ",
            "          ",
            "          ",
            "          "
        });

        // Hyphen / minus  (7 wide)
        m.put('-', new String[]{
            "       ",
            "       ",
            "       ",
            "       ",
            "8888888",
            "       ",
            "       ",
            "       ",
            "       ",
            "       "
        });

        return m;
    }
}
