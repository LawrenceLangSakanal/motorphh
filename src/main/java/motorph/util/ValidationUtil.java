package motorph.util;

import java.time.LocalDate;

/**
 * Shared input-validation helpers used across form pages.
 */
public final class ValidationUtil {

    private ValidationUtil() {}

    /** Returns {@code true} if the string is null or contains only whitespace. */
    public static boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }

    /** Returns {@code true} if the string represents a valid positive integer. */
    public static boolean isPositiveInt(String value) {
        if (isBlank(value)) return false;
        try {
            return Integer.parseInt(value.trim()) > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Returns {@code true} if the string is a valid date in {@code MM/dd/yyyy} format
     * and the resulting date is not null.
     */
    public static boolean isValidDate(String value) {
        return DateUtil.parseDate(value) != null;
    }

    /**
     * Returns {@code true} if start and end form a valid date range
     * (both are valid dates and end is on or after start).
     */
    public static boolean isValidDateRange(String startStr, String endStr) {
        LocalDate start = DateUtil.parseDate(startStr);
        LocalDate end = DateUtil.parseDate(endStr);
        if (start == null || end == null) return false;
        return !end.isBefore(start);
    }

    /** Returns {@code true} if the value has at least {@code min} non-whitespace characters. */
    public static boolean hasMinLength(String value, int min) {
        return value != null && value.trim().length() >= min;
    }
}
