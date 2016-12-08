package com.siemens.cz.junittest.shop.provider;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.ImmutableMap;
import com.siemens.cz.junittest.shop.Product;

public class CsvProductProvider implements IProductProvider {

	private static final Map<String, Product> catalog = ImmutableMap.of("123", new Product(23.2), "456",
			new Product(23.2), "789", new Product(23.2));

	@Override
	public Product findProductByKey(String key) {
		if (StringUtils.isNotEmpty(key)) {
			return catalog.get(key);
		}
		return null;
	}

}
