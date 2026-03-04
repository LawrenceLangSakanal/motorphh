package motorph.ui.theme;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

public final class UIStyles {
    private UIStyles() {}

    public static Border cardBorder() {
        return BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Theme.CARD_BORDER, 1),
                BorderFactory.createEmptyBorder(Theme.SP_LG, Theme.SP_LG, Theme.SP_LG, Theme.SP_LG));
    }

    public static Border pagePadding() {
        return BorderFactory.createEmptyBorder(Theme.SP_LG, Theme.SP_LG, Theme.SP_LG, Theme.SP_LG);
    }

    public static JPanel createCard() {
        JPanel card = new JPanel();
        card.setBackground(Theme.CARD_BG);
        card.setBorder(cardBorder());
        return card;
    }

    public static JLabel createPageTitle(String text) {
        JLabel label = new JLabel(text);
        label.setFont(Theme.FONT_TITLE);
        label.setForeground(Theme.TEXT_PRIMARY);
        return label;
    }

    public static JLabel createSectionTitle(String text) {
        JLabel label = new JLabel(text);
        label.setFont(Theme.FONT_SECTION);
        label.setForeground(Theme.PRIMARY);
        label.setBorder(BorderFactory.createEmptyBorder(Theme.SP_MD, 0, Theme.SP_SM, 0));
        return label;
    }

    public static JTextField createTextField(int columns) {
        JTextField field = new JTextField(columns);
        field.setFont(Theme.FONT_BODY);
        field.setPreferredSize(new Dimension(field.getPreferredSize().width, Theme.FIELD_HEIGHT));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Theme.BORDER),
                BorderFactory.createEmptyBorder(4, 8, 4, 8)));
        return field;
    }

    public static JPasswordField createPasswordField(int columns) {
        JPasswordField field = new JPasswordField(columns);
        field.setFont(Theme.FONT_BODY);
        field.setPreferredSize(new Dimension(field.getPreferredSize().width, Theme.FIELD_HEIGHT));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Theme.BORDER),
                BorderFactory.createEmptyBorder(4, 8, 4, 8)));
        return field;
    }

    public static void styleTable(JTable table) {
        table.setFont(Theme.FONT_TABLE_BODY);
        table.setRowHeight(Theme.TABLE_ROW_HEIGHT);
        table.setSelectionBackground(Theme.TABLE_SELECTED);
        table.setSelectionForeground(Theme.TEXT_PRIMARY);
        table.setGridColor(Theme.BORDER_LIGHT);
        table.setShowHorizontalLines(true);
        table.setShowVerticalLines(false);
        table.setIntercellSpacing(new Dimension(0, 1));
        table.setFillsViewportHeight(true);

        JTableHeader header = table.getTableHeader();
        header.setFont(Theme.FONT_TABLE_HEADER);
        header.setBackground(Theme.TABLE_HEADER_BG);
        header.setForeground(Theme.TEXT_PRIMARY);
        header.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Theme.BORDER));
        header.setPreferredSize(new Dimension(header.getPreferredSize().width, 40));

        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable t, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(t, value, isSelected, hasFocus, row, column);
                if (!isSelected) {
                    c.setBackground(row % 2 == 0 ? Theme.CARD_BG : Theme.TABLE_ROW_ALT);
                }
                setBorder(BorderFactory.createEmptyBorder(0, 12, 0, 12));
                return c;
            }
        };
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }
    }

    @SuppressWarnings("unchecked")
    public static JComboBox<String> createComboBox(String[] items) {
        JComboBox<String> combo = new JComboBox<>(items);
        combo.setFont(Theme.FONT_BODY);
        combo.setPreferredSize(new Dimension(combo.getPreferredSize().width, Theme.FIELD_HEIGHT));
        return combo;
    }
}
