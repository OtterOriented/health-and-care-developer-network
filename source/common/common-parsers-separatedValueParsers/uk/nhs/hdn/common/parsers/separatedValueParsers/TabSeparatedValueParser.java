/*
 * © Crown Copyright 2013
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.nhs.hdn.common.parsers.separatedValueParsers;

import org.jetbrains.annotations.NotNull;
import uk.nhs.hdn.common.MillisecondsSince1970;
import uk.nhs.hdn.common.parsers.CouldNotParseException;
import uk.nhs.hdn.common.parsers.Parser;
import uk.nhs.hdn.common.parsers.separatedValueParsers.fieldParsers.CouldNotParseFieldException;
import uk.nhs.hdn.common.parsers.separatedValueParsers.lineParsers.CouldNotParseLineException;
import uk.nhs.hdn.common.parsers.separatedValueParsers.separatedValuesParseEventHandlers.SeparatedValueParseEventHandler;
import uk.nhs.hdn.common.reflection.toString.AbstractToString;

import java.io.IOException;
import java.io.Reader;

public final class TabSeparatedValueParser<S> extends AbstractToString implements Parser
{
	private static final int EndOfStream = -1;
	private static final char HorizontalTab = '\t';
	private static final char CarriageReturn = '\r';
	private static final char LineFeed = '\n';
	private static final int GuessOfBufferSize = 4096;

	@NotNull
	private final SeparatedValueParseEventHandler<S> separatedValueParseEventHandler;

	public TabSeparatedValueParser(@NotNull final SeparatedValueParseEventHandler<S> separatedValueParseEventHandler)
	{
		this.separatedValueParseEventHandler = separatedValueParseEventHandler;
	}

	@Override
	public void parse(@NotNull final Reader bufferedReader, @MillisecondsSince1970 final long lastModified) throws IOException, CouldNotParseException
	{
		final S state = separatedValueParseEventHandler.start(lastModified);
		parseRows(bufferedReader, state);
		separatedValueParseEventHandler.end(state);
	}

	@SuppressWarnings("NestedAssignment")
	private void parseRows(final Reader bufferedReader, final S state) throws IOException, CouldNotParseException
	{
		long offset = 0L;
		int characterAsInt;
		StringBuilder fieldStringBuilder = newStringBuilder();
		boolean expectingLineFeed = false;
		boolean afterStartOfNewLine = false;
		while ((characterAsInt = bufferedReader.read()) != EndOfStream)
		{
			@SuppressWarnings("NumericCastThatLosesPrecision") final char character = (char) characterAsInt;
			switch (characterAsInt)
			{
				case CarriageReturn:
					expectingLineFeed = true;
					fieldStringBuilder = endOfLine(offset, fieldStringBuilder, state);
					afterStartOfNewLine = false;
					break;

				case HorizontalTab:
					guardExpectingLineFeed(offset, expectingLineFeed);
					fieldStringBuilder = raiseField(offset, fieldStringBuilder, state);
					afterStartOfNewLine = true;
					break;

				case LineFeed:
					if (expectingLineFeed)
					{
						expectingLineFeed = false;
					}
					else
					{
						fieldStringBuilder = endOfLine(offset, fieldStringBuilder, state);
					}
					afterStartOfNewLine = false;
					break;

				default:
					guardExpectingLineFeed(offset, expectingLineFeed);
					fieldStringBuilder.append(character);
					afterStartOfNewLine = true;
					break;
			}
			offset++;
		}
		guardExpectingLineFeed(offset, expectingLineFeed);
		if (afterStartOfNewLine)
		{
			endOfLine(offset, fieldStringBuilder, state);
		}
	}

	private static void guardExpectingLineFeed(final long offset, final boolean expectingLineFeed) throws CouldNotParseException
	{
		if (expectingLineFeed)
		{
			throw new CouldNotParseException(offset, "CR line terminated files are not supported");
		}
	}

	private StringBuilder raiseField(final long offset, final StringBuilder fieldStringBuilder, final S state) throws CouldNotParseException
	{
		try
		{
			separatedValueParseEventHandler.field(state, fieldStringBuilder.toString());
		}
		catch (CouldNotParseFieldException e)
		{
			throw new CouldNotParseException(offset, e);
		}
		return newStringBuilder();
	}

	private StringBuilder endOfLine(final long offset, final StringBuilder fieldStringBuilder, final S state) throws CouldNotParseException
	{
		final StringBuilder newStringBuilder;
		try
		{
			newStringBuilder = raiseField(offset, fieldStringBuilder, state);
		}
		catch (CouldNotParseException e)
		{
			throw new CouldNotParseException(offset, e);
		}
		try
		{
			separatedValueParseEventHandler.endOfLine(state);
		}
		catch (CouldNotParseLineException e)
		{
			throw new CouldNotParseException(offset, e);
		}
		return newStringBuilder;
	}

	private static StringBuilder newStringBuilder()
	{
		return new StringBuilder(GuessOfBufferSize);
	}
}
