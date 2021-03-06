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

package uk.nhs.hdn.dbs.request.requestBodies;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uk.nhs.hdn.common.serialisers.CouldNotSerialiseMapException;
import uk.nhs.hdn.common.serialisers.CouldNotWritePropertyException;
import uk.nhs.hdn.common.serialisers.MapSerialiser;
import uk.nhs.hdn.dbs.*;

public class AdvancedTraceRequestBody extends AbstractRequestBody
{
	@NotNull
	private final NameFragment familyName;
	@NotNull
	private final NameFragment givenName;
	@NotNull
	private final Gender administrativeGender;

	public AdvancedTraceRequestBody(@NotNull final LocalPatientIdentifier localPatientIdentifier, @NotNull final DbsDate dateOfBirth, @NotNull final NameFragment familyName, @NotNull final NameFragment givenName, @NotNull final Gender administrativeGender)
	{
		super(localPatientIdentifier, dateOfBirth);
		this.familyName = familyName;
		this.givenName = givenName;
		this.administrativeGender = administrativeGender;
	}

	@SuppressWarnings("FeatureEnvy")
	@Override
	public void serialiseMap(@NotNull final MapSerialiser mapSerialiser) throws CouldNotSerialiseMapException
	{
		super.serialiseMap(mapSerialiser);
		try
		{
			mapSerialiser.writeProperty(FamilyNameField, familyName);
			mapSerialiser.writeProperty(GivenNameField, givenName);
			mapSerialiser.writeProperty(AdministrativeGenderField, administrativeGender);
		}
		catch (CouldNotWritePropertyException e)
		{
			throw new CouldNotSerialiseMapException(this, e);
		}
	}

	@Override
	public boolean equals(@Nullable final Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (obj == null || getClass() != obj.getClass())
		{
			return false;
		}
		if (!super.equals(obj))
		{
			return false;
		}

		final AdvancedTraceRequestBody that = (AdvancedTraceRequestBody) obj;

		if (administrativeGender != that.administrativeGender)
		{
			return false;
		}
		if (!familyName.equals(that.familyName))
		{
			return false;
		}
		if (!givenName.equals(that.givenName))
		{
			return false;
		}

		return true;
	}

	@Override
	public int hashCode()
	{
		int result = super.hashCode();
		result = 31 * result + familyName.hashCode();
		result = 31 * result + givenName.hashCode();
		result = 31 * result + administrativeGender.hashCode();
		return result;
	}
}
