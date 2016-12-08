package com.siemens.cz.junittest.shop.provider;

import java.io.InputStream;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

import com.siemens.cz.junittest.shop.Shop;

public class BarcodeProvider implements IProductKeyProvider {

	@Override
	public void readProductKey(InputStream source, Shop shop) {
		try (Scanner in = new Scanner(source)) {
			while (in.hasNext()) {
				String input = in.nextLine();
				if (isBarcodeValid(input)) {
					shop.onProductKeyRead(input);
				} else if (isBillConfirmation(input)) {
					shop.onBillConfirmation();
				} else {
					System.err.println(String.format("Provided key '%s' is not valid barcode", input));
				}
			}
		}
	}

	protected static boolean isBarcodeValid(String key) {
		return StringUtils.isNotBlank(key) && StringUtils.isNumeric(key.trim());
	}

	protected static boolean isBillConfirmation(String s) {
		return StringUtils.isNotBlank(s) && "D".equalsIgnoreCase(s.trim());
	}

}
