package motorph.services;

import motorph.model.Employee;

public class SalaryCalculationService {

    public double computeGrossPay(Employee employee, double hoursWorked) {
        return employee.getHourlyRate() * hoursWorked;
    }

    public double computeAllowances(Employee employee) {
        double raw = employee.getRiceSubsidy()
                + employee.getPhoneAllowance()
                + employee.getClothingAllowance();
        return raw * employee.calculateBenefitsMultiplier();
    }
}
