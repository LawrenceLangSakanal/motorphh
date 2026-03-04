package motorph.ui.components;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import motorph.ui.theme.Theme;

public class AppButton extends JButton {

    public enum Style { PRIMARY, SECONDARY, DANGER, SUCCESS }

    private Color baseColor;
    private Color hoverColor;

    public AppButton(String text) {
        this(text, Style.PRIMARY);
    }

    public AppButton(String text, Style style) {
        super(text);
        applyStyle(style);
        setFont(Theme.FONT_BODY_BOLD);
        setFocusPainted(false);
        setBorderPainted(false);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setPreferredSize(new Dimension(getPreferredSize().width + 24, Theme.BUTTON_HEIGHT));

        addMouseListener(new MouseAdapter() {
            @Override public void mouseEntered(MouseEvent e) { if (isEnabled()) setBackground(hoverColor); }
            @Override public void mouseExited(MouseEvent e)  { if (isEnabled()) setBackground(baseColor); }
            @Override public void mousePressed(MouseEvent e) { if (isEnabled()) setBackground(hoverColor.darker()); }
            @Override public void mouseReleased(MouseEvent e){ if (isEnabled()) setBackground(hoverColor); }
        });
    }

    private void applyStyle(Style style) {
        switch (style) {
            case PRIMARY:   baseColor = Theme.PRIMARY;  hoverColor = Theme.PRIMARY_DARK; break;
            case SECONDARY: baseColor = new Color(0x6C757D); hoverColor = new Color(0x5A6268); break;
            case DANGER:    baseColor = Theme.DANGER;   hoverColor = Theme.DANGER.darker(); break;
            case SUCCESS:   baseColor = Theme.SUCCESS;  hoverColor = Theme.SUCCESS.darker(); break;
        }
        setBackground(baseColor);
        setForeground(Color.WHITE);
    }
}
