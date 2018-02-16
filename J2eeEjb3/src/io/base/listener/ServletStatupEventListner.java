package io.base.listener;

import java.util.Enumeration;

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
	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("Starting Servlet context: " + arg0.getServletContext().getContextPath());
		System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		Enumeration<String> attributes = arg0.getServletContext().getAttributeNames();
		while (attributes.hasMoreElements()) {
			String name = attributes.nextElement();
			System.out.println(name + " : " + arg0.getServletContext().getAttribute(name));
		}
		System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
	}

}
