package io.base.ejb3.session;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Remote;
import javax.ejb.Stateful;

import io.base.entity.Product;

/**
 * Session Bean implementation class Cart
 */
@Stateful
@Remote(CartRemote.class)
public class Cart implements CartRemote {

	/**
	 * Default constructor.
	 */
	public Cart() {
	}

	private List<Product> products;

	@PostConstruct
	public void initializeBean() {
		products = new ArrayList<>();
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	@Override
	public void addProductToCart(Product product) {
		System.out.println("Added product now... : " + product);
		getProducts().add(product);
	}

	@Override
	public List<Product> listProducts() {
		return getProducts();
	}

}
