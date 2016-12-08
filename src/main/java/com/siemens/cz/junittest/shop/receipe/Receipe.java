package com.siemens.cz.junittest.shop.receipe;

import java.util.ArrayList;
import java.util.List;

import com.siemens.cz.junittest.shop.Product;

public class Receipe {

	private List<Product> products;

	public Receipe() {
		products = new ArrayList<>();
	}

	public void addProduct(Product p) {
		products.add(p);
	}

	public List<Product> getProducts() {
		return products;
	}
}
