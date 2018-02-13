package io.base.listener;

import java.util.Enumeration;
import java.util.Iterator;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class ServletStatupEventListner
 *
 */
@WebListener
public class ServletStatupEventListner implements ServletContextListener {

	/**
	 * Default constructor.
	 */
	public ServletStatupEventListner() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("Starting Servlet context: " + arg0.getServletContext().getContextPath());
		System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		Enumeration<String> attributes = arg0.getServletContext().getAttributeNames();
		while (attributes.hasMoreElements()) {
			String name = (String) attributes.nextElement();
			System.out.println(name + " : " + arg0.getServletContext().getAttribute(name));
		}
		System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
	}

}
