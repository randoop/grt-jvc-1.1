/* This is a machine generated file. DO NOT EDIT. */
package com.pmdesigns.jvc.tools.bootstrap;

import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.Impure;
import java.util.*;

public class ApplicationGenerator {

@Impure
public static String genPage(Map<String,String> map) {
StringBuilder _sb = new StringBuilder();
if (map.get("package.prefix").length() > 0) {
_sb.append("package ").append(_nf(map.get("package.prefix"))).append(";\n");
}
_sb.append("	\n");
_sb.append("import com.pmdesigns.jvc.Destroyable;\n");
_sb.append("import javax.servlet.GenericServlet;\n");
_sb.append("\n");
_sb.append("/**\n");
_sb.append(" * An instance of this class is created when the servlet is initialized\n");
_sb.append(" * and its destroy method is called when the servlet is being destroyed\n");
_sb.append(" */\n");
_sb.append("public class Application implements Destroyable {\n");
_sb.append("\n");
_sb.append("    public Application(GenericServlet servlet) {\n");
_sb.append("    }\n");
_sb.append("\n");
_sb.append("    public void destroy() {\n");
_sb.append("    }\n");
_sb.append("}\n");
_sb.append("\n");

return _sb.toString();
}
@Pure
private static String _nf(String x) { return (x == null) ? "" : x; }
}
