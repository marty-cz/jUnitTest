package com.siemens.cz.junittest.shop;

public enum Tax {
	NO_TAX(0.0d), PST(0.05d), GST(0.08d);

	private final double taxValue;

	private Tax(double taxValue) {
		this.taxValue = taxValue;
	}

	public double getTaxValue() {
		return taxValue;
	}

}
