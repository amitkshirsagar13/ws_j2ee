package io.base.ejb3.session;

import javax.ejb.Remote;

@Remote
public interface HelloMessageRemote {
	public String sayHello(String name);
}
