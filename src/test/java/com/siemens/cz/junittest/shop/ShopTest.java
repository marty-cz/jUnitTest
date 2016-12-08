package com.siemens.cz.junittest.shop;

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import com.siemens.cz.junittest.shop.formatters.IProductInfoFormatter;
import com.siemens.cz.junittest.shop.provider.IProductKeyProvider;
import com.siemens.cz.junittest.shop.provider.IProductProvider;

public class ShopTest {

	private static final String TEST_KEY = "123456789";
	private static final Product TEST_PRODUCT = new Product(123.3d);
	private static Shop shop;

	@BeforeClass
	public static void setup() {
		IProductKeyProvider keyProvider = Mockito.mock(IProductKeyProvider.class);
		Mockito.when(keyProvider.readProductKey()).thenReturn(TEST_KEY);

		IProductProvider productProvider = Mockito.mock(IProductProvider.class);
		Mockito.when(productProvider.findProductByKey(Mockito.anyString())).thenReturn(TEST_PRODUCT);

		shop = Mockito.spy(new Shop());
		Mockito.when(shop.getProductKeyReader()).thenReturn(keyProvider);
		Mockito.when(shop.getProductProvider()).thenReturn(productProvider);
	}

	@Test
	public void sell_one_item() {
		shop.sellOneItem();
	}

	@Test
	public void read_product_key() {
		IProductKeyProvider keyProvider = shop.getProductKeyReader();

		String key = keyProvider.readProductKey();

		assertTrue("Product key was not properly readed", key != null && !key.isEmpty());
	}

	@Test
	public void find_product_by_key() {
		String key = TEST_KEY;

		Product p = shop.getProduct(key);

		assertTrue("Product was not found by key " + key, p != null);
	}

	@Test
	public void format_of_product_info() {
		IProductInfoFormatter formatter = shop.getProductInfoFormater();
		Product p = TEST_PRODUCT;

		String res = formatter.format(p);

		assertTrue("Product is not in EUR; " + p, res != null && res.startsWith("EUR "));
	}

}
