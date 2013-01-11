package uk.nhs.hcdn.common;

import static java.lang.StrictMath.pow;

public final class IntegerHelper
{
	@SuppressWarnings("NumericCastThatLosesPrecision")
	public static int power(final int x, final int n)
	{
		return (int) pow((double) x, (double) n);
	}

	public static boolean isEven(final int value)
	{
		return value % 2 == 0;
	}

	private IntegerHelper()
	{
	}
}
