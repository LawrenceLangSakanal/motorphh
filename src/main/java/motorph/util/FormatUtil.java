package motorph.util;

import java.text.DecimalFormat;

public final class FormatUtil {

    private static final DecimalFormat CURRENCY_FORMAT = new DecimalFormat("\u20B1#,##0.00");

    private FormatUtil() {}

    public static String currency(double amount) {
        return CURRENCY_FORMAT.format(amount);
    }
}
