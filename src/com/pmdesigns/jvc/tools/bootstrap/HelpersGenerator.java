/* This is a machine generated file. DO NOT EDIT. */
package com.pmdesigns.jvc.tools.bootstrap;

import java.util.*;

public class HelpersGenerator {

public static String genPage(Map<String,String> map) {
StringBuilder _sb = new StringBuilder();
_sb.append("package ").append(_nf(map.get("package.prefix.dot"))).append("utils;\n");
_sb.append("\n");
_sb.append("import java.util.Date;\n");
_sb.append("import java.text.NumberFormat;\n");
_sb.append("import java.text.SimpleDateFormat;\n");
_sb.append("\n");
_sb.append("\n");
_sb.append("/**\n");
_sb.append(" * Static helper methods.\n");
_sb.append(" * These methods are statically included in all page generators so they\n");
_sb.append(" * are available in your view templates so you can write things like:\n");
_sb.append(" * [[= currencyFormat(controller.getPrice()) ]]\n");
_sb.append(" */\n");
_sb.append("public class Helpers {\n");
_sb.append("	public static boolean isBlank(String s) {\n");
_sb.append("		return (s == null || s.length() == 0);\n");
_sb.append("	}\n");
_sb.append("\n");
_sb.append("	public static String currencyFormat(float amt) {\n");
_sb.append("		return NumberFormat.getCurrencyInstance().format(amt);\n");
_sb.append("	}\n");
_sb.append("\n");
_sb.append("	public static String dateFormat(Date d) {\n");
_sb.append("		return new SimpleDateFormat(\"MMM d yyyy\").format(d);\n");
_sb.append("	}\n");
_sb.append("\n");
_sb.append("	public static String timeFormat(Date d) {\n");
_sb.append("		return new SimpleDateFormat(\"hh:mma\").format(d);\n");
_sb.append("	}\n");
_sb.append("}\n");

return _sb.toString();
}
private static String _nf(String x) { return (x == null) ? "" : x; }
}
