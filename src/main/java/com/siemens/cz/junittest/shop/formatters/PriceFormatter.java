package com.siemens.cz.junittest.shop.formatters;

import com.siemens.cz.junittest.shop.Product;

public class PriceFormatter implements IProductInfoFormatter {

	@Override
	public String format(Product p) {
		return String.format("EUR %.2f", p.getPrice());
	}

}
