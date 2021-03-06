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

package uk.nhs.hdn.barcodes.gs1.organisation;

import org.jetbrains.annotations.NotNull;
import uk.nhs.hdn.common.parsers.json.jsonParseEventHandlers.constructors.objectConstructors.AbstractGenericObjectConstructor;
import uk.nhs.hdn.common.parsers.json.jsonParseEventHandlers.constructors.objectConstructors.ObjectConstructor;
import uk.nhs.hdn.common.parsers.json.jsonParseEventHandlers.schemaViolationInvalidJsonExceptions.SchemaViolationInvalidJsonException;

import java.util.EnumMap;
import java.util.Map;

import static java.lang.String.format;
import static java.util.Locale.ENGLISH;
import static uk.nhs.hdn.barcodes.gs1.organisation.AdditionalInformationKey.valueOf;

public final class AdditionalInformationObjectConstructor extends AbstractGenericObjectConstructor<AdditionalInformationKey>
{
	@NotNull
	public static final ObjectConstructor<?> AdditionalInformationObjectConstructorInstance = new AdditionalInformationObjectConstructor();

	private AdditionalInformationObjectConstructor()
	{
	}

	@NotNull
	@Override
	public Map<AdditionalInformationKey, Object> newCollector()
	{
		return new EnumMap<>(AdditionalInformationKey.class);
	}

	@Override
	public Object collect(@NotNull final Map<AdditionalInformationKey, Object> collector)
	{
		return new AdditionalInformation(false, collector);
	}

	@NotNull
	@Override
	protected AdditionalInformationKey convertKey(@NotNull final String key) throws SchemaViolationInvalidJsonException
	{
		try
		{
			return valueOf(key);
		}
		catch (IllegalArgumentException e)
		{
			throw new SchemaViolationInvalidJsonException(format(ENGLISH, "Additional information not recognised for key %1$s", key), e);
		}
	}

}
