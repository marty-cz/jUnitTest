package com.siemens.cz.junittest.shop.provider;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import com.siemens.cz.junittest.shop.Product;
import com.siemens.cz.junittest.shop.Tax;

public class CsvProductProvider implements IProductProvider {

	private Map<String, Product> catalog;

	private static final List<Product> CATALOG_LIST = ImmutableList.of(new Product("123", 23.2, Tax.NO_TAX),
			new Product("456", 2.2, Tax.GST), new Product("789", 8.8, Tax.PST));

	public CsvProductProvider() {
		catalog = Maps.uniqueIndex(CATALOG_LIST, Product::getBarCode);
	}

	@Override
	public Product findProductByKey(String key) {
		if (StringUtils.isNotEmpty(key)) {
			return catalog.get(key);
		}
		return null;
	}

}
