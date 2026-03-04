package motorph.services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import motorph.data.AttendanceRepository;
import motorph.model.AttendanceLog;

public class AttendanceService {

    private final AttendanceRepository attendanceRepo;
    private final Map<Integer, String> clockInTimes = new HashMap<>();
    private final Map<Integer, String> clockOutTimes = new HashMap<>();
    private final Set<Integer> clockedIn = new HashSet<>();

    public AttendanceService(AttendanceRepository attendanceRepo) {
        this.attendanceRepo = attendanceRepo;
    }

    public void clockIn(int employeeId) {
        String time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
        clockInTimes.put(employeeId, time);
        clockedIn.add(employeeId);
    }

    public void clockOut(int employeeId) {
        String time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
        clockOutTimes.put(employeeId, time);
        clockedIn.remove(employeeId);

        String ci = clockInTimes.get(employeeId);
        if (ci != null) {
            String date = LocalDate.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
            LocalTime in = LocalTime.parse(ci);
            LocalTime out = LocalTime.parse(time);
            double hours = java.time.Duration.between(in, out).toMinutes() / 60.0;
            if (hours < 0) hours = 0;
            attendanceRepo.addRecord(new AttendanceLog(employeeId, date, ci, time,
                    Math.round(hours * 100.0) / 100.0));
        }
    }

    public boolean isClockedIn(int employeeId) {
        return clockedIn.contains(employeeId);
    }

    public String getLastClockIn(int employeeId) {
        return clockInTimes.getOrDefault(employeeId, "");
    }

    public String getLastClockOut(int employeeId) {
        return clockOutTimes.getOrDefault(employeeId, "");
    }

    public double getTotalHoursWorked(int employeeId, int month, int year) {
        return attendanceRepo.getTotalHours(employeeId, month, year);
    }

    public int getDaysPresent(int employeeId, int month, int year) {
        return attendanceRepo.getByEmployeeAndMonth(employeeId, month, year).size();
    }

    public List<AttendanceLog> getRecords(int employeeId, String fromDate, String toDate) {
        return attendanceRepo.getByDateRange(employeeId, fromDate, toDate);
    }

    public List<AttendanceLog> getAllRecords(int employeeId) {
        return attendanceRepo.getByEmployee(employeeId);
    }
}
