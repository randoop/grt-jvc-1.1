[[= map.get("machine.generated")]] 
package [[= map.get("package.prefix.dot")]]generators;

import com.pmdesigns.jvc.JVCRequestContext;
import com.pmdesigns.jvc.tools.HtmlEncoder;

public class BaseGenerator {
	// null filters
	protected static String _nf(String x) { return (x == null) ? "" : x; }
	protected static Object _nf(Object x) { return (x == null) ? "" : x; }
	protected static int _nf(int x) { return x; }
	protected static short _nf(short x) { return x; }
	protected static long _nf(long x) { return x; }
	protected static float _nf(float x) { return x; }
	protected static double _nf(double x) { return x; }
	protected static char _nf(char x) { return x; }
	protected static byte _nf(byte x) { return x; }
	protected static boolean _nf(boolean x) { return x; }

	// html filters
	protected static String _hf(String x) { return (x == null) ? "" : HtmlEncoder.encode(x); }
	protected static Object _hf(Object x) { return (x == null) ? "" : x; }
	protected static int _hf(int x) { return x; }
	protected static short _hf(short x) { return x; }
	protected static long _hf(long x) { return x; }
	protected static float _hf(float x) { return x; }
	protected static double _hf(double x) { return x; }
	protected static char _hf(char x) { return x; }
	protected static byte _hf(byte x) { return x; }
	protected static boolean _hf(boolean x) { return x; }

	protected static String getCachedBlock(String key) { return JVCRequestContext.getCachedBlock(key); }
	protected static void setCachedBlock(String key, String val) { JVCRequestContext.setCachedBlock(key, val); }
}

