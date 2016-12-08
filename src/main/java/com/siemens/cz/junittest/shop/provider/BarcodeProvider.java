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
				String key = in.nextLine();
				if (isBarcodeValid(key)) {
					shop.onProductKeyRead(key);
				} else {
					System.err.println(String.format("Provided key '%s' is not valid barcode", key));
				}
			}
		}
	}

	protected static boolean isBarcodeValid(String key) {
		return StringUtils.isNotBlank(key) && StringUtils.isNumeric(key.trim());
	}

}
