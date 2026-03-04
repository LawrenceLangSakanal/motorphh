package motorph.ui.components;

import motorph.model.Employee;
import motorph.model.Role;
import motorph.ui.router.AppRouter;
import motorph.ui.theme.Icons;
import motorph.ui.theme.Theme;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class Sidebar extends JPanel {

    private final List<JButton> navButtons = new ArrayList<>();
    private JButton activeButton;

    public Sidebar(Employee employee, Role role, AppRouter router) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Theme.SIDEBAR_BG);
        setPreferredSize(new Dimension(Theme.SIDEBAR_WIDTH, 0));

        // Logo area
        JPanel logoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 15));
        logoPanel.setBackground(Theme.SIDEBAR_BG);
        logoPanel.setMaximumSize(new Dimension(Theme.SIDEBAR_WIDTH, 80));
        ImageIcon logo = Icons.loadLogo(36, 36);
        if (logo != null) logoPanel.add(new JLabel(logo));
        JLabel appName = new JLabel("MotorPH");
        appName.setFont(Theme.FONT_SIDEBAR_TITLE);
        appName.setForeground(Color.WHITE);
        logoPanel.add(appName);
        add(logoPanel);

        JSeparator sep = new JSeparator();
        sep.setForeground(Theme.SIDEBAR_HOVER);
        sep.setMaximumSize(new Dimension(Theme.SIDEBAR_WIDTH, 1));
        add(sep);
        add(Box.createVerticalStrut(10));

        // Navigation — role-based
        addNav(Icons.PROFILE + "  Profile", "PROFILE", router);
        addNav(Icons.ATTENDANCE + "  Attendance", "ATTENDANCE", router);
        addNav(Icons.PAYROLL + "  Payroll", "PAYROLL", router);

        if (role == Role.HR || role == Role.ADMIN) {
            addNav(Icons.EMPLOYEES + "  Employees", "EMPLOYEES", router);
        }

        addNav(Icons.LEAVE + "  Leave Request", "LEAVE_REQUEST", router);
        addNav(Icons.LEAVE_STATUS + "  Leave Status", "LEAVE_STATUS", router);

        if (role == Role.HR || role == Role.ADMIN || role == Role.MANAGER) {
            addNav(Icons.LEAVE_APPROVAL + "  Leave Approval", "LEAVE_APPROVAL", router);
        }

        add(Box.createVerticalGlue());

        // User info
        JPanel userPanel = new JPanel();
        userPanel.setLayout(new BoxLayout(userPanel, BoxLayout.Y_AXIS));
        userPanel.setBackground(Theme.SIDEBAR_BG);
        userPanel.setBorder(BorderFactory.createEmptyBorder(10, 16, 5, 16));
        userPanel.setMaximumSize(new Dimension(Theme.SIDEBAR_WIDTH, 50));

        JLabel nameLabel = new JLabel(employee.getFullName());
        nameLabel.setFont(Theme.FONT_SMALL_BOLD);
        nameLabel.setForeground(Theme.SIDEBAR_TEXT);
        nameLabel.setAlignmentX(LEFT_ALIGNMENT);
        userPanel.add(nameLabel);

        JLabel roleLabel = new JLabel(role.name());
        roleLabel.setFont(Theme.FONT_SMALL);
        roleLabel.setForeground(Theme.TEXT_MUTED);
        roleLabel.setAlignmentX(LEFT_ALIGNMENT);
        userPanel.add(roleLabel);
        add(userPanel);

        add(Box.createVerticalStrut(5));
        addNav(Icons.LOGOUT + "  Logout", "LOGOUT", router);
        add(Box.createVerticalStrut(10));

        if (!navButtons.isEmpty()) setActive(navButtons.get(0));
    }

    private void addNav(String text, String page, AppRouter router) {
        JButton btn = new JButton(text);
        btn.setFont(Theme.FONT_SIDEBAR);
        btn.setForeground(Theme.SIDEBAR_TEXT);
        btn.setBackground(Theme.SIDEBAR_BG);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setMaximumSize(new Dimension(Theme.SIDEBAR_WIDTH, 44));
        btn.setPreferredSize(new Dimension(Theme.SIDEBAR_WIDTH, 44));
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 10));

        btn.addMouseListener(new MouseAdapter() {
            @Override public void mouseEntered(MouseEvent e) { if (btn != activeButton) btn.setBackground(Theme.SIDEBAR_HOVER); }
            @Override public void mouseExited(MouseEvent e) { if (btn != activeButton) btn.setBackground(Theme.SIDEBAR_BG); }
        });

        btn.addActionListener(e -> {
            if ("LOGOUT".equals(page)) {
                router.logout();
            } else {
                setActive(btn);
                router.navigateTo(page);
            }
        });

        add(btn);
        navButtons.add(btn);
    }

    private void setActive(JButton btn) {
        if (activeButton != null) {
            activeButton.setBackground(Theme.SIDEBAR_BG);
            activeButton.setForeground(Theme.SIDEBAR_TEXT);
        }
        activeButton = btn;
        activeButton.setBackground(Theme.SIDEBAR_ACTIVE);
        activeButton.setForeground(Color.WHITE);
    }
}
