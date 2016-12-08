package com.siemens.cz.junittest.shop.provider;

import java.io.InputStream;

import com.siemens.cz.junittest.shop.Shop;

public interface IProductKeyProvider {
	void readProductKey(InputStream source, Shop shop);
}
