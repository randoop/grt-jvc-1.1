package test;

import com.pmdesigns.jvc.JVCDispatcher;


public final class TestDispatcher extends JVCDispatcher {
	private String pkgPrefix;

	public TestDispatcher(String pkgPrefix) {
		this.pkgPrefix = pkgPrefix;
		init();
	}
	
	public String getInitParameter(String key) {
		if (PKG_PREFIX_KEY.equals(key)) {
			return pkgPrefix;
		}
		return null;
	}
}
