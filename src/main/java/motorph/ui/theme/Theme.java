package motorph.ui.theme;

import java.awt.*;

public final class Theme {
    private Theme() {}

    // Primary palette
    public static final Color PRIMARY       = new Color(0x1B5E7B);
    public static final Color PRIMARY_DARK  = new Color(0x134B62);
    public static final Color PRIMARY_LIGHT = new Color(0xE8F4F8);

    // Semantic
    public static final Color SUCCESS       = new Color(0x28A745);
    public static final Color SUCCESS_LIGHT = new Color(0xD4EDDA);
    public static final Color DANGER        = new Color(0xDC3545);
    public static final Color DANGER_LIGHT  = new Color(0xF8D7DA);
    public static final Color WARNING       = new Color(0xFFC107);
    public static final Color WARNING_LIGHT = new Color(0xFFF3CD);
    public static final Color INFO          = new Color(0x17A2B8);

    // Neutrals
    public static final Color SIDEBAR_BG     = new Color(0x1E293B);
    public static final Color SIDEBAR_HOVER  = new Color(0x334155);
    public static final Color SIDEBAR_ACTIVE = new Color(0x1B5E7B);
    public static final Color SIDEBAR_TEXT   = new Color(0xCBD5E1);

    public static final Color HEADER_BG     = Color.WHITE;
    public static final Color HEADER_BORDER = new Color(0xE2E8F0);

    public static final Color CONTENT_BG  = new Color(0xF8FAFC);
    public static final Color CARD_BG     = Color.WHITE;
    public static final Color CARD_BORDER = new Color(0xE2E8F0);

    public static final Color TEXT_PRIMARY   = new Color(0x1E293B);
    public static final Color TEXT_SECONDARY = new Color(0x64748B);
    public static final Color TEXT_MUTED     = new Color(0x94A3B8);
    public static final Color TEXT_WHITE     = Color.WHITE;

    public static final Color BORDER       = new Color(0xE2E8F0);
    public static final Color BORDER_LIGHT = new Color(0xF1F5F9);

    public static final Color TABLE_HEADER_BG = new Color(0xF1F5F9);
    public static final Color TABLE_ROW_ALT   = new Color(0xF8FAFC);
    public static final Color TABLE_HOVER     = new Color(0xEFF6FF);
    public static final Color TABLE_SELECTED  = new Color(0xDBEAFE);

    // Fonts
    public static final Font FONT_TITLE        = new Font("Segoe UI", Font.BOLD, 24);
    public static final Font FONT_SUBTITLE     = new Font("Segoe UI", Font.BOLD, 18);
    public static final Font FONT_SECTION      = new Font("Segoe UI", Font.BOLD, 15);
    public static final Font FONT_BODY         = new Font("Segoe UI", Font.PLAIN, 14);
    public static final Font FONT_BODY_BOLD    = new Font("Segoe UI", Font.BOLD, 14);
    public static final Font FONT_SMALL        = new Font("Segoe UI", Font.PLAIN, 12);
    public static final Font FONT_SMALL_BOLD   = new Font("Segoe UI", Font.BOLD, 12);
    public static final Font FONT_TABLE_HEADER = new Font("Segoe UI", Font.BOLD, 13);
    public static final Font FONT_TABLE_BODY   = new Font("Segoe UI", Font.PLAIN, 13);
    public static final Font FONT_SIDEBAR      = new Font("Segoe UI", Font.PLAIN, 14);
    public static final Font FONT_SIDEBAR_TITLE = new Font("Segoe UI", Font.BOLD, 16);
    public static final Font FONT_MONO         = new Font("Consolas", Font.PLAIN, 14);

    // Dimensions
    public static final int SIDEBAR_WIDTH  = 240;
    public static final int HEADER_HEIGHT  = 60;
    public static final int BUTTON_HEIGHT  = 36;
    public static final int FIELD_HEIGHT   = 36;
    public static final int TABLE_ROW_HEIGHT = 36;

    // Spacing
    public static final int SP_XS = 4;
    public static final int SP_SM = 8;
    public static final int SP_MD = 16;
    public static final int SP_LG = 24;
    public static final int SP_XL = 32;
}
