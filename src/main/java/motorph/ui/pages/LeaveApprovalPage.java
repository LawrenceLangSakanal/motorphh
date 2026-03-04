package motorph.ui.pages;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import motorph.data.EmployeeRepository;
import motorph.model.Employee;
import motorph.model.LeaveRequest;
import motorph.model.Role;
import motorph.services.LeaveService;
import motorph.ui.components.AppButton;
import motorph.ui.components.AppTable;
import motorph.ui.theme.Theme;
import motorph.ui.theme.UIStyles;

public class LeaveApprovalPage extends JPanel {

    private final Employee employee;
    private final LeaveService leaveService;
    private final EmployeeRepository employeeRepo;
    private final Role role;
    private final PModel tableModel;
    private AppTable table;

    public LeaveApprovalPage(Employee employee, LeaveService leaveService,
                             EmployeeRepository employeeRepo, Role role) {
        this.employee = employee;
        this.leaveService = leaveService;
        this.employeeRepo = employeeRepo;
        this.role = role;

        setLayout(new BorderLayout());
        setBackground(Theme.CONTENT_BG);
        setBorder(UIStyles.pagePadding());

        JPanel top = new JPanel(new BorderLayout());
        top.setOpaque(false);
        top.add(UIStyles.createPageTitle("Leave Approval"), BorderLayout.WEST);

        JPanel actions = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        actions.setOpaque(false);

        AppButton approveBtn = new AppButton("Approve", AppButton.Style.SUCCESS);
        approveBtn.addActionListener(e -> decide(true));
        actions.add(approveBtn);

        AppButton rejectBtn = new AppButton("Reject", AppButton.Style.DANGER);
        rejectBtn.addActionListener(e -> decide(false));
        actions.add(rejectBtn);

        top.add(actions, BorderLayout.EAST);
        add(top, BorderLayout.NORTH);

        tableModel = new PModel(loadPending());
        table = new AppTable(tableModel);
        UIStyles.styleTable(table);

        AppButton refreshBtn = new AppButton("Refresh", AppButton.Style.SECONDARY);
        refreshBtn.addActionListener(e -> tableModel.setData(loadPending()));
        actions.add(refreshBtn);

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(12, 0, 0, 0),
                BorderFactory.createLineBorder(Theme.CARD_BORDER)));
        add(scroll, BorderLayout.CENTER);
    }

    private List<LeaveRequest> loadPending() {
        List<LeaveRequest> all = leaveService.getPending();
        if (role == Role.HR || role == Role.ADMIN) return all;

        // Manager sees only their team
        String name = employee.getFullName();
        return all.stream().filter(r -> {
            Optional<Employee> emp = employeeRepo.findById(r.getEmployeeId());
            return emp.isPresent() && name.equals(emp.get().getImmediateSupervisor());
        }).collect(Collectors.toList());
    }

    private void decide(boolean approve) {
        int sel = table.getSelectedRow();
        if (sel < 0) {
            JOptionPane.showMessageDialog(this, "Please select a leave request.", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }
        LeaveRequest req = tableModel.getAt(table.convertRowIndexToModel(sel));
        String action = approve ? "approve" : "reject";
        int c = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to " + action + " this request from " + req.getEmployeeName() + "?",
                "Confirm", JOptionPane.YES_NO_OPTION);
        if (c == JOptionPane.YES_OPTION) {
            if (approve) leaveService.approve(req.getId(), employee.getFullName());
            else leaveService.reject(req.getId(), employee.getFullName());
            tableModel.setData(loadPending());
        }
    }

    private static class PModel extends AbstractTableModel {
        private static final String[] C = {"ID", "Employee", "Type", "Start", "End", "Days", "Reason", "Submitted"};
        private List<LeaveRequest> data;
        PModel(List<LeaveRequest> d) { data = new ArrayList<>(d); }
        void setData(List<LeaveRequest> d) { data = new ArrayList<>(d); fireTableDataChanged(); }
        LeaveRequest getAt(int r) { return data.get(r); }
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
                case 6: return l.getReason();
                case 7: return l.getSubmittedDate();
                default: return "";
            }
        }
    }
}
