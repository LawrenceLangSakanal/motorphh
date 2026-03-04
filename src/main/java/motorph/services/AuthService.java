package motorph.services;

import java.util.Optional;
import motorph.data.EmployeeRepository;
import motorph.model.Employee;

public class AuthService {

    private final EmployeeRepository employeeRepo;

    public AuthService(EmployeeRepository employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    public Optional<Employee> login(int employeeId, String password) {
        return employeeRepo.authenticate(employeeId, password);
    }
}
