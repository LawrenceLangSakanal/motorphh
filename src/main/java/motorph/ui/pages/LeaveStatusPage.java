package motorph.ui.pages;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import motorph.model.Employee;
import motorph.model.LeaveRequest;
import motorph.model.Role;
import motorph.services.LeaveService;
import motorph.ui.components.AppButton;
import motorph.ui.components.AppTable;
import motorph.ui.theme.Theme;
import motorph.ui.theme.UIStyles;

public class LeaveStatusPage extends JPanel {

    private final Employee employee;
    private final LeaveService leaveService;
    private final Role role;
    private final LModel tableModel;

    public LeaveStatusPage(Employee employee, LeaveService leaveService, Role role) {
        this.employee = employee;
        this.leaveService = leaveService;
        this.role = role;

        setLayout(new BorderLayout());
        setBackground(Theme.CONTENT_BG);
        setBorder(UIStyles.pagePadding());

        JPanel top = new JPanel(new BorderLayout());
        top.setOpaque(false);
        top.add(UIStyles.createPageTitle("Leave Status"), BorderLayout.WEST);

        add(top, BorderLayout.NORTH);

        tableModel = new LModel(loadData());

        AppButton refresh = new AppButton("Refresh", AppButton.Style.SECONDARY);
        refresh.addActionListener(e -> tableModel.setData(loadData()));
        JPanel rp = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rp.setOpaque(false);
        rp.add(refresh);
        top.add(rp, BorderLayout.EAST);
        AppTable table = new AppTable(tableModel);
        UIStyles.styleTable(table);

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(12, 0, 0, 0),
                BorderFactory.createLineBorder(Theme.CARD_BORDER)));
        add(scroll, BorderLayout.CENTER);
    }

    private List<LeaveRequest> loadData() {
        return (role == Role.HR || role == Role.ADMIN)
                ? leaveService.getAll()
                : leaveService.getByEmployee(employee.getEmployeeId());
    }

    private static class LModel extends AbstractTableModel {
        private static final String[] C = {"ID", "Employee", "Type", "Start", "End", "Days", "Status", "Decided By", "Decision Date"};
        private List<LeaveRequest> data;
        LModel(List<LeaveRequest> d) { data = new ArrayList<>(d); }
        void setData(List<LeaveRequest> d) { data = new ArrayList<>(d); fireTableDataChanged(); }
        @Override public int getRowCount() { return data.size(); }
        @Override public int getColumnCount() { return C.length; }
        @Override public String getColumnName(int c) { return C[c]; }
        @Override
        public Object getValueAt(int r, int c) {
            LeaveRequest l = data.get(r);
            switch (c) {
                case 0: return l.getId();
                case 1: return l.getEmployeeName();
                case 2: return l.getLeaveType();
                case 3: return l.getStartDate();
                case 4: return l.getEndDate();
                case 5: return l.getDays();
                case 6: return l.getStatus();
                case 7: return l.getDecidedBy() != null ? l.getDecidedBy() : "\u2014";
                case 8: return l.getDecisionDate() != null ? l.getDecisionDate() : "\u2014";
                default: return "";
            }
        }
    }
}
