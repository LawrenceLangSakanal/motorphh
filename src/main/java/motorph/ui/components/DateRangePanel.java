package motorph.ui.components;

import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.*;
import motorph.ui.theme.Theme;

public class DateRangePanel extends JPanel {

    private final JTextField fromField;
    private final JTextField toField;
    private final AppButton filterBtn;

    public DateRangePanel() {
        setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));
        setOpaque(false);

        add(label("From:"));
        fromField = field(LocalDate.now().withDayOfMonth(1));
        add(fromField);

        add(label("To:"));
        toField = field(LocalDate.now());
        add(toField);

        filterBtn = new AppButton("Filter", AppButton.Style.PRIMARY);
        filterBtn.setPreferredSize(new Dimension(80, Theme.FIELD_HEIGHT));
        add(filterBtn);
    }

    private JLabel label(String t) {
        JLabel l = new JLabel(t);
        l.setFont(Theme.FONT_BODY);
        return l;
    }

    private JTextField field(LocalDate date) {
        JTextField f = new JTextField(10);
        f.setFont(Theme.FONT_BODY);
        f.setText(date.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        f.setPreferredSize(new Dimension(120, Theme.FIELD_HEIGHT));
        return f;
    }

    public String getFromDate() { return fromField.getText().trim(); }
    public String getToDate()   { return toField.getText().trim(); }
    public AppButton getFilterButton() { return filterBtn; }
}
