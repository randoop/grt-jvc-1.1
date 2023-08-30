[[ if (map.get("package.prefix").length() > 0) {]]
package [[= map.get("package.prefix")]];
[[}]]
	
import com.pmdesigns.jvc.Destroyable;
import javax.servlet.GenericServlet;

/**
 * An instance of this class is created when the servlet is initialized
 * and its destroy method is called when the servlet is being destroyed
 */
public class Application implements Destroyable {

    public Application(GenericServlet servlet) {
    }

    public void destroy() {
    }
}

