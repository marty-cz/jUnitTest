package com.siemens.cz.junittest.shop;

import com.siemens.cz.junittest.shop.formatters.IReceipeFormatter;
import com.siemens.cz.junittest.shop.formatters.ReceipeFormatter;
import com.siemens.cz.junittest.shop.provider.BarcodeProvider;
import com.siemens.cz.junittest.shop.provider.CsvProductProvider;
import com.siemens.cz.junittest.shop.provider.IProductKeyProvider;
import com.siemens.cz.junittest.shop.provider.IProductProvider;
import com.siemens.cz.junittest.shop.receipe.PriceCalculator;
import com.siemens.cz.junittest.shop.receipe.Receipe;
import com.siemens.cz.junittest.shop.writer.ConsoleClient;
import com.siemens.cz.junittest.shop.writer.IProductWriter;

public class Shop {

	private IProductProvider productProvider;
	private IReceipeFormatter infoFormatter;
	private IProductWriter writer;

	private Receipe actualReceipe;

	public Shop() {
		productProvider = new CsvProductProvider();
		infoFormatter = new ReceipeFormatter();
		writer = new ConsoleClient();
		actualReceipe = new Receipe();
	}

	IProductProvider getProductProvider() {
		return productProvider;
	}

	IReceipeFormatter getProductInfoFormater() {
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
			actualReceipe.addProduct(p);
			String info = getProductInfoFormater().formatProductInfo(p);
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

	public void onBillConfirmation() {
		PriceCalculator calculator = new PriceCalculator(actualReceipe);
		ReceipeFormatter priceFormatter = new ReceipeFormatter();
		IProductWriter writer = getWriter();

		writer.writePriceToPayBeforeTax(priceFormatter.formatPrice(calculator.calculateSubTotal()));
		writer.writeValueOfGstTax(priceFormatter.formatPrice(calculator.calculateSumOfGstTax()));
		writer.writeValueOfPstTax(priceFormatter.formatPrice(calculator.calculateSumOfPstTax()));
		writer.writePriceToPay(priceFormatter.formatPrice(calculator.calculateTotal()));

		actualReceipe = new Receipe();
	}

}
