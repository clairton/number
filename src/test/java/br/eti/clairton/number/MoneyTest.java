package br.eti.clairton.number;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

import br.eti.clairton.number.Money;

public class MoneyTest {
	private Money real = new Money(2.236d);	

	@Test
	public void test() {
		assertEquals("R$ 2,24", real.toString());
	}	

	@Test
	public void testAdd() {
		assertEquals("R$ 7,56", real.add(new Money("5.324")).toString());
	}	

	@Test
	public void testSubtract() {
		assertEquals("-R$ 3,09", real.subtract(new Money("5.326")).toString());
	}	

	@Test
	public void testDivide() {
		assertEquals("R$ 0,62", real.divide(new BigDecimal(3.6)).toString());
	}
	

	@Test
	public void testMultiply() {
		assertEquals("R$ 6.708,47", real.multiply(new BigDecimal(3000.21)).toString());
	}

}
