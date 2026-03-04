package motorph.model;

public class RegularEmployee extends Employee {

    public RegularEmployee() {
        super();
        setStatus("Regular");
    }

    public RegularEmployee(int employeeId, String password, String lastName, String firstName,
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
        setStatus("Regular");
    }

    @Override
    public double calculateBenefitsMultiplier() {
        return 1.0;
    }
}
