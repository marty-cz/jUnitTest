package com.siemens.cz.junittest.shop.receipe;

import com.siemens.cz.junittest.shop.Product;
import com.siemens.cz.junittest.shop.Tax;

public class PriceCalculator {

	private Receipe receipe;

	public PriceCalculator(Receipe receipe) {
		this.receipe = receipe;
	}

	public Double calculateTotal() {
		return receipe.getProducts().stream().mapToDouble(p -> p.getPrice() * (1.0 + p.getTax().getTaxValue())).sum();
	}

	public Double calculateSubTotal() {
		return receipe.getProducts().stream().mapToDouble(Product::getPrice).sum();
	}

	public Double calculateSumOfGstTax() {
		return calculateTaxSize(Tax.GST);
	}

	public Double calculateSumOfPstTax() {
		return calculateTaxSize(Tax.PST);
	}

	private double calculateTaxSize(Tax taxType) {
		return receipe.getProducts().stream().filter(p -> p.getTax() == taxType)
				.mapToDouble(p -> p.getPrice() * p.getTax().getTaxValue()).sum();
	}

}
