[[= map.get("machine.generated")]] 
package [[= map.get("package.prefix.dot")]]generators[[= map.get("dot.relative.package")]];

import com.pmdesigns.jvc.JVCRequestContext;
import [[= map.get("package.prefix.dot")]]generators.BaseGenerator;
import [[= map.get("package.prefix.dot")]]controllers[[= map.get("dot.relative.package")]].[[= map.get("page.name")]]Controller;
import static [[= map.get("package.prefix.dot")]]utils.Helpers.*;
[[= map.get("imports")]] 

public class [[= map.get("page.name")]]Generator extends BaseGenerator {

public static String genPage(JVCRequestContext rc) {
[[= map.get("page.name")]]Controller controller = new [[= map.get("page.name")]]Controller(rc);
StringBuilder _sb = new StringBuilder();
[[= map.get("page.body")]] 
return _sb.toString();
}
}
