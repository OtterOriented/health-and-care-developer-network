/*
 * © Crown copyright 2013
 * Licensed under the Apache License, Version 2.0 (http://www.apache.org/licenses/LICENSE-2.0.html)
 */

package uk.nhs.hcdn.common.parsers;

import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import static java.lang.String.format;
import static java.util.Locale.ENGLISH;

public final class CouldNotParseException extends Exception
{
	public CouldNotParseException(final int offset, @NonNls @NotNull final String because)
	{
		super(format(ENGLISH, "Could not parse at offset %1$s because %2$s", offset, because));
	}

	public CouldNotParseException(final int offset, @NotNull final Exception cause)
	{
		super(format(ENGLISH, "Could not parse at offset %1$s because of exception %2$s", offset, cause.getMessage()), cause);
	}
}
