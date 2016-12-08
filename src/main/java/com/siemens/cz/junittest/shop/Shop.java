package com.siemens.cz.junittest.shop;

import com.siemens.cz.junittest.shop.formatters.IProductInfoFormatter;
import com.siemens.cz.junittest.shop.formatters.PriceFormatter;
import com.siemens.cz.junittest.shop.provider.BarcodeProvider;
import com.siemens.cz.junittest.shop.provider.CsvProductProvider;
import com.siemens.cz.junittest.shop.provider.IProductKeyProvider;
import com.siemens.cz.junittest.shop.provider.IProductProvider;
import com.siemens.cz.junittest.shop.writer.ConsoleClient;
import com.siemens.cz.junittest.shop.writer.IProductWriter;

public class Shop {

	private IProductProvider productProvider;
	private IProductInfoFormatter infoFormatter;
	private IProductWriter writer;

	public Shop() {
		productProvider = new CsvProductProvider();
		infoFormatter = new PriceFormatter();
		writer = new ConsoleClient();
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

	public void onProductKeyRead(String key) {
		if (key != null && !key.isEmpty()) {
			sellItem(key.trim());
		} else {
			System.err.println("Provided key is empty");
		}
	}

	private void sellItem(String key) {
		Product p = getProduct(key);
		if (p != null) {
			String info = getProductInfoFormater().format(p);
			getWriter().write(info);
		} else {
			System.err.println(String.format("Product with key '%s' is not found in catalog", key));
		}
	}

	public static void main(String[] args) {
		IProductKeyProvider keyProvider = new BarcodeProvider();
		Shop shop = new Shop();

		keyProvider.readProductKey(System.in, shop);
	}

}
