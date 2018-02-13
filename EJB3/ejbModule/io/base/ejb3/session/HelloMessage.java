package io.base.ejb3.session;

import javax.ejb.Remote;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class HelloMessage
 */
@Stateless
@Remote(HelloMessageRemote.class)
public class HelloMessage implements HelloMessageRemote {
	
	/**
	 * Default constructor.
	 */
	public HelloMessage() {
	}

	@Override
	public String sayHello(String name) {
		System.out.println("From App EJB");
		return String.format("Hello %s, Douche bag!!!", name);
	}

	
	
}
