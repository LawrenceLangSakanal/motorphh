package motorph.ui.pages;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;
import motorph.data.EmployeeRepository;
import motorph.model.Employee;
import motorph.ui.components.AppTable;
import motorph.ui.components.FormRow;
import motorph.ui.theme.Theme;
import motorph.ui.theme.UIStyles;
import motorph.util.FormatUtil;

public class EmployeeManagementPage extends JPanel {

    private final EmployeeRepository employeeRepo;
    private final EmpModel tableModel;
    private final TableRowSorter<EmpModel> sorter;

    public EmployeeManagementPage(EmployeeRepository employeeRepo) {
        this.employeeRepo = employeeRepo;
        setLayout(new BorderLayout());
        setBackground(Theme.CONTENT_BG);
        setBorder(UIStyles.pagePadding());

        // Top
        JPanel top = new JPanel(new BorderLayout());
        top.setOpaque(false);
        top.add(UIStyles.createPageTitle("Employee Management"), BorderLayout.WEST);

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        searchPanel.setOpaque(false);
        searchPanel.add(lbl("Search:"));
        JTextField searchField = UIStyles.createTextField(20);
        searchPanel.add(searchField);
        top.add(searchPanel, BorderLayout.EAST);
        add(top, BorderLayout.NORTH);

        // Table
        tableModel = new EmpModel(employeeRepo.getAll());
        AppTable table = new AppTable(tableModel);
        UIStyles.styleTable(table);
        sorter = new TableRowSorter<>(tableModel);
        table.setRowSorter(sorter);

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(12, 0, 0, 0),
                BorderFactory.createLineBorder(Theme.CARD_BORDER)));
        add(scroll, BorderLayout.CENTER);

        // Search filter
        searchField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) { doFilter(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { doFilter(); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { doFilter(); }
            private void doFilter() {
                String t = searchField.getText().trim();
                sorter.setRowFilter(t.isEmpty() ? null :
                        RowFilter.regexFilter("(?i)" + java.util.regex.Pattern.quote(t)));
            }
        });

        // Double-click detail
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (e.getClickCount() == 2 && table.getSelectedRow() >= 0) {
                    Employee emp = tableModel.getAt(table.convertRowIndexToModel(table.getSelectedRow()));
                    showDetail(emp);
                }
            }
        });
    }

    private JLabel lbl(String t) { JLabel l = new JLabel(t); l.setFont(Theme.FONT_BODY); return l; }

    private void showDetail(Employee emp) {
        JPanel p = new JPanel(new GridBagLayout());
        p.setBackground(Theme.CARD_BG);
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(4, 0, 4, 12);
        g.anchor = GridBagConstraints.WEST;

        int r = 0;
        FormRow.addSection(p, g, r++, "Personal");
        FormRow.addReadOnly(p, g, r++, "ID", String.valueOf(emp.getEmployeeId()));
        FormRow.addReadOnly(p, g, r++, "Name", emp.getFullName());
        FormRow.addReadOnly(p, g, r++, "Birthday", emp.getBirthday());
        FormRow.addReadOnly(p, g, r++, "Address", emp.getAddress());
        FormRow.addReadOnly(p, g, r++, "Phone", emp.getPhoneNumber());

        FormRow.addSection(p, g, r++, "Employment");
        FormRow.addReadOnly(p, g, r++, "Status", emp.getStatus());
        FormRow.addReadOnly(p, g, r++, "Position", emp.getPosition());
        FormRow.addReadOnly(p, g, r++, "Supervisor", emp.getImmediateSupervisor());

        FormRow.addSection(p, g, r++, "Government IDs");
        FormRow.addReadOnly(p, g, r++, "SSS #", emp.getSssNumber());
        FormRow.addReadOnly(p, g, r++, "PhilHealth #", emp.getPhilhealthNumber());
        FormRow.addReadOnly(p, g, r++, "TIN #", emp.getTinNumber());
        FormRow.addReadOnly(p, g, r++, "Pag-IBIG #", emp.getPagibigNumber());

        FormRow.addSection(p, g, r++, "Compensation");
        FormRow.addReadOnly(p, g, r++, "Basic Salary", FormatUtil.currency(emp.getBasicSalary()));
        FormRow.addReadOnly(p, g, r++, "Hourly Rate", FormatUtil.currency(emp.getHourlyRate()));

        JScrollPane sp = new JScrollPane(p);
        sp.setPreferredSize(new Dimension(450, 450));
        sp.setBorder(null);
        JOptionPane.showMessageDialog(this, sp, emp.getFullName(), JOptionPane.PLAIN_MESSAGE);
    }

    private static class EmpModel extends AbstractTableModel {
        private static final String[] C = {"ID", "Full Name", "Status", "Position", "Supervisor", "Basic Salary"};
        private final List<Employee> data;
        EmpModel(List<Employee> d) { data = d; }
        Employee getAt(int r) { return data.get(r); }
        @Override public int getRowCount() { return data.size(); }
        @Override public int getColumnCount() { return C.length; }
        @Override public String getColumnName(int c) { return C[c]; }
        @Override public Class<?> getColumnClass(int c) { return c == 0 ? Integer.class : String.class; }
        @Override
        public Object getValueAt(int r, int c) {
            Employee e = data.get(r);
            switch (c) {
                case 0: return e.getEmployeeId();
                case 1: return e.getFullName();
                case 2: return e.getStatus();
                case 3: return e.getPosition();
                case 4: return e.getImmediateSupervisor();
                case 5: return FormatUtil.currency(e.getBasicSalary());
                default: return "";
            }
        }
    }
}
