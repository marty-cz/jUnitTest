package com.siemens.cz.junittest.shop;

import com.siemens.cz.junittest.shop.formatters.IProductInfoFormatter;
import com.siemens.cz.junittest.shop.formatters.PriceFormatter;
import com.siemens.cz.junittest.shop.provider.BarcodeProvider;
import com.siemens.cz.junittest.shop.provider.CsvProductProvider;
import com.siemens.cz.junittest.shop.provider.IProductKeyProvider;
import com.siemens.cz.junittest.shop.provider.IProductProvider;
import com.siemens.cz.junittest.shop.writer.IProductWriter;
import com.siemens.cz.junittest.shop.writer.UdpClient;

public class Shop {

	private IProductKeyProvider productKeyProvider;
	private IProductProvider productProvider;
	private IProductInfoFormatter infoFormatter;
	private IProductWriter writer;

	public Shop() {
		productKeyProvider = new BarcodeProvider();
		productProvider = new CsvProductProvider();
		infoFormatter = new PriceFormatter();
		writer = new UdpClient();
	}

	IProductKeyProvider getProductKeyReader() {
		return productKeyProvider;
	}

	IProductProvider getProductProvider() {
		return productProvider;
	}

	IProductInfoFormatter getProductInfoFormater() {
		return infoFormatter;
	}

	IProductWriter getWriter() {
		return writer;
	}

	Product getProduct(String key) {
		IProductProvider productProvider = getProductProvider();
		return productProvider.findProductByKey(key);
	}

	public void sellOneItem() {
		String key = getProductKeyReader().readProductKey();
		if (key != null && !key.isEmpty()) {
			Product p = getProduct(key);
			if (p != null) {
				String info = getProductInfoFormater().format(p);
				getWriter().write(info);
			}
		}
	}

	public static void main(String[] args) {

		System.out.println("Hello World");

	}

}
