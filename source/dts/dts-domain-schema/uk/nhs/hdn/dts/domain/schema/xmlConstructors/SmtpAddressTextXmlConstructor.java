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

package uk.nhs.hdn.dts.domain.schema.xmlConstructors;

import org.jetbrains.annotations.NotNull;
import uk.nhs.hdn.common.parsers.xml.xmlParseEventHandlers.xmlConstructors.TextXmlConstructor;
import uk.nhs.hdn.common.parsers.xml.xmlParseEventHandlers.xmlConstructors.XmlConstructor;
import uk.nhs.hdn.common.xml.XmlSchemaViolationException;
import uk.nhs.hdn.dts.domain.SmtpAddress;

import static uk.nhs.hdn.dts.domain.SmtpAddress.UnknownSmtpAddress;

public final class SmtpAddressTextXmlConstructor extends TextXmlConstructor<SmtpAddress>
{
	@NotNull
	public static final XmlConstructor<?, ?> SmtpAddressTextXmlConstructorInstance = new SmtpAddressTextXmlConstructor();

	private SmtpAddressTextXmlConstructor()
	{
		super(UnknownSmtpAddress, SmtpAddress.class);
	}

	@NotNull
	@Override
	protected SmtpAddress convert(@NotNull final String text) throws XmlSchemaViolationException
	{
		try
		{
			return new SmtpAddress(text);
		}
		catch (RuntimeException e)
		{
			throw new XmlSchemaViolationException(e);
		}
	}
}
