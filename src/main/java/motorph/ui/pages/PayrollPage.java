package motorph.ui.pages;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.time.LocalDate;
import javax.swing.*;
import motorph.data.EmployeeRepository;
import motorph.model.Employee;
import motorph.model.Payslip;
import motorph.model.Role;
import motorph.services.PayrollService;
import motorph.ui.components.AppButton;
import motorph.ui.theme.Theme;
import motorph.ui.theme.UIStyles;

public class PayrollPage extends JPanel {

    private final Employee employee;
    private final EmployeeRepository employeeRepo;
    private final PayrollService payrollService;
    private final Role role;

    private JComboBox<String> monthCombo;
    private JSpinner yearSpinner;
    private JComboBox<EmpItem> employeeCombo;
    private JTextArea outputArea;
    private AppButton copyBtn;

    public PayrollPage(Employee employee, EmployeeRepository employeeRepo,
                       PayrollService payrollService, Role role) {
        this.employee = employee;
        this.employeeRepo = employeeRepo;
        this.payrollService = payrollService;
        this.role = role;
        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout());
        setBackground(Theme.CONTENT_BG);
        setBorder(UIStyles.pagePadding());
        add(UIStyles.createPageTitle("Payroll"), BorderLayout.NORTH);

        JPanel body = new JPanel(new BorderLayout(0, 12));
        body.setOpaque(false);
        body.setBorder(BorderFactory.createEmptyBorder(12, 0, 0, 0));

        // Controls
        JPanel controls = UIStyles.createCard();
        controls.setLayout(new FlowLayout(FlowLayout.LEFT, 12, 8));

        controls.add(lbl("Month:"));
        String[] months = {"January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"};
        monthCombo = UIStyles.createComboBox(months);
        monthCombo.setSelectedIndex(LocalDate.now().getMonthValue() - 1);
        controls.add(monthCombo);

        controls.add(lbl("Year:"));
        yearSpinner = new JSpinner(new SpinnerNumberModel(LocalDate.now().getYear(), 2020, 2030, 1));
        yearSpinner.setFont(Theme.FONT_BODY);
        yearSpinner.setEditor(new JSpinner.NumberEditor(yearSpinner, "#"));
        controls.add(yearSpinner);

        controls.add(lbl("Employee:"));
        employeeCombo = new JComboBox<>();
        employeeCombo.setFont(Theme.FONT_BODY);
        populateEmployees();
        controls.add(employeeCombo);

        AppButton genBtn = new AppButton("Generate Payslip", AppButton.Style.SUCCESS);
        genBtn.addActionListener(e -> generate());
        controls.add(genBtn);

        copyBtn = new AppButton("Copy", AppButton.Style.SECONDARY);
        copyBtn.setEnabled(false);
        copyBtn.addActionListener(e -> {
            Toolkit.getDefaultToolkit().getSystemClipboard()
                    .setContents(new StringSelection(outputArea.getText()), null);
            JOptionPane.showMessageDialog(this, "Copied to clipboard!");
        });
        controls.add(copyBtn);
        body.add(controls, BorderLayout.NORTH);

        outputArea = new JTextArea();
        outputArea.setFont(Theme.FONT_MONO);
        outputArea.setEditable(false);
        outputArea.setBackground(Theme.CARD_BG);
        outputArea.setMargin(new Insets(20, 20, 20, 20));
        outputArea.setText("Select month, year, and employee, then click 'Generate Payslip'.");
        body.add(new JScrollPane(outputArea), BorderLayout.CENTER);

        add(body, BorderLayout.CENTER);
    }

    private JLabel lbl(String t) { JLabel l = new JLabel(t); l.setFont(Theme.FONT_BODY); return l; }

    private void populateEmployees() {
        if (role == Role.ADMIN || role == Role.HR) {
            for (Employee e : employeeRepo.getAll()) {
                EmpItem it = new EmpItem(e);
                employeeCombo.addItem(it);
                if (e.getEmployeeId() == employee.getEmployeeId()) employeeCombo.setSelectedItem(it);
            }
        } else {
            employeeCombo.addItem(new EmpItem(employee));
            employeeCombo.setEnabled(false);
        }
    }

    private void generate() {
        EmpItem sel = (EmpItem) employeeCombo.getSelectedItem();
        if (sel == null) return;
        int month = monthCombo.getSelectedIndex() + 1;
        int year = (int) yearSpinner.getValue();
        Payslip p = payrollService.generatePayslip(sel.emp, month, year);
        outputArea.setText(p.toMultilineString());
        outputArea.setCaretPosition(0);
        copyBtn.setEnabled(true);
    }

    private static class EmpItem {
        final Employee emp;
        EmpItem(Employee e) { emp = e; }
        @Override public String toString() { return emp.getEmployeeId() + " \u2014 " + emp.getFullName(); }
    }
}
