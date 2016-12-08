package com.siemens.cz.junittest.shop.writer;

public interface IProductWriter {
	void write(String s);

	void writePriceToPay(String s);

	void writePriceToPayBeforeTax(String s);

	void writeValueOfPstTax(String s);

	void writeValueOfGstTax(String s);
}
