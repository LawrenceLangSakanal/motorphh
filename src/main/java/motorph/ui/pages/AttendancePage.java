package motorph.ui.pages;

import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import motorph.model.AttendanceLog;
import motorph.model.Employee;
import motorph.services.AttendanceService;
import motorph.ui.components.AppTable;
import motorph.ui.components.DateRangePanel;
import motorph.ui.components.StatCard;
import motorph.ui.theme.Theme;
import motorph.ui.theme.UIStyles;

public class AttendancePage extends JPanel {

    private final Employee employee;
    private final AttendanceService attendanceService;
    private DateRangePanel dateRange;
    private AttModel tableModel;

    public AttendancePage(Employee employee, AttendanceService attendanceService) {
        this.employee = employee;
        this.attendanceService = attendanceService;
        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout());
        setBackground(Theme.CONTENT_BG);
        setBorder(UIStyles.pagePadding());

        JPanel top = new JPanel(new BorderLayout());
        top.setOpaque(false);
        top.add(UIStyles.createPageTitle("Attendance"), BorderLayout.NORTH);

        // Stats
        int month = LocalDate.now().getMonthValue();
        int year = LocalDate.now().getYear();
        double hours = attendanceService.getTotalHoursWorked(employee.getEmployeeId(), month, year);
        int days = attendanceService.getDaysPresent(employee.getEmployeeId(), month, year);

        JPanel stats = new JPanel(new FlowLayout(FlowLayout.LEFT, 16, 10));
        stats.setOpaque(false);
        stats.add(new StatCard("Total Hours (This Month)", String.format("%.1f", hours), Theme.PRIMARY));
        stats.add(new StatCard("Days Present", String.valueOf(days), Theme.SUCCESS));
        stats.add(new StatCard("Avg Hours/Day", days > 0 ? String.format("%.1f", hours / days) : "0", Theme.INFO));
        top.add(stats, BorderLayout.CENTER);

        dateRange = new DateRangePanel();
        dateRange.setBorder(BorderFactory.createEmptyBorder(0, 0, 8, 0));
        top.add(dateRange, BorderLayout.SOUTH);
        add(top, BorderLayout.NORTH);

        List<AttendanceLog> data = attendanceService.getRecords(
                employee.getEmployeeId(), dateRange.getFromDate(), dateRange.getToDate());
        tableModel = new AttModel(data);
        AppTable table = new AppTable(tableModel);
        UIStyles.styleTable(table);

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBorder(BorderFactory.createLineBorder(Theme.CARD_BORDER));
        add(scroll, BorderLayout.CENTER);

        dateRange.getFilterButton().addActionListener(e -> {
            tableModel.setData(attendanceService.getRecords(
                    employee.getEmployeeId(), dateRange.getFromDate(), dateRange.getToDate()));
        });
    }

    private static class AttModel extends AbstractTableModel {
        private static final String[] COLS = {"Date", "Time In", "Time Out", "Hours Worked"};
        private List<AttendanceLog> data;

        AttModel(List<AttendanceLog> d) { data = new ArrayList<>(d); }
        void setData(List<AttendanceLog> d) { data = new ArrayList<>(d); fireTableDataChanged(); }

        @Override public int getRowCount() { return data.size(); }
        @Override public int getColumnCount() { return COLS.length; }
        @Override public String getColumnName(int c) { return COLS[c]; }

        @Override
        public Object getValueAt(int r, int c) {
            AttendanceLog a = data.get(r);
            switch (c) {
                case 0: return a.getDate();
                case 1: return a.getTimeIn();
                case 2: return a.getTimeOut();
                case 3: return String.format("%.2f", a.getHoursWorked());
                default: return "";
            }
        }
    }
}
