package motorph;

import javax.swing.*;
import motorph.data.AttendanceRepository;
import motorph.data.EmployeeRepository;
import motorph.data.LeaveRepository;
import motorph.ui.pages.LoginPage;

public class App {

    private static EmployeeRepository employeeRepo;
    private static AttendanceRepository attendanceRepo;
    private static LeaveRepository leaveRepo;

    public static void main(String[] args) {
        // Set look-and-feel
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); }
            catch (Exception ignored) {}
        }

        // Initialise shared repositories (preserved across login/logout cycles)
        employeeRepo = new EmployeeRepository();
        attendanceRepo = new AttendanceRepository();
        leaveRepo = new LeaveRepository();

        SwingUtilities.invokeLater(App::showLogin);
    }

    public static void showLogin() {
        new LoginPage(employeeRepo, attendanceRepo, leaveRepo).setVisible(true);
    }
}
