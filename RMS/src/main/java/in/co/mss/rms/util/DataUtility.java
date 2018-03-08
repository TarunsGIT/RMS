package in.co.mss.rms.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Data Utility class to format data from one format to another
 * 
 * @author Session Facade
 * @version 1.0
 * 
 * 
 */

public class DataUtility {
	/**
	 * Application Date Format
	 */
	public static final String APP_DATE_FORMAT = "dd/MM/yyyy";

	public static final String APP_TIME_FORMAT = "dd/MM/yyyy HH:mm:ss";

	/**
	 * Date formatter
	 */
	private static final SimpleDateFormat formatter = new SimpleDateFormat(
			APP_DATE_FORMAT);

	private static final SimpleDateFormat timeFormatter = new SimpleDateFormat(
			APP_TIME_FORMAT);

	/******************** REMOVING WHITE SPACE OF STRING USING trim(); METHOD **************/

	/**
	 * Trims and trailing and leading spaces of a String
	 * 
	 * 
	 * @param val
	 * @return
	 */
	public static String getString(String val) {
		if (DataValidator.isNotNull(val)) {
			return val.trim();
		} else {
			return val;
		}
	}

	/******* CONVERTING INCOMING OBECT TO STRING USING TO STRING(); METHOD *********/

	/**
	 * Converts and Object to String
	 * 
	 * @param val
	 * @return
	 */
	public static String getStringData(Object val) {
		if (val != null) {
			return val.toString();
		} else {
			return "";
		}
	}

	/**************** CONVERTING STRING INTO INTEGER USING INTERGER.PARSEINT(); METHOD ********/

	/**
	 * Converts String into Integer
	 * 
	 * @param val
	 * @return
	 */
	public static int getInt(String val) {
		if (DataValidator.isInteger(val)) {
			return Integer.parseInt(val);
		} else {
			return 0;
		}
	}

	/**************** CONVERTING STRING INTO INTEGER USING LONG.PARSELONG(); METHOD ********/

	/**
	 * Converts String into Long
	 * 
	 * @param val
	 * @return
	 */
	public static long getLong(String val) {
		if (DataValidator.isLong(val)) {
			return Long.parseLong(val);
		} else {
			return 0;
		}
	}

	/**************** CONVERTING STRING INTO DATE USING FORMATER.PARSE(); METHOD ********/

	/**
	 * Converts String into Date
	 * 
	 * @param val
	 * @return
	 */

	public static Date getDate(String val) {
		Date date = null;
		try {
			date = formatter.parse(val);
		} catch (Exception e) {

		}
		return date;
	}

	/**************** CONVERTING DATE INTO STRING USING FORMATER.FORMAT; METHOD ********/

	/**
	 * Converts Date into String
	 * 
	 * @param date
	 * @return
	 */
	public static String getDateString(Date date) {
		try {
			return formatter.format(date);
		} catch (Exception e) {
		}
		return "";
	}

	/**************** CALCULATING DATE BY USING A SPECIFIC DAY ********/

	/**
	 * Gets date after n number of days
	 * 
	 * @param date
	 * @param day
	 * @return
	 */
	/*
	 * public static Date getDate(Date date, int day) { return null; }
	 */

	/******************* STRING INTO TIME *******************************************/

	/**
	 * Converts String into Time
	 * 
	 * @param val
	 * @return
	 */
	public static Timestamp getTimestamp(String val) {

		Timestamp timeStamp = null;
		try {
			timeStamp = new Timestamp((timeFormatter.parse(val)).getTime());
		} catch (Exception e) {
			return null;
		}
		return timeStamp;
	}

	public static Timestamp getTimestamp(long l) {

		Timestamp timeStamp = null;
		try {
			timeStamp = new Timestamp(l);
		} catch (Exception e) {
			return null;
		}
		return timeStamp;
	}

	public static Timestamp getCurrentTimestamp() {
		Timestamp timeStamp = null;
		try {
			timeStamp = new Timestamp(new Date().getTime());
		} catch (Exception e) {
		}
		return timeStamp;

	}

	public static long getTimestamp(Timestamp tm) {
		try {
			return tm.getTime();
		} catch (Exception e) {
			return 0;
		}
	}

	public static String getCapital(String val) {
		String str = val;
		StringBuffer buffer = new StringBuffer();
		Matcher m = Pattern
				.compile("([a-z])([a-z]*)", Pattern.CASE_INSENSITIVE).matcher(
						str);
		while (m.find()) {
			m.appendReplacement(buffer, m.group(1).toUpperCase()
					+ m.group(2).toLowerCase());
		}
		return m.appendTail(buffer).toString();

	}

	public static void main(String[] args) {
		System.out.println(getCapital("john"));
		System.out.println(getInt("124"));
		System.out.println(getDate("09/09/2016"));
		System.out.println(getDate("09/02/2016").getTime());

	}

}
