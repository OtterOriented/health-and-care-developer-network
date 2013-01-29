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

package uk.nhs.hdn.common.serialisers;

import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import static java.lang.String.format;
import static java.util.Locale.ENGLISH;

public final class CouldNotWriteValueException extends AbstractDataException
{
	public CouldNotWriteValueException(@NotNull final Object value, @NotNull final AbstractDataException cause)
	{
		super(format(ENGLISH, "Could not write value %1$s", value), cause);
	}

	public CouldNotWriteValueException(@NotNull final Object value, @NonNls @NotNull final String because)
	{
		super(format(ENGLISH, "Could not write value %1$s because %2$s", value, because));
	}
}
