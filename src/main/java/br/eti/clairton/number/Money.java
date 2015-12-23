package br.eti.clairton.number;

import static java.text.NumberFormat.getCurrencyInstance;
import static java.util.Locale.forLanguageTag;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;

public class Money extends BigDecimal {
	private static final long serialVersionUID = 1L;
	private final NumberFormat format;
	private final Integer scale;
	private final RoundingMode mode;

	public Money(final BigDecimal value) {
		this(value.toString());
	}

	public Money(final Double value) {
		this(value.toString());
	}

	public Money(final String value) {
		this(value, 2, RoundingMode.HALF_EVEN);
	}

	public Money(final Double value, final Integer scale, final RoundingMode mode) {
		this(value.toString(), scale, mode);
	}

	public Money(final String value, final Integer scale, final RoundingMode mode) {
		this(value, forLanguageTag("pt-BR"), scale, mode);
	}

	public Money(final String value, Locale locale, final Integer scale, final RoundingMode mode) {
		super(value);
		format = getCurrencyInstance(locale);
		format.setRoundingMode(mode);
		this.scale = scale;
		this.mode = mode;
	}
	
	@Override
	public BigDecimal subtract(final BigDecimal subtrahend) {
		return new Money(super.subtract(subtrahend).setScale(scale, mode));
	}

	@Override
	public BigDecimal add(final BigDecimal augend) {
		return new Money(super.add(augend).setScale(scale, mode));
	}
	
	@Override
	public BigDecimal multiply(final BigDecimal multiplicand) {
		return new Money(super.multiply(multiplicand).setScale(scale, mode));
	}
	
	@Override
	public BigDecimal divide(final BigDecimal divisor) {
		return new Money(super.divide(divisor, new MathContext(scale, mode)));
	}	
	
	@Override
	public String toString() {
		return format.format(this.doubleValue());
	}
}
