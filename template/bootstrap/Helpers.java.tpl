package [[= map.get("package.prefix.dot")]]utils;

import java.util.Date;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;


/**
 * Static helper methods.
 * These methods are statically included in all page generators so they
 * are available in your view templates so you can write things like:
 * \[[= currencyFormat(controller.getPrice()) \]]
 */
public class Helpers {
	public static boolean isBlank(String s) {
		return (s == null || s.length() == 0);
	}

	public static String currencyFormat(float amt) {
		return NumberFormat.getCurrencyInstance().format(amt);
	}

	public static String dateFormat(Date d) {
		return new SimpleDateFormat("MMM d yyyy").format(d);
	}

	public static String timeFormat(Date d) {
		return new SimpleDateFormat("hh:mma").format(d);
	}
}
