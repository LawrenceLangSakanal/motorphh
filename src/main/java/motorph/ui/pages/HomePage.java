package motorph.ui.pages;

import motorph.App;
import motorph.data.EmployeeRepository;
import motorph.model.Employee;
import motorph.model.Role;
import motorph.services.*;
import motorph.ui.components.HeaderBar;
import motorph.ui.components.Sidebar;
import motorph.ui.router.AppRouter;
import motorph.ui.theme.Theme;

import javax.swing.*;
import java.awt.*;

public class HomePage extends JFrame {

    public HomePage(Employee employee, EmployeeRepository employeeRepo,
                    RoleService roleService, AttendanceService attendanceService,
                    PayrollService payrollService, LeaveService leaveService,
                    PhotoService photoService) {

        setTitle("MotorPH Payroll System");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1200, 750);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(1000, 650));

        Role role = roleService.determineRole(employee);
        AppRouter router = new AppRouter();
        router.setLogoutAction(() -> {
            int c = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to logout?", "Logout",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (c == JOptionPane.YES_OPTION) {
                dispose();
                SwingUtilities.invokeLater(App::showLogin);
            }
        });

        JPanel root = new JPanel(new BorderLayout());
        root.add(new Sidebar(employee, role, router), BorderLayout.WEST);

        JPanel right = new JPanel(new BorderLayout());
        right.add(new HeaderBar(employee, attendanceService), BorderLayout.NORTH);

        JPanel content = router.getContentPanel();
        content.setBackground(Theme.CONTENT_BG);
        right.add(content, BorderLayout.CENTER);
        root.add(right, BorderLayout.CENTER);

        // Register pages
        router.addPage("PROFILE", new ProfilePage(employee, photoService));
        router.addPage("ATTENDANCE", new AttendancePage(employee, attendanceService));
        router.addPage("PAYROLL", new PayrollPage(employee, employeeRepo, payrollService, role));

        if (role == Role.HR || role == Role.ADMIN) {
            router.addPage("EMPLOYEES", new EmployeeManagementPage(employeeRepo));
        }

        router.addPage("LEAVE_REQUEST", new LeaveRequestPage(employee, leaveService));
        router.addPage("LEAVE_STATUS", new LeaveStatusPage(employee, leaveService, role));

        if (role == Role.HR || role == Role.ADMIN || role == Role.MANAGER) {
            router.addPage("LEAVE_APPROVAL", new LeaveApprovalPage(employee, leaveService, employeeRepo, role));
        }

        router.navigateTo("PROFILE");
        setContentPane(root);
    }
}
