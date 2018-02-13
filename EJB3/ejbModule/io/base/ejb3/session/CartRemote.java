package io.base.ejb3.session;

import java.util.List;

import javax.ejb.Remote;

import io.base.entity.Product;

@Remote
public interface CartRemote {
	public void addProductToCart(Product product);
	public List<Product> listProducts();
}
