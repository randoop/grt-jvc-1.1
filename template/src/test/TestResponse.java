package test;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.net.HttpURLConnection;


public class TestResponse implements HttpServletResponse {
	public int respCode = HttpURLConnection.HTTP_OK;
	public String respMsg;
	public List<Cookie> cookies = new ArrayList<Cookie>();
	public StringWriter stringWriter = new StringWriter();
	public PrintWriter printWriter = new PrintWriter(stringWriter);
	

	public String getResponseString() {
		return stringWriter.toString();
	}
	
	public void addCookie(Cookie cookie) {
		cookies.add(cookie);
	}

	public void sendError(int sc, String msg) throws IOException {
		respCode = sc;
		respMsg = msg;
	}

	public void sendError(int sc) throws IOException {
		respCode = sc;
		respMsg = null;
	}

	public void sendRedirect(String location) throws IOException {
		respCode = HttpURLConnection.HTTP_MOVED_TEMP;
		respMsg = location;
	}

	public PrintWriter getWriter() {
		return printWriter;
	}

	public void flushBuffer() {
		printWriter.flush();
	}

	public boolean containsHeader(String name) {
		throw new RuntimeException("not implemented");
	}

	public String encodeURL(String url) {
		throw new RuntimeException("not implemented");
	}

	public String encodeRedirectURL(String url) {
		throw new RuntimeException("not implemented");
	}

	public String encodeUrl(String url) {
		throw new RuntimeException("not implemented");
	}

	public String encodeRedirectUrl(String url) {
		throw new RuntimeException("not implemented");
	}

	public void setDateHeader(String name, long date) {
		throw new RuntimeException("not implemented");
	}

	public void addDateHeader(String name, long date) {
		throw new RuntimeException("not implemented");
	}

	public void setHeader(String name, String value) {
		throw new RuntimeException("not implemented");
	}

	public void addHeader(String name, String value) {
		throw new RuntimeException("not implemented");
	}

	public void setIntHeader(String name, int value) {
		throw new RuntimeException("not implemented");
	}

	public void addIntHeader(String name, int value) {
		throw new RuntimeException("not implemented");
	}

	public void setStatus(int sc) {
		throw new RuntimeException("not implemented");
	}

	public void setStatus(int sc, String sm) {
		throw new RuntimeException("not implemented");
	}

	public Locale getLocale() {
		throw new RuntimeException("not implemented");
	}

	public void setLocale(Locale loc) {
		throw new RuntimeException("not implemented");
	}

	public void reset() {
		throw new RuntimeException("not implemented");
	}

	public int getBufferSize() {
		throw new RuntimeException("not implemented");
	}

	public void setBufferSize(int s) {
		throw new RuntimeException("not implemented");
	}

	public void resetBuffer() {
		throw new RuntimeException("not implemented");
	}

	public boolean isCommitted() {
		throw new RuntimeException("not implemented");
	}

	public void setContentType(String s) {
		throw new RuntimeException("not implemented");
	}

	public void setContentLength(int nn) {
		throw new RuntimeException("not implemented");
	}

	public void setCharacterEncoding(String s) {
		throw new RuntimeException("not implemented");
	}

    public ServletOutputStream getOutputStream() throws IOException {
		throw new RuntimeException("not implemented");
	}

    public String getCharacterEncoding () {
		throw new RuntimeException("not implemented");
	}

    public String getContentType () {
		throw new RuntimeException("not implemented");
	}
}
