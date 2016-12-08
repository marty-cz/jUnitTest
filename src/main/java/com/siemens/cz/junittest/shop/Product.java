package com.siemens.cz.junittest.shop;

import lombok.ToString;

@ToString(includeFieldNames = true)
public class Product {

	private double price;

	public Product(double price) {
		this.price = price;
	}

	public double getPrice() {
		return this.price;
	}

}
