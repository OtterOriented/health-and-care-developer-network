package org.gov.data.nhs.hcdn.barcodes;

import org.jetbrains.annotations.NotNull;

public enum Digit
{
	Zero(0),
	One(1),
	Two(2),
	Three(3),
	Four(4),
	Five(5),
	Six(6),
	Seven(7),
	Eight(8),
	Nine(9),
	;

	private static final int Utf16CodeForZero = 48;
	private final char asCharacter;
	private final String asString;

	@SuppressWarnings({"ClassWithoutConstructor", "UtilityClassWithoutPrivateConstructor"})
	private static final class CompilerWorkaround
	{
		private static final Digit[] Index = new Digit[10];
	}

	private final int digit;

	@SuppressWarnings("NumericCastThatLosesPrecision")
	Digit(final int digit)
	{
		this.digit = digit;
		CompilerWorkaround.Index[digit] = this;
		asCharacter = (char) (Utf16CodeForZero + this.digit);
		asString = Integer.toString(this.digit);
	}

	@SuppressWarnings("MethodNamesDifferingOnlyByCase")
	public int digit()
	{
		return digit;
	}

	public char toChar()
	{
		return asCharacter;
	}

	@SuppressWarnings("RefusedBequest")
	@Override
	@NotNull
	public String toString()
	{
		return asString;
	}

	@SuppressWarnings("MethodNamesDifferingOnlyByCase")
	@NotNull
	public static Digit digit(final int zeroBased)
	{
		if (zeroBased < 0 || zeroBased > 9)
		{
			throw new IllegalArgumentException("digit must be between 0 and 9");
		}
		return CompilerWorkaround.Index[zeroBased];
	}

	@NotNull
	public static Digit digitFromUtf16Code(final char utf16Code)
	{
		return digit((int) utf16Code - Utf16CodeForZero);
	}

	@NotNull
	public static Digit digitFromCodepoint(final int unicodeCodePoint)
	{
		return digit(unicodeCodePoint - Utf16CodeForZero);
	}
}
