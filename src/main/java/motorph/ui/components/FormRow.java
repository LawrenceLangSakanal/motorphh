package motorph.ui.components;

import java.awt.*;
import javax.swing.*;
import motorph.ui.theme.Theme;

public class FormRow {

    public static void add(JPanel panel, GridBagConstraints gbc, int row, String label, JComponent field) {
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.NONE;
        JLabel lbl = new JLabel(label + ":");
        lbl.setFont(Theme.FONT_BODY_BOLD);
        lbl.setForeground(Theme.TEXT_SECONDARY);
        panel.add(lbl, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(field, gbc);
    }

    public static void addReadOnly(JPanel panel, GridBagConstraints gbc, int row, String label, String value) {
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.NONE;
        JLabel lbl = new JLabel(label + ":");
        lbl.setFont(Theme.FONT_BODY_BOLD);
        lbl.setForeground(Theme.TEXT_SECONDARY);
        panel.add(lbl, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JLabel val = new JLabel(value == null || value.isEmpty() ? "\u2014" : value);
        val.setFont(Theme.FONT_BODY);
        val.setForeground(Theme.TEXT_PRIMARY);
        panel.add(val, gbc);
    }

    public static void addSection(JPanel panel, GridBagConstraints gbc, int row, String title) {
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(16, 0, 8, 0);

        JLabel lbl = new JLabel(title);
        lbl.setFont(Theme.FONT_SECTION);
        lbl.setForeground(Theme.PRIMARY);
        lbl.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Theme.BORDER_LIGHT));
        panel.add(lbl, gbc);

        gbc.gridwidth = 1;
        gbc.insets = new Insets(6, 0, 6, 12);
    }
}
