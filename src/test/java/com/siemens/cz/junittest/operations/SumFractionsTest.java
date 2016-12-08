package com.siemens.cz.junittest.operations;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import junit.framework.Assert;

public class SumFractionsTest {

	private static final List<Fraction[]> INPUT_DATA = new ArrayList<>();
	private static final List<Fraction> RESULT_DATA = new ArrayList<>();
	static {

		INPUT_DATA.add(new Fraction[] { new Fraction(0), new Fraction(0) });
		RESULT_DATA.add(new Fraction(0));

		INPUT_DATA.add(new Fraction[] { new Fraction(1, 2), new Fraction(1, 2) });
		RESULT_DATA.add(new Fraction(1));

		INPUT_DATA.add(new Fraction[] { new Fraction(2), new Fraction(-1, 2) });
		RESULT_DATA.add(new Fraction(3, 2));

		INPUT_DATA.add(new Fraction[] { new Fraction(3, 7), new Fraction(2, 3) });
		RESULT_DATA.add(new Fraction(23, 21));

		INPUT_DATA.add(new Fraction[] { new Fraction(0), new Fraction(2) });
		RESULT_DATA.add(new Fraction(2));

		INPUT_DATA.add(new Fraction[] { new Fraction(4), new Fraction(0) });
		RESULT_DATA.add(new Fraction(4));

		INPUT_DATA.add(new Fraction[] { new Fraction(3), new Fraction(6) });
		RESULT_DATA.add(new Fraction(9));

		INPUT_DATA.add(new Fraction[] { new Fraction(-1), new Fraction(-4) });
		RESULT_DATA.add(new Fraction(-5));

		INPUT_DATA.add(new Fraction[] { new Fraction(-2), new Fraction(7) });
		RESULT_DATA.add(new Fraction(5));

		// INPUT_DATA.add(new Fraction[] { new Fraction(Integer.MAX_VALUE), new
		// Fraction(1, 2) });
		// RESULT_DATA.add(new Fraction(Integer.MIN_VALUE + 1, 2));

	}

	@Test
	public void zero_denominator() throws Exception {
		try {
			new Fraction(7, 0);
			Assert.fail("Not possible state");
		} catch (IllegalArgumentException expected) {
		}
	}

	@Test
	public void sum_two_fractions() throws Exception {

		for (int i = 0; i < INPUT_DATA.size(); i++) {
			Fraction res = Fractions.sum(INPUT_DATA.get(i));

			String inDataStr = Arrays.stream(INPUT_DATA.get(i)).map(Object::toString)
					.collect(Collectors.joining(" and "));
			assertThat("(" + i + ") Fraction sum for fractions " + inDataStr, res, is(equalTo(RESULT_DATA.get(i))));
		}
	}

}
