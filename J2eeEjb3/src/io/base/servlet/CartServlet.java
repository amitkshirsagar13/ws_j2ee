package io.base.servlet;

import java.io.IOException;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.base.ejb3.session.Cart;
import io.base.ejb3.session.CartRemote;
import io.base.entity.Product;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/cart")
public class CartServlet extends HttpServlet {
	private static final String EMPTY = "";
	private static final long serialVersionUID = 1L;
	private static final String CART_SESSION_KEY = "shoppingCart";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CartServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String productName = null;
		if (request.getParameter("product") != null && !request.getParameter("product").equalsIgnoreCase(EMPTY)) {
			productName = request.getParameter("product");
		}
		response.getWriter().append("Served at: ").append(request.getContextPath());

		CartRemote cartRemote = (CartRemote) request.getSession().getAttribute(CART_SESSION_KEY);
		if (cartRemote == null) {
			try {
				cartRemote = lookupCartRemote("ejb:");
			} catch (NamingException e) {
				e.printStackTrace();
			}
			request.getSession().setAttribute(CART_SESSION_KEY, cartRemote);

		}

		if (productName != null) {
			Product product = new Product().setName(productName);
			System.out.println("\t- Added " + product + " to cart...");
			cartRemote.addProductToCart(product);
			response.getWriter().append("\n\t").append(" - Added " + product + " to cart...");
		}
		boolean listProduct = false;
		if (request.getParameter("list") != null && !request.getParameter("list").equalsIgnoreCase(EMPTY)) {
			listProduct = true;
		}

		if (listProduct) {
			response.getWriter().append("\n\n");
			for (Product product : cartRemote.listProducts()) {
				System.out.println(product);
				response.getWriter().append("\t- " + product + "\n");
			}
		}
	}

	private static CartRemote lookupCartRemote(String namespace) throws NamingException {
		Context ctx = createInitialContext();
		String appName = "";
		String moduleName = "EJB3";
		String distinctName = "";
		String beanName = Cart.class.getSimpleName();
		String viewClassName = CartRemote.class.getName();
		return (CartRemote) ctx.lookup(namespace + appName + "/" + moduleName + "/" + distinctName + "/" + beanName
				+ "!" + viewClassName + "?stateful");
	}

	private static Context createInitialContext() throws NamingException {
		Properties jndiProperties = new Properties();
		jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
		jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		jndiProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
		jndiProperties.put("jboss.naming.client.ejb.context", true);
		return new InitialContext(jndiProperties);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
