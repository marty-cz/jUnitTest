package com.siemens.cz.junittest.shop.writer;

public class ConsoleClient implements IProductWriter {

	@Override
	public void write(String s) {
		System.out.println(s);
	}

	@Override
	public void writePriceToPay(String s) {
		write("Total: " + s);
	}

	@Override
	public void writePriceToPayBeforeTax(String s) {
		write("SubTotal: " + s);
	}

	@Override
	public void writeValueOfPstTax(String s) {
		write("PST: " + s);
	}

	@Override
	public void writeValueOfGstTax(String s) {
		write("GST: " + s);
	}

}
