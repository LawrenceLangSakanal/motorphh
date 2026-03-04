package motorph.model;

public class ProbationaryEmployee extends Employee {

    public ProbationaryEmployee() {
        super();
        setStatus("Probationary");
    }

    public ProbationaryEmployee(int employeeId, String password, String lastName, String firstName,
                                String birthday, String address, String phoneNumber,
                                String sssNumber, String philhealthNumber, String tinNumber, String pagibigNumber,
                                String position, String immediateSupervisor,
                                double basicSalary, double riceSubsidy, double phoneAllowance,
                                double clothingAllowance, double grossSemiMonthlyRate, double hourlyRate) {
        super(employeeId, password, lastName, firstName, birthday, address, phoneNumber,
              sssNumber, philhealthNumber, tinNumber, pagibigNumber,
              position, immediateSupervisor,
              basicSalary, riceSubsidy, phoneAllowance, clothingAllowance,
              grossSemiMonthlyRate, hourlyRate);
        setStatus("Probationary");
    }

    @Override
    public double calculateBenefitsMultiplier() {
        return 0.75;
    }
}
