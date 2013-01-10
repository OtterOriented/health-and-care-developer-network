package org.gov.data.nhs.hcdn.common.reflection;

import org.gov.data.nhs.hcdn.common.reflection.toString.AbstractToString;
import org.gov.data.nhs.hcdn.common.reflection.toString.ExcludeFromToString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;

public final class FieldInformation extends AbstractToString
{
	@SuppressWarnings("MethodNamesDifferingOnlyByCase")
	@NotNull
	public static FieldInformation fieldInformation(@NotNull final Field field)
	{
		return new FieldInformation(field);
	}

	@NotNull
	private final Field field;

	public FieldInformation(@NotNull final Field field)
	{
		this.field = field;
	}

	public boolean doesNotHaveAnnotation()
	{
		return field.getAnnotation(ExcludeFromToString.class) == null;
	}

	@Override
	public boolean equals(@Nullable final Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if ((obj == null) || (getClass() != obj.getClass()))
		{
			return false;
		}

		final FieldInformation that = (FieldInformation) obj;

		if (!field.equals(that.field))
		{
			return false;
		}

		return true;
	}

	@Override
	public int hashCode()
	{
		return field.hashCode();
	}
}
