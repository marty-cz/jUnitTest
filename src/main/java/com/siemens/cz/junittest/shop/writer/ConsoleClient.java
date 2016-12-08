package com.siemens.cz.junittest.shop.writer;

public class ConsoleClient implements IProductWriter {

	@Override
	public void write(String s) {
		System.out.println(s);
	}

}
