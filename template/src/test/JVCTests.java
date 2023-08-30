package test;

import javax.servlet.http.Cookie;
import java.net.HttpURLConnection;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import static org.junit.Assert.*;


/**
 * JVC unit tests
 */
public class JVCTests extends TestCase {
	protected TestDispatcher dispatcher;
	protected TestRequest request;
	protected TestResponse response;
	private static String PKG_PREFIX;

	@Override
	protected void setUp() {
		dispatcher = new TestDispatcher(PKG_PREFIX);
		request = new TestRequest("/test", "");
		response = new TestResponse();
	}
	
	public static Test suite() {
		return new TestSuite(JVCTests.class);
	}

	public void testComments() {
		// get page that contains comment tags
		String resp = getPageContents("/tests/comments");
		assertEquals("hello test", resp);
	}

	public void testComments2() {
		// get another page that contains comment tags
		String resp = getPageContents("/tests/comments2");
		assertEquals("before\nafter", resp);
	}
	
	public void testIncludes() {
		// get page that contains include tags
		String resp = getPageContents("/tests/includes");
		assertEquals("hello hello1\nhello2", resp);
	}
	
	public void testExpressions() {
		// get page that contains code and expression tags
		String resp = getPageContents("/tests/expressions");
		assertEquals("10,9,8,7,6,5,4,3,2,1,0", resp);
	}
	
	public void testRelativeRedirect() {
		// get page that causes a redirect response using relative path
		request.servletPath = "/tests/redirect1";
		getPage();

		assertEquals(HttpURLConnection.HTTP_MOVED_TEMP, response.respCode);
		assertEquals("redirect_dest", response.respMsg);
	}

	public void testAbsoluteRedirect() {
		// get page that causes a redirect response using relative path
		request.servletPath = "/tests/redirect2";
		getPage();

		assertEquals(HttpURLConnection.HTTP_MOVED_TEMP, response.respCode);
		assertEquals("/test/tests/redirect_dest", response.respMsg);
	}

	public void testRedirectAndFlash() {
		// first just repeat the redirect test...
		
		// get page that causes a redirect response (and puts something in the flash)
		request.servletPath = "/tests/redirect_and_flash";
		getPage();

		assertEquals(HttpURLConnection.HTTP_MOVED_TEMP, response.respCode);
		String url = response.respMsg;
		assertEquals("tests/redirect_dest", url);

		// now test that the flash was stored in a 'flash cookie'
		assertEquals(1, response.cookies.size());
		Cookie[] cookies = response.cookies.toArray(new Cookie[1]);

		// now act like a browser and request the redirected page
		// (include the cookie in the request)
		request = new TestRequest("/test", url);
		request.cookies = cookies;
		response = new TestResponse();
		getPage();

 		assertEquals(HttpURLConnection.HTTP_OK, response.respCode);
		String resp = response.getResponseString().trim();
		assertEquals("flash value", resp); // from the original page
	}

	public void testThreadLocal() {
		// get page that gets the JVCRequestContext via JVCDispatcher.getRC() method
		String resp = getPageContents("/tests/threadlocal");
		assertEquals("servlet path: /tests/threadlocal", resp);
	}
	
	public void testCache() throws InterruptedException {
		// first make sure that page without caching gives different results each time its invoked
		// (it contains a call to System.currentTimeMillis())
		String resp1 = getPageContents("/tests/nocache");
		Thread.currentThread().sleep(10);
		request = new TestRequest("/test", "/tests/nocache");
		response = new TestResponse();
		String resp2 = getPageContents("/tests/nocache");
		assertFalse(resp1.equals(resp2));

		// now do the same thing with a page using caching, results should be the same
		request = new TestRequest("/test", "/tests/cache");
		response = new TestResponse();
		resp1 = getPageContents("/tests/cache");
		Thread.currentThread().sleep(10);
		request = new TestRequest("/test", "/tests/cache");
		response = new TestResponse();
		resp2 = getPageContents("/tests/cache");
		assertEquals(resp1, resp2);
	}

	public void testIncludedCache() throws InterruptedException {
		// make sure that caching works even with included files
		String resp1 = getPageContents("/tests/cache2");
		Thread.currentThread().sleep(10);
		request = new TestRequest("/test", "/tests/cache2");
		response = new TestResponse();
		String resp2 = getPageContents("/tests/cache2");
		assertEquals(resp1, resp2);
	}

	public void testLinks() {
		// get page that contains some links
		String resp = getPageContents("/tests/links");
		String expected = "<a href='relative/path' >relative</a><a href='/test/absolute/path' >absolute</a>";
		assertEquals(expected, resp);
	}

	public void testImports() {
		// get page that contains some links
		String resp = getPageContents("/tests/imports");
		String expected = "ok";
		assertEquals(expected, resp);
	}

	public void testHelpers() {
		// get page that contains some tags that use helper methods
		String resp = getPageContents("/tests/helpers");
		String expected = "$1.00,true,true,false";
		assertEquals(expected, resp);
	}

	public void testHtmlEscape() {
		// get page that contains some html escape tags
		String resp = getPageContents("/tests/escape");
		String expected = "1 1, \" &quot;, & &amp;, < &lt;, > &gt;";
		assertEquals(expected, resp);
	}

	private String getPageContents(String path) {
		request.servletPath = path;
		getPage();

		assertEquals(HttpURLConnection.HTTP_OK, response.respCode);
		return response.getResponseString().trim();
	}
	
	private void getPage() {
		try {
			dispatcher.doGet(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}
	
	public static void main (String[] args) {
		PKG_PREFIX = (args.length > 0) ? args[0] : "";
		junit.textui.TestRunner.run(suite());
	}
}
