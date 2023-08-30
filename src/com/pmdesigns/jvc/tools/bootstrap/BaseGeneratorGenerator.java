/* This is a machine generated file. DO NOT EDIT. */
package com.pmdesigns.jvc.tools.bootstrap;

import java.util.*;

public class BaseGeneratorGenerator {

public static String genPage(Map<String,String> map) {
StringBuilder _sb = new StringBuilder();
_sb.append(_nf(map.get("machine.generated"))).append(" \n");
_sb.append("package ").append(_nf(map.get("package.prefix.dot"))).append("generators;\n");
_sb.append("\n");
_sb.append("import com.pmdesigns.jvc.JVCRequestContext;\n");
_sb.append("import com.pmdesigns.jvc.tools.HtmlEncoder;\n");
_sb.append("\n");
_sb.append("public class BaseGenerator {\n");
_sb.append("	// null filters\n");
_sb.append("	protected static String _nf(String x) { return (x == null) ? \"\" : x; }\n");
_sb.append("	protected static Object _nf(Object x) { return (x == null) ? \"\" : x; }\n");
_sb.append("	protected static int _nf(int x) { return x; }\n");
_sb.append("	protected static short _nf(short x) { return x; }\n");
_sb.append("	protected static long _nf(long x) { return x; }\n");
_sb.append("	protected static float _nf(float x) { return x; }\n");
_sb.append("	protected static double _nf(double x) { return x; }\n");
_sb.append("	protected static char _nf(char x) { return x; }\n");
_sb.append("	protected static byte _nf(byte x) { return x; }\n");
_sb.append("	protected static boolean _nf(boolean x) { return x; }\n");
_sb.append("\n");
_sb.append("	// html filters\n");
_sb.append("	protected static String _hf(String x) { return (x == null) ? \"\" : HtmlEncoder.encode(x); }\n");
_sb.append("	protected static Object _hf(Object x) { return (x == null) ? \"\" : x; }\n");
_sb.append("	protected static int _hf(int x) { return x; }\n");
_sb.append("	protected static short _hf(short x) { return x; }\n");
_sb.append("	protected static long _hf(long x) { return x; }\n");
_sb.append("	protected static float _hf(float x) { return x; }\n");
_sb.append("	protected static double _hf(double x) { return x; }\n");
_sb.append("	protected static char _hf(char x) { return x; }\n");
_sb.append("	protected static byte _hf(byte x) { return x; }\n");
_sb.append("	protected static boolean _hf(boolean x) { return x; }\n");
_sb.append("\n");
_sb.append("	protected static String getCachedBlock(String key) { return JVCRequestContext.getCachedBlock(key); }\n");
_sb.append("	protected static void setCachedBlock(String key, String val) { JVCRequestContext.setCachedBlock(key, val); }\n");
_sb.append("}\n");
_sb.append("\n");

return _sb.toString();
}
private static String _nf(String x) { return (x == null) ? "" : x; }
}
