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

package uk.nhs.hdn.common.parsers.json.jsonParseEventHandlers.constructors.arrayConstructors;

import org.jetbrains.annotations.NotNull;
import uk.nhs.hdn.common.parsers.json.jsonParseEventHandlers.schemaViolationInvalidJsonExceptions.SchemaViolationInvalidJsonException;

import java.math.BigDecimal;

public abstract class AbstractLongNumberArrayConstructor<X> extends AbstractNumberArrayConstructor<X>
{
	protected AbstractLongNumberArrayConstructor()
	{
	}

	@Override
	public final void addConstantNumberValue(@NotNull final X arrayCollector, final int index, @NotNull final BigDecimal value) throws SchemaViolationInvalidJsonException
	{
		final long longValue;
		try
		{
			longValue = value.longValueExact();
		}
		catch (ArithmeticException e)
		{
			throw new SchemaViolationInvalidJsonException("floating point and decimal numbers are not permitted", e);
		}
		addConstantNumberValue(arrayCollector, index, longValue);
	}
}
