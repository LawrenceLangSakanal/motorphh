package motorph.ui.components;

import java.awt.*;
import javax.swing.*;
import motorph.model.Employee;
import motorph.services.AttendanceService;
import motorph.ui.theme.Theme;

public class HeaderBar extends JPanel {

    private final Employee employee;
    private final AttendanceService attendanceService;
    private JLabel statusLabel;
    private JLabel timeLabel;
    private AppButton clockInBtn;
    private AppButton clockOutBtn;

    public HeaderBar(Employee employee, AttendanceService attendanceService) {
        this.employee = employee;
        this.attendanceService = attendanceService;
        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout());
        setBackground(Theme.HEADER_BG);
        setPreferredSize(new Dimension(0, Theme.HEADER_HEIGHT));
        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Theme.HEADER_BORDER));

        // Left
        JPanel left = new JPanel(new FlowLayout(FlowLayout.LEFT, 16, 0));
        left.setOpaque(false);
        left.setBorder(BorderFactory.createEmptyBorder(0, 8, 0, 0));
        JLabel welcome = new JLabel("Welcome, " + employee.getFirstName());
        welcome.setFont(Theme.FONT_SUBTITLE);
        welcome.setForeground(Theme.TEXT_PRIMARY);
        left.add(welcome);
        add(left, BorderLayout.WEST);

        // Right — clock controls
        JPanel right = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 12));
        right.setOpaque(false);

        statusLabel = new JLabel("Status: OUT");
        statusLabel.setFont(Theme.FONT_BODY_BOLD);
        statusLabel.setForeground(Theme.DANGER);
        right.add(statusLabel);

        timeLabel = new JLabel("");
        timeLabel.setFont(Theme.FONT_SMALL);
        timeLabel.setForeground(Theme.TEXT_SECONDARY);
        right.add(timeLabel);

        right.add(Box.createHorizontalStrut(4));

        clockInBtn = new AppButton("\u25B6 Clock In", AppButton.Style.SUCCESS);
        clockInBtn.setPreferredSize(new Dimension(110, 32));
        clockInBtn.addActionListener(e -> { attendanceService.clockIn(employee.getEmployeeId()); refresh(); });
        right.add(clockInBtn);

        clockOutBtn = new AppButton("\u25A0 Clock Out", AppButton.Style.DANGER);
        clockOutBtn.setPreferredSize(new Dimension(115, 32));
        clockOutBtn.setEnabled(false);
        clockOutBtn.addActionListener(e -> { attendanceService.clockOut(employee.getEmployeeId()); refresh(); });
        right.add(clockOutBtn);

        add(right, BorderLayout.EAST);
        refresh();
    }

    private void refresh() {
        boolean in = attendanceService.isClockedIn(employee.getEmployeeId());
        if (in) {
            statusLabel.setText("Status: IN");
            statusLabel.setForeground(Theme.SUCCESS);
            timeLabel.setText("Clocked in: " + attendanceService.getLastClockIn(employee.getEmployeeId()));
            clockInBtn.setEnabled(false);
            clockOutBtn.setEnabled(true);
        } else {
            statusLabel.setText("Status: OUT");
            statusLabel.setForeground(Theme.DANGER);
            String out = attendanceService.getLastClockOut(employee.getEmployeeId());
            timeLabel.setText(out.isEmpty() ? "" : "Clocked out: " + out);
            clockInBtn.setEnabled(true);
            clockOutBtn.setEnabled(false);
        }
    }
}
