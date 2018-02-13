package io.base.servlet;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Properties;

import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.base.ejb3.session.HelloMessage;
import io.base.ejb3.session.HelloMessageRemote;

/**
 * Servlet implementation class HelloWorld
 */
@WebServlet("/helloworld")
public class HelloWorld extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB(name = "java:jboss/exported/EJB3/HelloMessage")
	// @EJB(lookup =
	// "ejb:J2eeEjb3/HelloMessage!io.base.ejb3.session.HelloMessageRemote")
	private HelloMessageRemote helloMessageRemote;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HelloWorld() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			response.getWriter().append("Served at: ").append(request.getContextPath()).append(" {")
					.append(helloMessageRemote.sayHello("Fucker")).append("}").append(" [")
					.append(getMessageFromRemoteEjb3("Conventional Fucker")).append("]");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private String getMessageFromRemoteEjb3(String name) throws NamingException {
		String message = "blah blah...";
		message = lookupTextProcessorBean("ejb:").sayHello(name);
		return message;
	}

	private static HelloMessageRemote lookupTextProcessorBean(String namespace) throws NamingException {
		Context ctx = createInitialContext();
		String appName = "";
		String moduleName = "EJB3";
		String distinctName = "";
		String beanName = HelloMessage.class.getSimpleName();
		String viewClassName = HelloMessageRemote.class.getName();
		return (HelloMessageRemote) ctx.lookup(
				namespace + appName + "/" + moduleName + "/" + distinctName + "/" + beanName + "!" + viewClassName);
	}

	private static Context createInitialContext() throws NamingException {
		Properties jndiProperties = new Properties();
		jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
		jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		jndiProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
		jndiProperties.put("jboss.naming.client.ejb.context", true);
		return new InitialContext(jndiProperties);
	}

}
