package test;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;


public class TestRequest implements HttpServletRequest {

	public String servletPath;
	public String contextPath;
	public String uri;
	public Cookie[] cookies;
	
	public TestRequest(String contextPath, String servletPath) {
		this.contextPath = contextPath;
		this.servletPath = servletPath;
		this.uri = contextPath + '/' + servletPath;
	}
	
	public String getServletPath() {
		return servletPath;
	}

	public String getContextPath() {
		return contextPath;
	}

	public String getRequestURI() {
		return uri;
	}

	public Cookie[] getCookies() {
		return cookies;
	}
	
	public String getAuthType() {
		throw new RuntimeException("not implemented");
	}

	public long getDateHeader(String name) {
		throw new RuntimeException("not implemented");
	}

	public String getHeader(String name) {
		throw new RuntimeException("not implemented");
	}

	public Enumeration getHeaders(String name) {
		throw new RuntimeException("not implemented");
	}

	public Enumeration getHeaderNames() {
		throw new RuntimeException("not implemented");
	}

	public int getIntHeader(String name) {
		throw new RuntimeException("not implemented");
	}

	public String getMethod() {
		throw new RuntimeException("not implemented");
	}

	public String getPathInfo() {
		throw new RuntimeException("not implemented");
	}

	public String getPathTranslated() {
		throw new RuntimeException("not implemented");
	}

	public String getQueryString() {
		throw new RuntimeException("not implemented");
	}

	public String getRemoteUser() {
		throw new RuntimeException("not implemented");
	}

	public boolean isUserInRole(String role) {
		throw new RuntimeException("not implemented");
	}

	public java.security.Principal getUserPrincipal() {
		throw new RuntimeException("not implemented");
	}

	public String getRequestedSessionId() {
		throw new RuntimeException("not implemented");
	}

	public StringBuffer getRequestURL() {
		throw new RuntimeException("not implemented");
	}

	public HttpSession getSession(boolean create) {
		throw new RuntimeException("not implemented");
	}

	public HttpSession getSession() {
		throw new RuntimeException("not implemented");
	}

	public boolean isRequestedSessionIdValid() {
		throw new RuntimeException("not implemented");
	}

	public boolean isRequestedSessionIdFromCookie() {
		throw new RuntimeException("not implemented");
	}

	public boolean isRequestedSessionIdFromURL() {
		throw new RuntimeException("not implemented");
	}

	public boolean isRequestedSessionIdFromUrl() {
		throw new RuntimeException("not implemented");
	}

	public int getLocalPort() {
		throw new RuntimeException("not implemented");
	}

	public String getLocalAddr() {
		throw new RuntimeException("not implemented");
	}

	public String getLocalName() {
		throw new RuntimeException("not implemented");
	}

	public int getRemotePort() {
		throw new RuntimeException("not implemented");
	}

    public String getRealPath(String path) {
		throw new RuntimeException("not implemented");
	}

	public javax.servlet.RequestDispatcher getRequestDispatcher(String s) {
		throw new RuntimeException("not implemented");
	}

	public boolean isSecure() {
		throw new RuntimeException("not implemented");
	}

	public Enumeration getLocales() {
		throw new RuntimeException("not implemented");
	}

	public Locale getLocale() {
		throw new RuntimeException("not implemented");
	}

	public void setLocale(Locale loc) {
		throw new RuntimeException("not implemented");
	}

	public void removeAttribute(String s) {
		throw new RuntimeException("not implemented");
	}

	public void setAttribute(String s, Object o) {
		throw new RuntimeException("not implemented");
	}

	public String getRemoteHost() {
		throw new RuntimeException("not implemented");
	}

	public String getRemoteAddr() {
		throw new RuntimeException("not implemented");
	}

    public Object getAttribute(String name) {
		throw new RuntimeException("not implemented");
	}

	public Enumeration getAttributeNames() {
		throw new RuntimeException("not implemented");
	}
		
    public String getCharacterEncoding () {
		throw new RuntimeException("not implemented");
	}
		
    public int getContentLength() {
		throw new RuntimeException("not implemented");
	}
		
    public String getContentType() {
		throw new RuntimeException("not implemented");
	}
		
    public ServletInputStream getInputStream() throws IOException {
		throw new RuntimeException("not implemented");
	}
		
    public String getParameter(String name) {
		throw new RuntimeException("not implemented");
	}
		
    public Enumeration getParameterNames() {
		throw new RuntimeException("not implemented");
	}
		
    public String[] getParameterValues(String name) {
		throw new RuntimeException("not implemented");
	}
		
    public String getProtocol() {
		throw new RuntimeException("not implemented");
	}
		
    public String getScheme() {
		throw new RuntimeException("not implemented");
	}
		
    public String getServerName() {
		throw new RuntimeException("not implemented");
	}
		
    public int getServerPort() {
		throw new RuntimeException("not implemented");
	}
		
    public BufferedReader getReader () throws IOException {
		throw new RuntimeException("not implemented");
	}

	public Map getParameterMap() {
		throw new RuntimeException("not implemented");
	}

	public void setCharacterEncoding(String s) {
		throw new RuntimeException("not implemented");
	}
} 

