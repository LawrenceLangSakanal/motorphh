package motorph.model;

import motorph.util.FormatUtil;

public class Payslip {

    private int employeeId;
    private String employeeName;
    private String position;
    private String period;

    private double basicSalary;
    private double riceSubsidy;
    private double phoneAllowance;
    private double clothingAllowance;
    private double grossSemiMonthlyRate;
    private double hourlyRate;

    private double hoursWorked;
    private double grossPay;

    private double sssDeduction;
    private double philhealthDeduction;
    private double pagibigDeduction;
    private double withholdingTax;
    private double totalDeductions;

    private double netPay;
    private double benefitsMultiplier;

    public String toMultilineString() {
        StringBuilder sb = new StringBuilder();
        sb.append("═══════════════════════════════════════════\n");
        sb.append("           MOTORPH PAYROLL SLIP\n");
        sb.append("═══════════════════════════════════════════\n");
        sb.append(String.format("  Employee ID   : %d%n", employeeId));
        sb.append(String.format("  Employee Name : %s%n", employeeName));
        sb.append(String.format("  Position      : %s%n", position));
        sb.append(String.format("  Pay Period    : %s%n", period));
        sb.append(String.format("  Benefits Mult.: %.0f%%%n", benefitsMultiplier * 100));
        sb.append("───────────────────────────────────────────\n");
        sb.append("  EARNINGS\n");
        sb.append("───────────────────────────────────────────\n");
        sb.append(String.format("  Basic Salary          : %s%n", FormatUtil.currency(basicSalary)));
        sb.append(String.format("  Rice Subsidy          : %s%n", FormatUtil.currency(riceSubsidy)));
        sb.append(String.format("  Phone Allowance       : %s%n", FormatUtil.currency(phoneAllowance)));
        sb.append(String.format("  Clothing Allowance    : %s%n", FormatUtil.currency(clothingAllowance)));
        sb.append(String.format("  Gross Semi-Monthly    : %s%n", FormatUtil.currency(grossSemiMonthlyRate)));
        sb.append(String.format("  Hourly Rate           : %s%n", FormatUtil.currency(hourlyRate)));
        sb.append(String.format("  Hours Worked          : %.2f%n", hoursWorked));
        sb.append(String.format("  Gross Pay             : %s%n", FormatUtil.currency(grossPay)));
        sb.append("───────────────────────────────────────────\n");
        sb.append("  DEDUCTIONS\n");
        sb.append("───────────────────────────────────────────\n");
        sb.append(String.format("  SSS                   : %s%n", FormatUtil.currency(sssDeduction)));
        sb.append(String.format("  PhilHealth            : %s%n", FormatUtil.currency(philhealthDeduction)));
        sb.append(String.format("  Pag-IBIG              : %s%n", FormatUtil.currency(pagibigDeduction)));
        sb.append(String.format("  Withholding Tax       : %s%n", FormatUtil.currency(withholdingTax)));
        sb.append(String.format("  Total Deductions      : %s%n", FormatUtil.currency(totalDeductions)));
        sb.append("───────────────────────────────────────────\n");
        sb.append(String.format("  NET PAY               : %s%n", FormatUtil.currency(netPay)));
        sb.append("═══════════════════════════════════════════\n");
        return sb.toString();
    }

    public int getEmployeeId() { return employeeId; }
    public void setEmployeeId(int employeeId) { this.employeeId = employeeId; }

    public String getEmployeeName() { return employeeName; }
    public void setEmployeeName(String employeeName) { this.employeeName = employeeName; }

    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }

    public String getPeriod() { return period; }
    public void setPeriod(String period) { this.period = period; }

    public double getBasicSalary() { return basicSalary; }
    public void setBasicSalary(double basicSalary) { this.basicSalary = basicSalary; }

    public double getRiceSubsidy() { return riceSubsidy; }
    public void setRiceSubsidy(double riceSubsidy) { this.riceSubsidy = riceSubsidy; }

    public double getPhoneAllowance() { return phoneAllowance; }
    public void setPhoneAllowance(double phoneAllowance) { this.phoneAllowance = phoneAllowance; }

    public double getClothingAllowance() { return clothingAllowance; }
    public void setClothingAllowance(double clothingAllowance) { this.clothingAllowance = clothingAllowance; }

    public double getGrossSemiMonthlyRate() { return grossSemiMonthlyRate; }
    public void setGrossSemiMonthlyRate(double grossSemiMonthlyRate) { this.grossSemiMonthlyRate = grossSemiMonthlyRate; }

    public double getHourlyRate() { return hourlyRate; }
    public void setHourlyRate(double hourlyRate) { this.hourlyRate = hourlyRate; }

    public double getHoursWorked() { return hoursWorked; }
    public void setHoursWorked(double hoursWorked) { this.hoursWorked = hoursWorked; }

    public double getGrossPay() { return grossPay; }
    public void setGrossPay(double grossPay) { this.grossPay = grossPay; }

    public double getSssDeduction() { return sssDeduction; }
    public void setSssDeduction(double sssDeduction) { this.sssDeduction = sssDeduction; }

    public double getPhilhealthDeduction() { return philhealthDeduction; }
    public void setPhilhealthDeduction(double philhealthDeduction) { this.philhealthDeduction = philhealthDeduction; }

    public double getPagibigDeduction() { return pagibigDeduction; }
    public void setPagibigDeduction(double pagibigDeduction) { this.pagibigDeduction = pagibigDeduction; }

    public double getWithholdingTax() { return withholdingTax; }
    public void setWithholdingTax(double withholdingTax) { this.withholdingTax = withholdingTax; }

    public double getTotalDeductions() { return totalDeductions; }
    public void setTotalDeductions(double totalDeductions) { this.totalDeductions = totalDeductions; }

    public double getNetPay() { return netPay; }
    public void setNetPay(double netPay) { this.netPay = netPay; }

    public double getBenefitsMultiplier() { return benefitsMultiplier; }
    public void setBenefitsMultiplier(double benefitsMultiplier) { this.benefitsMultiplier = benefitsMultiplier; }
}
