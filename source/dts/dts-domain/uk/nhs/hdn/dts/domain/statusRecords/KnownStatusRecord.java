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

package uk.nhs.hdn.dts.domain.statusRecords;

import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uk.nhs.hdn.common.serialisers.CouldNotSerialiseMapException;
import uk.nhs.hdn.common.serialisers.CouldNotWritePropertyException;
import uk.nhs.hdn.common.serialisers.MapSerialiser;
import uk.nhs.hdn.common.unknown.AbstractIsUnknown;
import uk.nhs.hdn.dts.domain.statusRecords.dateTime.KnownDateTime;

public final class KnownStatusRecord extends AbstractIsUnknown implements StatusRecord
{
	@NotNull
	private final KnownDateTime dateTime;
	@NotNull
	private final Event event;
	@NotNull
	private final Status status;
	@NotNull
	private final StatusCode statusCode;
	@NotNull
	@NonNls
	private final String description;

	public KnownStatusRecord(@NotNull final KnownDateTime dateTime, @NotNull final Event event, @NotNull final Status status, @NotNull final StatusCode statusCode, @NonNls @NotNull final String description)
	{
		super(false);
		this.dateTime = dateTime;
		this.event = event;
		this.status = status;
		this.statusCode = statusCode;
		this.description = description;
	}

	@SuppressWarnings("FeatureEnvy")
	@Override
	public void serialiseMap(@NotNull final MapSerialiser mapSerialiser) throws CouldNotSerialiseMapException
	{
		try
		{
			mapSerialiser.writeProperty("DateTime", dateTime);
			mapSerialiser.writeProperty("Event", event);
			mapSerialiser.writeProperty("Status", status);
			mapSerialiser.writeProperty("StatusCode", statusCode);
			mapSerialiser.writeProperty("Description", description);
		}
		catch (CouldNotWritePropertyException e)
		{
			throw new CouldNotSerialiseMapException(this, e);
		}
	}

	@NotNull
	@Override
	public KnownDateTime dateTime()
	{
		return dateTime;
	}

	@NotNull
	@Override
	public Event event()
	{
		return event;
	}

	@Override
	public boolean isEvent(@NotNull final Event event)
	{
		return this.event == event;
	}

	@NotNull
	@Override
	public Status status()
	{
		return status;
	}

	@Override
	public boolean hasStatus(@NotNull final Status status)
	{
		return this.status == status;
	}

	@NotNull
	@Override
	public StatusCode statusCode()
	{
		return statusCode;
	}

	@Override
	public boolean hasStatusCode(@NotNull final StatusCode statusCode)
	{
		return this.statusCode.equals(statusCode);
	}

	@NotNull
	@Override
	public String description()
	{
		return description;
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

		final KnownStatusRecord that = (KnownStatusRecord) obj;

		if (!dateTime.equals(that.dateTime))
		{
			return false;
		}
		if (!description.equals(that.description))
		{
			return false;
		}
		if (event != that.event)
		{
			return false;
		}
		if (status != that.status)
		{
			return false;
		}
		if (!statusCode.equals(that.statusCode))
		{
			return false;
		}

		return true;
	}

	@Override
	public int hashCode()
	{
		int result = dateTime.hashCode();
		result = 31 * result + event.hashCode();
		result = 31 * result + status.hashCode();
		result = 31 * result + statusCode.hashCode();
		result = 31 * result + description.hashCode();
		return result;
	}
}
