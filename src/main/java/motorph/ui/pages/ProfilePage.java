package motorph.ui.pages;

import java.awt.*;
import javax.swing.*;
import motorph.model.Employee;
import motorph.services.PhotoService;
import motorph.ui.components.AvatarPanel;
import motorph.ui.components.FormRow;
import motorph.ui.theme.Theme;
import motorph.ui.theme.UIStyles;
import motorph.util.FormatUtil;

public class ProfilePage extends JPanel {

    public ProfilePage(Employee employee, PhotoService photoService) {
        setLayout(new BorderLayout());
        setBackground(Theme.CONTENT_BG);
        setBorder(UIStyles.pagePadding());

        add(UIStyles.createPageTitle("My Profile"), BorderLayout.NORTH);

        JPanel body = new JPanel(new BorderLayout(24, 0));
        body.setOpaque(false);
        body.setBorder(BorderFactory.createEmptyBorder(16, 0, 0, 0));

        // Left — avatar + name
        JPanel left = new JPanel();
        left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
        left.setOpaque(false);
        left.setPreferredSize(new Dimension(160, 0));
        left.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        left.add(new AvatarPanel(employee.getEmployeeId(), photoService));
        left.add(Box.createVerticalStrut(12));

        JLabel name = new JLabel(employee.getFullName());
        name.setFont(Theme.FONT_SUBTITLE);
        name.setForeground(Theme.TEXT_PRIMARY);
        name.setAlignmentX(CENTER_ALIGNMENT);
        left.add(name);

        JLabel pos = new JLabel(employee.getPosition());
        pos.setFont(Theme.FONT_SMALL);
        pos.setForeground(Theme.TEXT_SECONDARY);
        pos.setAlignmentX(CENTER_ALIGNMENT);
        left.add(pos);

        body.add(left, BorderLayout.WEST);

        // Right — details card
        JPanel card = UIStyles.createCard();
        card.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 0, 6, 12);
        gbc.anchor = GridBagConstraints.WEST;

        int r = 0;
        FormRow.addSection(card, gbc, r++, "Personal Information");
        FormRow.addReadOnly(card, gbc, r++, "Employee ID", String.valueOf(employee.getEmployeeId()));
        FormRow.addReadOnly(card, gbc, r++, "Full Name", employee.getFullName());
        FormRow.addReadOnly(card, gbc, r++, "Birthday", employee.getBirthday());
        FormRow.addReadOnly(card, gbc, r++, "Address", employee.getAddress());
        FormRow.addReadOnly(card, gbc, r++, "Phone", employee.getPhoneNumber());

        FormRow.addSection(card, gbc, r++, "Employment Details");
        FormRow.addReadOnly(card, gbc, r++, "Status", employee.getStatus());
        FormRow.addReadOnly(card, gbc, r++, "Position", employee.getPosition());
        FormRow.addReadOnly(card, gbc, r++, "Supervisor", employee.getImmediateSupervisor());

        FormRow.addSection(card, gbc, r++, "Government IDs");
        FormRow.addReadOnly(card, gbc, r++, "SSS #", employee.getSssNumber());
        FormRow.addReadOnly(card, gbc, r++, "PhilHealth #", employee.getPhilhealthNumber());
        FormRow.addReadOnly(card, gbc, r++, "TIN #", employee.getTinNumber());
        FormRow.addReadOnly(card, gbc, r++, "Pag-IBIG #", employee.getPagibigNumber());

        FormRow.addSection(card, gbc, r++, "Compensation");
        FormRow.addReadOnly(card, gbc, r++, "Basic Salary", FormatUtil.currency(employee.getBasicSalary()));
        FormRow.addReadOnly(card, gbc, r++, "Rice Subsidy", FormatUtil.currency(employee.getRiceSubsidy()));
        FormRow.addReadOnly(card, gbc, r++, "Phone Allowance", FormatUtil.currency(employee.getPhoneAllowance()));
        FormRow.addReadOnly(card, gbc, r++, "Clothing Allowance", FormatUtil.currency(employee.getClothingAllowance()));
        FormRow.addReadOnly(card, gbc, r++, "Gross Semi-Monthly Rate", FormatUtil.currency(employee.getGrossSemiMonthlyRate()));
        FormRow.addReadOnly(card, gbc, r++, "Hourly Rate", FormatUtil.currency(employee.getHourlyRate()));

        FormRow.addSection(card, gbc, r++, "Monthly Deductions");
        FormRow.addReadOnly(card, gbc, r++, "SSS", FormatUtil.currency(employee.getSssDeduction()));
        FormRow.addReadOnly(card, gbc, r++, "PhilHealth", FormatUtil.currency(employee.getPhilhealthDeduction()));
        FormRow.addReadOnly(card, gbc, r++, "Pag-IBIG", FormatUtil.currency(employee.getPagibigDeduction()));
        FormRow.addReadOnly(card, gbc, r++, "Tax", FormatUtil.currency(employee.getTaxDeduction()));

        JScrollPane scroll = new JScrollPane(card);
        scroll.setBorder(null);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        body.add(scroll, BorderLayout.CENTER);

        add(body, BorderLayout.CENTER);
    }
}
