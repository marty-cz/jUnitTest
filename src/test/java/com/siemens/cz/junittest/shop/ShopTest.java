package com.siemens.cz.junittest.shop;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.siemens.cz.junittest.shop.formatters.IProductInfoFormatter;
import com.siemens.cz.junittest.shop.provider.BarcodeProvider;
import com.siemens.cz.junittest.shop.provider.IProductKeyProvider;

public class ShopTest {

	private static final String TEST_KEY = "123";
	private static final Product TEST_PRODUCT = new Product(123.3d);
	private Shop shop;

	@Before
	public void setup() {
		shop = Mockito.spy(new Shop());
	}

	@Test
	public void sell_one_item() {
		// shop.sellOneItem();
	}

	@Test
	public void read_one_empty_product_key() {
		IProductKeyProvider keyProvider = new BarcodeProvider();
		keyProvider.readProductKey(new ByteArrayInputStream("".getBytes()), shop);

		Mockito.verify(shop, Mockito.times(0)).onProductKeyRead(Mockito.anyString());
	}

	@Test
	public void read_one_invalid_product_key() {
		IProductKeyProvider keyProvider = new BarcodeProvider();
		keyProvider.readProductKey(new ByteArrayInputStream("lplpo".getBytes()), shop);

		Mockito.verify(shop, Mockito.times(0)).onProductKeyRead(Mockito.anyString());
	}

	@Test
	public void read_one_product_key() {
		IProductKeyProvider keyProvider = new BarcodeProvider();
		keyProvider.readProductKey(new ByteArrayInputStream(TEST_KEY.getBytes()), shop);

		Mockito.verify(shop, Mockito.times(1)).onProductKeyRead(Mockito.anyString());
	}

	@Test
	public void find_existing_product_by_key() {
		String key = TEST_KEY;

		Product p = shop.getProduct(key);

		assertTrue("Product was not found by key " + key, p != null);
	}

	@Test
	public void find_product_by_unknown_key() {
		String key = "pokopk";

		Product p = shop.getProduct(key);

		assertTrue("Product was found by key " + key + ", but it shouldn't", p == null);
	}

	@Test
	public void format_of_product_info() {
		IProductInfoFormatter formatter = shop.getProductInfoFormater();
		Product p = TEST_PRODUCT;

		String res = formatter.format(p);

		assertTrue("Product is not in EUR; " + p, res != null && res.startsWith("EUR "));
	}

}
