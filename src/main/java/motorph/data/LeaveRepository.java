package motorph.data;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import motorph.model.LeaveRequest;

public class LeaveRepository {

    private static final String FILE_PATH = "data/leave_requests.csv";
    private final List<LeaveRequest> requests = new ArrayList<>();
    private final AtomicInteger nextId = new AtomicInteger(1);

    public LeaveRepository() {
        new File("data").mkdirs();
        loadFromFile();
    }

    public List<LeaveRequest> getAll() {
        return new ArrayList<>(requests);
    }

    public LeaveRequest findById(int id) {
        return requests.stream().filter(r -> r.getId() == id).findFirst().orElse(null);
    }

    public void save(LeaveRequest request) {
        request.setId(nextId.getAndIncrement());
        requests.add(request);
        saveToFile();
    }

    public void update(LeaveRequest updated) {
        for (int i = 0; i < requests.size(); i++) {
            if (requests.get(i).getId() == updated.getId()) {
                requests.set(i, updated);
                break;
            }
        }
        saveToFile();
    }

    private void loadFromFile() {
        File file = new File(FILE_PATH);
        if (!file.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            br.readLine(); // skip header
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split(",", -1);
                if (p.length >= 11) {
                    LeaveRequest r = new LeaveRequest();
                    r.setId(Integer.parseInt(p[0]));
                    r.setEmployeeId(Integer.parseInt(p[1]));
                    r.setEmployeeName(p[2]);
                    r.setLeaveType(p[3]);
                    r.setStartDate(p[4]);
                    r.setEndDate(p[5]);
                    r.setDays(Integer.parseInt(p[6]));
                    r.setReason(p[7]);
                    r.setStatus(p[8]);
                    r.setDecidedBy(p[9]);
                    r.setDecisionDate(p[10]);
                    if (p.length > 11) r.setSubmittedDate(p[11]);
                    requests.add(r);
                    if (r.getId() >= nextId.get()) nextId.set(r.getId() + 1);
                }
            }
        } catch (Exception e) {
            // start fresh
        }
    }

    private void saveToFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_PATH))) {
            pw.println("id,employeeId,employeeName,leaveType,startDate,endDate,days,reason,status,decidedBy,decisionDate,submittedDate");
            for (LeaveRequest r : requests) {
                pw.println(String.join(",",
                        String.valueOf(r.getId()),
                        String.valueOf(r.getEmployeeId()),
                        safe(r.getEmployeeName()),
                        safe(r.getLeaveType()),
                        safe(r.getStartDate()),
                        safe(r.getEndDate()),
                        String.valueOf(r.getDays()),
                        safe(r.getReason()),
                        safe(r.getStatus()),
                        safe(r.getDecidedBy()),
                        safe(r.getDecisionDate()),
                        safe(r.getSubmittedDate())
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String safe(String s) {
        return s != null ? s.replace(",", ";") : "";
    }
}
