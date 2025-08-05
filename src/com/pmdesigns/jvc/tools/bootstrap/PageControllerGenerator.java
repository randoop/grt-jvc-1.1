/* This is a machine generated file. DO NOT EDIT. */
package com.pmdesigns.jvc.tools.bootstrap;

import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.Impure;
import java.util.*;

public class PageControllerGenerator {

@Impure
public static String genPage(Map<String,String> map) {
StringBuilder _sb = new StringBuilder();
_sb.append("package ").append(_nf(map.get("package.prefix.dot"))).append("controllers").append(_nf(map.get("dot.relative.package"))).append(";\n");
_sb.append("\n");
_sb.append("import com.pmdesigns.jvc.JVCRequestContext;\n");
_sb.append("\n");
_sb.append("public class ").append(_nf(map.get("page.name"))).append("Controller extends Base").append(_nf(map.get("dir.name"))).append("Controller {\n");
_sb.append("\n");
_sb.append("    public ").append(_nf(map.get("page.name"))).append("Controller(JVCRequestContext rc) {\n");
_sb.append("        super(rc);\n");
_sb.append("    }\n");
_sb.append("}\n");
_sb.append("\n");

return _sb.toString();
}
@Pure
private static String _nf(String x) { return (x == null) ? "" : x; }
}
