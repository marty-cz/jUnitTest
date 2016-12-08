package com.siemens.cz.junittest.operations;

public class Fraction {

	private int numerator;
	private int denominator;

	public Fraction(int value) {
		this(value, 1);
	}

	public Fraction(int numerator, int denominator) {
		if (denominator == 0) {
			throw new IllegalArgumentException("Fraction cannot be divided by 0");
		}
		this.numerator = numerator;
		this.denominator = denominator;
	}

	public int getNumerator() {
		return numerator;
	}

	public int getDenominator() {
		return denominator;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append(numerator);
		if (denominator != 1) {
			sb.append('/').append(denominator);
		}

		return sb.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + numerator;
		result = prime * result + denominator;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Fraction))
			return false;

		Fraction other = (Fraction) obj;
		Double value = (double) numerator / denominator;
		Double valueOther = (double) other.getNumerator() / other.getDenominator();

		return value.equals(valueOther);
	}

}
