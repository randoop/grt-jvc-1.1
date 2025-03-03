<?xml version="1.0" encoding="ISO-8859-1"?>
<web-app xmlns="http://java.sun.com/xml/ns/j2ee"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://java.sun.com/xml/ns/j2ee http://java.sun.com/xml/ns/j2ee/web-app_2_4.xsd"
    version="2.4">

    <display-name>JVC Request Dispatcher</display-name>
    <description>
    This servlet dispatches requests to JVC PageGenerators.
    </description>

    <servlet>
        <servlet-name>JVCDispatcher</servlet-name>
        <servlet-class>com.pmdesigns.jvc.JVCDispatcher</servlet-class>

        <init-param>
          <param-name>pkg_prefix</param-name>
          <param-value>PKG_PREFIX</param-value>
        </init-param>
    </servlet>

    <servlet-mapping>
        <servlet-name>JVCDispatcher</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>

</web-app>
