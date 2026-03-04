package motorph.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import motorph.data.LeaveRepository;
import motorph.model.LeaveRequest;

public class LeaveService {

    private final LeaveRepository leaveRepo;

    public LeaveService(LeaveRepository leaveRepo) {
        this.leaveRepo = leaveRepo;
    }

    public void submitRequest(LeaveRequest request) {
        request.setStatus("PENDING");
        request.setSubmittedDate(LocalDate.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        leaveRepo.save(request);
    }

    public List<LeaveRequest> getByEmployee(int employeeId) {
        return leaveRepo.getAll().stream()
                .filter(r -> r.getEmployeeId() == employeeId)
                .collect(Collectors.toList());
    }

    public List<LeaveRequest> getAll() {
        return leaveRepo.getAll();
    }

    public List<LeaveRequest> getPending() {
        return leaveRepo.getAll().stream()
                .filter(r -> "PENDING".equals(r.getStatus()))
                .collect(Collectors.toList());
    }

    public void approve(int requestId, String approverName) {
        LeaveRequest req = leaveRepo.findById(requestId);
        if (req != null) {
            req.setStatus("APPROVED");
            req.setDecidedBy(approverName);
            req.setDecisionDate(LocalDate.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
            leaveRepo.update(req);
        }
    }

    public void reject(int requestId, String approverName) {
        LeaveRequest req = leaveRepo.findById(requestId);
        if (req != null) {
            req.setStatus("REJECTED");
            req.setDecidedBy(approverName);
            req.setDecisionDate(LocalDate.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
            leaveRepo.update(req);
        }
    }
}
