package motorph.ui.components;

import java.awt.*;
import javax.swing.*;
import motorph.ui.theme.Theme;

public class StatCard extends JPanel {

    public StatCard(String title, String value, Color accentColor) {
        setLayout(new BorderLayout());
        setBackground(Theme.CARD_BG);
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Theme.CARD_BORDER),
                BorderFactory.createEmptyBorder(0, 0, 0, 0)));
        setPreferredSize(new Dimension(200, 90));

        JPanel accent = new JPanel();
        accent.setBackground(accentColor);
        accent.setPreferredSize(new Dimension(0, 3));
        add(accent, BorderLayout.NORTH);

        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setOpaque(false);
        content.setBorder(BorderFactory.createEmptyBorder(12, 16, 12, 16));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(Theme.FONT_SMALL);
        titleLabel.setForeground(Theme.TEXT_SECONDARY);
        titleLabel.setAlignmentX(LEFT_ALIGNMENT);
        content.add(titleLabel);
        content.add(Box.createVerticalStrut(4));

        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(Theme.FONT_TITLE);
        valueLabel.setForeground(Theme.TEXT_PRIMARY);
        valueLabel.setAlignmentX(LEFT_ALIGNMENT);
        content.add(valueLabel);

        add(content, BorderLayout.CENTER);
    }
}
