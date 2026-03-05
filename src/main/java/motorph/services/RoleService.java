package motorph.services;

import motorph.model.Employee;
import motorph.model.Role;

public class RoleService {

    public Role determineRole(Employee employee) {
        String pos = employee.getPosition().toLowerCase();
        if (pos.contains("chief executive") || pos.contains("super admin")) return Role.ADMIN;
        if (pos.contains("hr") || pos.contains("payroll")) return Role.HR;
        if (pos.contains("manager") || pos.contains("team leader")
                || pos.contains("head") || pos.contains("chief")) return Role.MANAGER;
        return Role.EMPLOYEE;
    }

    /** Returns {@code true} if the role is allowed to approve or reject leave requests. */
    public boolean canApproveLeave(Role role) {
        return role == Role.HR || role == Role.ADMIN || role == Role.MANAGER;
    }

    /** Returns {@code true} if the role is allowed to access payroll management screens. */
    public boolean canAccessPayrollManagement(Role role) {
        return role == Role.HR || role == Role.ADMIN || role == Role.MANAGER;
    }

    /** Returns {@code true} if the role is allowed to access the employee management screen. */
    public boolean canAccessEmployeeManagement(Role role) {
        return role == Role.HR || role == Role.ADMIN;
    }
}

