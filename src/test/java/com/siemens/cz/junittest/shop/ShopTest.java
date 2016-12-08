package com.siemens.cz.junittest.shop;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.siemens.cz.junittest.shop.formatters.IReceipeFormatter;
import com.siemens.cz.junittest.shop.provider.BarcodeProvider;
import com.siemens.cz.junittest.shop.provider.IProductKeyProvider;

public class ShopTest {

	private static final String TEST_KEY = "123";
	private static final Product TEST_PRODUCT = new Product(TEST_KEY, 123.3d, Tax.NO_TAX);
	private Shop shop;

	@Before
	public void setup() {
		shop = Mockito.spy(new Shop());
	}

	@Test
	public void sell_one_item() {
		shop.onProductKeyRead(TEST_KEY);

		Mockito.verify(shop, Mockito.times(1)).getWriter();
	}

	@Test
	public void sell_two_items() {
		shop.onProductKeyRead(TEST_KEY);
		shop.onProductKeyRead("456");
		shop.onBillConfirmation();

		Mockito.verify(shop, Mockito.times(3)).getWriter();
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
		IReceipeFormatter formatter = shop.getProductInfoFormater();
		Product p = TEST_PRODUCT;

		String res = formatter.formatProductInfo(p);

		assertTrue("Product is not in correct format 'ID PRICE TAX' : " + p,
				res != null && res.startsWith(TEST_KEY + " "));
	}

}
