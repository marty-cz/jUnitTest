package com.siemens.cz.junittest.shop.provider;

import com.siemens.cz.junittest.shop.Product;

public interface IProductProvider {

	Product findProductByKey(String key);
}
