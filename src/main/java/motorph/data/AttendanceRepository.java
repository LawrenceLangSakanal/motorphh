package motorph.data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import motorph.model.AttendanceLog;

public class AttendanceRepository {

    private final List<AttendanceLog> records = new ArrayList<>();

    public AttendanceRepository() {
        loadSampleData();
    }

    public void addRecord(AttendanceLog log) {
        records.add(log);
    }

    public List<AttendanceLog> getByEmployee(int employeeId) {
        return records.stream()
                .filter(a -> a.getEmployeeId() == employeeId)
                .collect(Collectors.toList());
    }

    public List<AttendanceLog> getByEmployeeAndMonth(int employeeId, int month, int year) {
        return records.stream()
                .filter(a -> a.getEmployeeId() == employeeId)
                .filter(a -> matchesMonth(a.getDate(), month, year))
                .collect(Collectors.toList());
    }

    public List<AttendanceLog> getByDateRange(int employeeId, String fromDate, String toDate) {
        return records.stream()
                .filter(a -> a.getEmployeeId() == employeeId)
                .filter(a -> isInRange(a.getDate(), fromDate, toDate))
                .collect(Collectors.toList());
    }

    public double getTotalHours(int employeeId, int month, int year) {
        return getByEmployeeAndMonth(employeeId, month, year).stream()
                .mapToDouble(AttendanceLog::getHoursWorked)
                .sum();
    }

    public List<AttendanceLog> getAll() {
        return records;
    }

    private boolean matchesMonth(String date, int month, int year) {
        if (date == null || date.isEmpty()) return false;
        try {
            String[] parts = date.split("/");
            int m = Integer.parseInt(parts[0]);
            int y = Integer.parseInt(parts[2]);
            return m == month && y == year;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isInRange(String date, String from, String to) {
        if (date == null || from == null || to == null) return false;
        try {
            int d = toInt(date);
            int f = toInt(from);
            int t = toInt(to);
            return d >= f && d <= t;
        } catch (Exception e) {
            return false;
        }
    }

    private int toInt(String date) {
        String[] p = date.split("/");
        return Integer.parseInt(p[2]) * 10000 + Integer.parseInt(p[0]) * 100 + Integer.parseInt(p[1]);
    }

    private void loadSampleData() {
        int[] ids = {10001,10002,10003,10004,10005,10006,10007,10008,10009,10010,
                     10011,10012,10013,10014,10015,10016,10017,10018,10019,10020,
                     10021,10022,10023,10024,10025,10026,10027,10028,10029,10030,
                     10031,10032,10033,10034};
        for (int id : ids) {
            for (int year = 2024; year <= 2026; year++) {
                for (int month = 1; month <= 12; month++) {
                    for (int day = 1; day <= 20; day++) {
                        String date = String.format("%02d/%02d/%04d", month, day, year);
                        records.add(new AttendanceLog(id, date, "08:00", "17:00", 8.0));
                    }
                }
            }
        }
    }
}
