package com.siemens.cz.junittest.operations;

public class Fractions {

	public static Fraction sum(Fraction value1, Fraction value2) {
		Double top = (double) value1.getNumerator() * value2.getDenominator()
				+ (double) value2.getNumerator() * value1.getDenominator();

		Double bottom = (double) value1.getDenominator() * value2.getDenominator();
		return new Fraction(top.intValue(), bottom.intValue());
	}

	public static Fraction sum(Fraction... fractions) {
		Fraction res = new Fraction(0);
		for (int i = 0; i < fractions.length; i++) {
			res = sum(res, fractions[i]);
		}
		return res;
	}

}
