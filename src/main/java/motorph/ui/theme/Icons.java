package motorph.ui.theme;

import java.awt.*;
import java.net.URL;
import javax.swing.*;

public final class Icons {
    private Icons() {}

    // Unicode icons for sidebar
    public static final String PROFILE        = "\uD83D\uDC64";
    public static final String EMPLOYEES      = "\uD83D\uDC65";
    public static final String ATTENDANCE     = "\uD83D\uDDD3";
    public static final String PAYROLL        = "\uD83D\uDCB0";
    public static final String LEAVE          = "\uD83D\uDCC4";
    public static final String LEAVE_STATUS   = "\uD83D\uDCCB";
    public static final String LEAVE_APPROVAL = "\u2714";
    public static final String LOGOUT         = "\uD83D\uDEAA";

    public static ImageIcon loadImage(String resourcePath, int width, int height) {
        URL url = Icons.class.getClassLoader().getResource(resourcePath);
        if (url != null) {
            ImageIcon raw = new ImageIcon(url);
            Image scaled = raw.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(scaled);
        }
        return null;
    }

    public static ImageIcon loadLogo(int w, int h) {
        return loadImage("images/motorph_logo.png", w, h);
    }

    public static ImageIcon loadDefaultAvatar(int w, int h) {
        return loadImage("images/default_avatar.png", w, h);
    }
}
