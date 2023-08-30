/* This is a machine generated file. DO NOT EDIT. */
package com.pmdesigns.jvc.tools.bootstrap;

import java.util.*;

public class PageGeneratorGenerator {

public static String genPage(Map<String,String> map) {
StringBuilder _sb = new StringBuilder();
_sb.append(_nf(map.get("machine.generated"))).append(" \n");
_sb.append("package ").append(_nf(map.get("package.prefix.dot"))).append("generators").append(_nf(map.get("dot.relative.package"))).append(";\n");
_sb.append("\n");
_sb.append("import com.pmdesigns.jvc.JVCRequestContext;\n");
_sb.append("import ").append(_nf(map.get("package.prefix.dot"))).append("generators.BaseGenerator;\n");
_sb.append("import ").append(_nf(map.get("package.prefix.dot"))).append("controllers").append(_nf(map.get("dot.relative.package"))).append(".").append(_nf(map.get("page.name"))).append("Controller;\n");
_sb.append("import static ").append(_nf(map.get("package.prefix.dot"))).append("utils.Helpers.*;\n");
_sb.append(_nf(map.get("imports"))).append(" \n");
_sb.append("\n");
_sb.append("public class ").append(_nf(map.get("page.name"))).append("Generator extends BaseGenerator {\n");
_sb.append("\n");
_sb.append("public static String genPage(JVCRequestContext rc) {\n");
_sb.append(_nf(map.get("page.name"))).append("Controller controller = new ").append(_nf(map.get("page.name"))).append("Controller(rc);\n");
_sb.append("StringBuilder _sb = new StringBuilder();\n");
_sb.append(_nf(map.get("page.body"))).append(" \n");
_sb.append("return _sb.toString();\n");
_sb.append("}\n");
_sb.append("}\n");

return _sb.toString();
}
private static String _nf(String x) { return (x == null) ? "" : x; }
}
