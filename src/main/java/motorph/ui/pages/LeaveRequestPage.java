package motorph.ui.pages;

import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import javax.swing.*;
import motorph.model.Employee;
import motorph.model.LeaveRequest;
import motorph.services.LeaveService;
import motorph.ui.components.AppButton;
import motorph.ui.components.FormRow;
import motorph.ui.theme.Theme;
import motorph.ui.theme.UIStyles;

public class LeaveRequestPage extends JPanel {

    private final Employee employee;
    private final LeaveService leaveService;
    private JComboBox<String> typeCombo;
    private JTextField startField, endField;
    private JTextArea reasonArea;

    public LeaveRequestPage(Employee employee, LeaveService leaveService) {
        this.employee = employee;
        this.leaveService = leaveService;
        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout());
        setBackground(Theme.CONTENT_BG);
        setBorder(UIStyles.pagePadding());
        add(UIStyles.createPageTitle("Leave Request"), BorderLayout.NORTH);

        JPanel card = UIStyles.createCard();
        card.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 0, 8, 16);
        gbc.anchor = GridBagConstraints.WEST;

        int row = 0;
        FormRow.addSection(card, gbc, row++, "Submit Leave Request");

        String[] types = {"Sick Leave", "Vacation Leave", "Emergency Leave", "Maternity/Paternity Leave", "Other"};
        typeCombo = UIStyles.createComboBox(types);
        FormRow.add(card, gbc, row++, "Leave Type", typeCombo);

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        startField = UIStyles.createTextField(15);
        startField.setText(LocalDate.now().format(fmt));
        FormRow.add(card, gbc, row++, "Start Date (MM/dd/yyyy)", startField);

        endField = UIStyles.createTextField(15);
        endField.setText(LocalDate.now().plusDays(1).format(fmt));
        FormRow.add(card, gbc, row++, "End Date (MM/dd/yyyy)", endField);

        reasonArea = new JTextArea(4, 30);
        reasonArea.setFont(Theme.FONT_BODY);
        reasonArea.setLineWrap(true);
        reasonArea.setWrapStyleWord(true);
        reasonArea.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Theme.BORDER),
                BorderFactory.createEmptyBorder(6, 8, 6, 8)));
        FormRow.add(card, gbc, row++, "Reason", new JScrollPane(reasonArea));

        gbc.gridx = 1;
        gbc.gridy = row;
        gbc.fill = GridBagConstraints.NONE;
        AppButton submitBtn = new AppButton("Submit Request");
        submitBtn.addActionListener(e -> submit());
        card.add(submitBtn, gbc);

        JPanel wrapper = new JPanel(new FlowLayout(FlowLayout.LEFT));
        wrapper.setOpaque(false);
        wrapper.setBorder(BorderFactory.createEmptyBorder(16, 0, 0, 0));
        wrapper.add(card);
        add(wrapper, BorderLayout.CENTER);
    }

    private void submit() {
        String type = (String) typeCombo.getSelectedItem();
        String start = startField.getText().trim();
        String end = endField.getText().trim();
        String reason = reasonArea.getText().trim();

        if (start.isEmpty() || end.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all date fields.", "Validation", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (reason.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please provide a reason.", "Validation", JOptionPane.WARNING_MESSAGE);
            return;
        }

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        int days;
        try {
            LocalDate s = LocalDate.parse(start, fmt);
            LocalDate e = LocalDate.parse(end, fmt);
            days = (int) ChronoUnit.DAYS.between(s, e) + 1;
            if (days <= 0) {
                JOptionPane.showMessageDialog(this, "End date must be on or after start date.", "Validation", JOptionPane.WARNING_MESSAGE);
                return;
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid date format. Use MM/dd/yyyy.", "Validation", JOptionPane.WARNING_MESSAGE);
            return;
        }

        LeaveRequest req = new LeaveRequest();
        req.setEmployeeId(employee.getEmployeeId());
        req.setEmployeeName(employee.getFullName());
        req.setLeaveType(type);
        req.setStartDate(start);
        req.setEndDate(end);
        req.setDays(days);
        req.setReason(reason);

        leaveService.submitRequest(req);
        JOptionPane.showMessageDialog(this, "Leave request submitted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

        reasonArea.setText("");
        startField.setText(LocalDate.now().format(fmt));
        endField.setText(LocalDate.now().plusDays(1).format(fmt));
    }
}
