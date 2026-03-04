package motorph.services;

import motorph.model.Employee;
import motorph.model.Payslip;

public class PayrollService {

    private final AttendanceService attendanceService;
    private final SalaryCalculationService salaryCalcService;
    private final GovernmentDeductionService deductionService;

    public PayrollService(AttendanceService attendanceService,
                          SalaryCalculationService salaryCalcService,
                          GovernmentDeductionService deductionService) {
        this.attendanceService = attendanceService;
        this.salaryCalcService = salaryCalcService;
        this.deductionService = deductionService;
    }

    public Payslip generatePayslip(Employee employee, int month, int year) {
        Payslip payslip = new Payslip();
        payslip.setEmployeeId(employee.getEmployeeId());
        payslip.setEmployeeName(employee.getFullName());
        payslip.setPosition(employee.getPosition());
        payslip.setPeriod(monthName(month) + " " + year);
        payslip.setBenefitsMultiplier(employee.calculateBenefitsMultiplier());

        payslip.setBasicSalary(employee.getBasicSalary());
        payslip.setRiceSubsidy(employee.getRiceSubsidy());
        payslip.setPhoneAllowance(employee.getPhoneAllowance());
        payslip.setClothingAllowance(employee.getClothingAllowance());
        payslip.setGrossSemiMonthlyRate(employee.getGrossSemiMonthlyRate());
        payslip.setHourlyRate(employee.getHourlyRate());

        double hoursWorked = attendanceService.getTotalHoursWorked(
                employee.getEmployeeId(), month, year);
        payslip.setHoursWorked(hoursWorked);

        double grossPay = salaryCalcService.computeGrossPay(employee, hoursWorked);
        payslip.setGrossPay(grossPay);

        double sss, phil, pag, tax;
        if (hasRecordedDeductions(employee)) {
            sss = employee.getSssDeduction();
            phil = employee.getPhilhealthDeduction();
            pag = employee.getPagibigDeduction();
            tax = employee.getTaxDeduction();
        } else {
            sss = deductionService.computeSSS(grossPay);
            phil = deductionService.computePhilHealth(grossPay);
            pag = deductionService.computePagIBIG(grossPay);
            double taxableIncome = grossPay - sss - phil - pag;
            tax = deductionService.computeWithholdingTax(taxableIncome);
        }

        payslip.setSssDeduction(sss);
        payslip.setPhilhealthDeduction(phil);
        payslip.setPagibigDeduction(pag);
        payslip.setWithholdingTax(tax);

        double totalDeductions = sss + phil + pag + tax;
        payslip.setTotalDeductions(totalDeductions);
        payslip.setNetPay(grossPay - totalDeductions);

        return payslip;
    }

    private boolean hasRecordedDeductions(Employee emp) {
        return emp.getSssDeduction() > 0
                || emp.getPhilhealthDeduction() > 0
                || emp.getPagibigDeduction() > 0
                || emp.getTaxDeduction() > 0;
    }

    private String monthName(int month) {
        String[] names = {"", "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"};
        return (month >= 1 && month <= 12) ? names[month] : "Unknown";
    }
}
