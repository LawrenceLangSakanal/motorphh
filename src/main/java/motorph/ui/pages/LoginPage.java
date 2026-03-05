package motorph.ui.pages;

import java.awt.*;
import java.util.Optional;
import javax.swing.*;
import motorph.data.AttendanceRepository;
import motorph.data.EmployeeRepository;
import motorph.data.LeaveRepository;
import motorph.model.Employee;
import motorph.services.*;
import motorph.ui.components.AppButton;
import motorph.ui.theme.Icons;
import motorph.ui.theme.Theme;
import motorph.ui.theme.UIStyles;

public class LoginPage extends JFrame {

    private final EmployeeRepository employeeRepo;
    private final AttendanceRepository attendanceRepo;
    private final LeaveRepository leaveRepo;

    private JTextField idField;
    private JPasswordField passwordField;
    private JLabel statusLabel;

    public LoginPage(EmployeeRepository employeeRepo, AttendanceRepository attendanceRepo, LeaveRepository leaveRepo) {
        this.employeeRepo = employeeRepo;
        this.attendanceRepo = attendanceRepo;
        this.leaveRepo = leaveRepo;
        initUI();
    }

    private void initUI() {
        setTitle("MotorPH Payroll System");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(440, 540);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel main = new JPanel(new BorderLayout());
        main.setBackground(Theme.CONTENT_BG);

        // Header
        JPanel header = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 18));
        header.setBackground(Theme.SIDEBAR_BG);
        header.setPreferredSize(new Dimension(440, 80));
        ImageIcon logo = Icons.loadLogo(44, 44);
        if (logo != null) header.add(new JLabel(logo));
        JLabel title = new JLabel("MotorPH Payroll");
        title.setFont(Theme.FONT_TITLE);
        title.setForeground(Color.WHITE);
        header.add(title);
        main.add(header, BorderLayout.NORTH);

        // Form card
        JPanel center = new JPanel(new GridBagLayout());
        center.setBackground(Theme.CONTENT_BG);

        JPanel card = new JPanel(new GridBagLayout());
        card.setBackground(Theme.CARD_BG);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Theme.CARD_BORDER),
                BorderFactory.createEmptyBorder(30, 40, 30, 40)));
        card.setPreferredSize(new Dimension(360, 340));

        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(6, 8, 6, 8);
        g.fill = GridBagConstraints.HORIZONTAL;
        g.gridwidth = 2;
        g.gridx = 0;

        g.gridy = 0;
        JLabel loginTitle = new JLabel("Sign In", SwingConstants.CENTER);
        loginTitle.setFont(Theme.FONT_SUBTITLE);
        loginTitle.setForeground(Theme.TEXT_PRIMARY);
        card.add(loginTitle, g);

        g.gridy = 1;
        JLabel sub = new JLabel("Enter your credentials to access the system", SwingConstants.CENTER);
        sub.setFont(Theme.FONT_SMALL);
        sub.setForeground(Theme.TEXT_SECONDARY);
        card.add(sub, g);

        g.gridy = 2;
        g.insets = new Insets(12, 8, 2, 8);
        JLabel idLbl = new JLabel("Employee ID");
        idLbl.setFont(Theme.FONT_BODY_BOLD);
        card.add(idLbl, g);

        g.gridy = 3;
        g.insets = new Insets(2, 8, 6, 8);
        idField = UIStyles.createTextField(20);
        card.add(idField, g);

        g.gridy = 4;
        g.insets = new Insets(8, 8, 2, 8);
        JLabel pwLbl = new JLabel("Password");
        pwLbl.setFont(Theme.FONT_BODY_BOLD);
        card.add(pwLbl, g);

        g.gridy = 5;
        g.insets = new Insets(2, 8, 6, 8);
        passwordField = UIStyles.createPasswordField(20);
        card.add(passwordField, g);

        g.gridy = 6;
        g.insets = new Insets(16, 8, 6, 8);
        AppButton loginBtn = new AppButton("Sign In");
        loginBtn.setPreferredSize(new Dimension(0, 40));
        card.add(loginBtn, g);

        g.gridy = 7;
        g.insets = new Insets(4, 8, 4, 8);
        statusLabel = new JLabel(" ", SwingConstants.CENTER);
        statusLabel.setFont(Theme.FONT_SMALL);
        statusLabel.setForeground(Theme.DANGER);
        card.add(statusLabel, g);

        center.add(card);
        main.add(center, BorderLayout.CENTER);

        // Footer hint
        JLabel hint = new JLabel("Hint: Employee ID = your login ID, Password = your employee ID number (e.g. 10001)", SwingConstants.CENTER);
        hint.setFont(Theme.FONT_SMALL);
        hint.setForeground(Theme.TEXT_MUTED);
        hint.setBorder(BorderFactory.createEmptyBorder(0, 10, 12, 10));
        main.add(hint, BorderLayout.SOUTH);

        setContentPane(main);

        loginBtn.addActionListener(e -> doLogin());
        passwordField.addActionListener(e -> doLogin());
        idField.addActionListener(e -> passwordField.requestFocus());
    }

    private void doLogin() {
        String idText = idField.getText().trim();
        String pw = new String(passwordField.getPassword()).trim();

        if (idText.isEmpty() || pw.isEmpty()) {
            statusLabel.setText("Please enter Employee ID and Password.");
            return;
        }

        int empId;
        try {
            empId = Integer.parseInt(idText);
        } catch (NumberFormatException e) {
            statusLabel.setText("Employee ID must be a number.");
            return;
        }

        Optional<Employee> result = employeeRepo.authenticate(empId, pw);
        if (result.isPresent()) {
            Employee emp = result.get();
            motorph.util.AppContext.getInstance().setCurrentEmployee(emp);
            RoleService roleService = new RoleService();
            AttendanceService attendanceService = new AttendanceService(attendanceRepo);
            LeaveService leaveService = new LeaveService(leaveRepo);
            PhotoService photoService = new PhotoService();
            SalaryCalculationService salaryCalcService = new SalaryCalculationService();
            GovernmentDeductionService deductionService = new GovernmentDeductionService();
            PayrollService payrollService = new PayrollService(attendanceService, salaryCalcService, deductionService);

            SwingUtilities.invokeLater(() -> {
                new HomePage(emp, employeeRepo, roleService, attendanceService,
                        payrollService, leaveService, photoService).setVisible(true);
                dispose();
            });
        } else {
            statusLabel.setText("Invalid Employee ID or Password.");
            passwordField.setText("");
        }
    }
}
