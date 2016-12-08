package com.siemens.cz.junittest.shop;

import lombok.ToString;

@ToString(includeFieldNames = true)
public class Product {

	private double price;
	private Tax tax;
	private String barcode;

	public Product(String barcode, double price, Tax taxType) {
		this.barcode = barcode;
		this.price = price;
		this.tax = taxType;
	}

	public double getPrice() {
		return this.price;
	}

	public Tax getTax() {
		return tax;
	}

	public String getBarCode() {
		return barcode;
	}

}
