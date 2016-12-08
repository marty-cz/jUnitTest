package com.siemens.cz.junittest.shop.formatters;

import org.apache.commons.lang3.StringUtils;

import com.siemens.cz.junittest.shop.Product;
import com.siemens.cz.junittest.shop.Tax;

public class ReceipeFormatter implements IReceipeFormatter {

	@Override
	public String formatProductInfo(Product p) {
		return formatProductName(p) + " " + formatPrice(p.getPrice(), formatTaxInformation(p.getTax()));
	}

	public String formatPrice(Double priceToPay) {
		return formatPrice(priceToPay, null);
	}

	private String formatPrice(Double priceToPay, String taxInformation) {
		String res = String.format("%.2f", priceToPay);
		if (StringUtils.isNotBlank(taxInformation)) {
			res += String.format(" %s", taxInformation);
		}
		return res;
	}

	private String formatProductName(Product p) {
		return p.getBarCode();
	}

	private String formatTaxInformation(Tax tax) {
		switch (tax) {
		case GST:
			return "G";
		case PST:
			return "GP";
		default:
			break;
		}
		return null;
	}

}
