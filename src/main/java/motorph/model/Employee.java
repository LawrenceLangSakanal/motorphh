package motorph.model;

public abstract class Employee {

    private int employeeId;
    private String password;
    private String lastName;
    private String firstName;
    private String birthday;
    private String address;
    private String phoneNumber;
    private String sssNumber;
    private String philhealthNumber;
    private String tinNumber;
    private String pagibigNumber;
    private String status;
    private String position;
    private String immediateSupervisor;

    private double basicSalary;
    private double riceSubsidy;
    private double phoneAllowance;
    private double clothingAllowance;
    private double grossSemiMonthlyRate;
    private double hourlyRate;

    private double sssDeduction;
    private double philhealthDeduction;
    private double pagibigDeduction;
    private double taxDeduction;

    public Employee() {}

    public Employee(int employeeId, String password, String lastName, String firstName,
                    String birthday, String address, String phoneNumber,
                    String sssNumber, String philhealthNumber, String tinNumber, String pagibigNumber,
                    String position, String immediateSupervisor,
                    double basicSalary, double riceSubsidy, double phoneAllowance,
                    double clothingAllowance, double grossSemiMonthlyRate, double hourlyRate) {
        this.employeeId = employeeId;
        this.password = password;
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthday = birthday;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.sssNumber = sssNumber;
        this.philhealthNumber = philhealthNumber;
        this.tinNumber = tinNumber;
        this.pagibigNumber = pagibigNumber;
        this.position = position;
        this.immediateSupervisor = immediateSupervisor;
        this.basicSalary = basicSalary;
        this.riceSubsidy = riceSubsidy;
        this.phoneAllowance = phoneAllowance;
        this.clothingAllowance = clothingAllowance;
        this.grossSemiMonthlyRate = grossSemiMonthlyRate;
        this.hourlyRate = hourlyRate;
    }

    public abstract double calculateBenefitsMultiplier();

    public String getFullName() { return firstName + " " + lastName; }

    public double getTotalDeductions() {
        return sssDeduction + philhealthDeduction + pagibigDeduction + taxDeduction;
    }

    public int getEmployeeId() { return employeeId; }
    public void setEmployeeId(int employeeId) { this.employeeId = employeeId; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getBirthday() { return birthday; }
    public void setBirthday(String birthday) { this.birthday = birthday; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getSssNumber() { return sssNumber; }
    public void setSssNumber(String sssNumber) { this.sssNumber = sssNumber; }

    public String getPhilhealthNumber() { return philhealthNumber; }
    public void setPhilhealthNumber(String philhealthNumber) { this.philhealthNumber = philhealthNumber; }

    public String getTinNumber() { return tinNumber; }
    public void setTinNumber(String tinNumber) { this.tinNumber = tinNumber; }

    public String getPagibigNumber() { return pagibigNumber; }
    public void setPagibigNumber(String pagibigNumber) { this.pagibigNumber = pagibigNumber; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }

    public String getImmediateSupervisor() { return immediateSupervisor; }
    public void setImmediateSupervisor(String immediateSupervisor) { this.immediateSupervisor = immediateSupervisor; }

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

    public double getSssDeduction() { return sssDeduction; }
    public void setSssDeduction(double sssDeduction) { this.sssDeduction = sssDeduction; }

    public double getPhilhealthDeduction() { return philhealthDeduction; }
    public void setPhilhealthDeduction(double philhealthDeduction) { this.philhealthDeduction = philhealthDeduction; }

    public double getPagibigDeduction() { return pagibigDeduction; }
    public void setPagibigDeduction(double pagibigDeduction) { this.pagibigDeduction = pagibigDeduction; }

    public double getTaxDeduction() { return taxDeduction; }
    public void setTaxDeduction(double taxDeduction) { this.taxDeduction = taxDeduction; }

    @Override
    public String toString() {
        return employeeId + " - " + getFullName() + " (" + position + ")";
    }
}
