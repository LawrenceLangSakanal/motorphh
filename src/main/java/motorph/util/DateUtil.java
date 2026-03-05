package motorph.util;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

/**
 * Date/time helpers using the Manila (Asia/Manila) timezone.
 */
public final class DateUtil {

    public static final ZoneId MANILA = ZoneId.of("Asia/Manila");
    public static final DateTimeFormatter DATE_FMT = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    public static final DateTimeFormatter TIME_FMT = DateTimeFormatter.ofPattern("HH:mm");
    public static final DateTimeFormatter DATETIME_FMT = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");

    private DateUtil() {}

    /** Returns the current date in the Manila timezone. */
    public static LocalDate today() {
        return LocalDate.now(MANILA);
    }

    /** Returns the current time in the Manila timezone. */
    public static LocalTime nowTime() {
        return LocalTime.now(MANILA);
    }

    /** Formats a {@link LocalDate} as {@code MM/dd/yyyy}. */
    public static String formatDate(LocalDate date) {
        return date == null ? "" : date.format(DATE_FMT);
    }

    /** Formats a {@link LocalTime} as {@code HH:mm}. */
    public static String formatTime(LocalTime time) {
        return time == null ? "" : time.format(TIME_FMT);
    }

    /**
     * Parses a date string in {@code MM/dd/yyyy} format.
     *
     * @return the parsed {@link LocalDate}, or {@code null} if parsing fails
     */
    public static LocalDate parseDate(String dateStr) {
        if (dateStr == null || dateStr.isBlank()) return null;
        try {
            return LocalDate.parse(dateStr.trim(), DATE_FMT);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    /**
     * Calculates the number of calendar days between two dates (inclusive).
     *
     * @return number of days, or 0 if either date is null or end is before start
     */
    public static int daysBetween(LocalDate start, LocalDate end) {
        if (start == null || end == null) return 0;
        long diff = ChronoUnit.DAYS.between(start, end) + 1;
        return diff > 0 ? (int) diff : 0;
    }

    /** Returns today's date formatted as {@code MM/dd/yyyy}. */
    public static String todayString() {
        return formatDate(today());
    }
}
