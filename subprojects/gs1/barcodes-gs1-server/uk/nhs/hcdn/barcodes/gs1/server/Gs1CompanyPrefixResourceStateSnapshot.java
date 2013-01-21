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

package uk.nhs.hcdn.barcodes.gs1.server;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uk.nhs.hcdn.barcodes.Digits;
import uk.nhs.hcdn.barcodes.gs1.checkDigits.IncorrectCheckDigitIllegalStateException;
import uk.nhs.hcdn.barcodes.gs1.checkDigits.IncorrectNumberOfDigitsIllegalStateException;
import uk.nhs.hcdn.barcodes.gs1.organisation.index.Gs1CompanyPrefixIndex;
import uk.nhs.hcdn.barcodes.gs1.organisation.index.Index;
import uk.nhs.hcdn.barcodes.gs1.organisation.Tuple;
import uk.nhs.hcdn.barcodes.gs1.keys.globalTradeItemNumbers.DigitsAreNotForAGlobalTradeItemNumberException;
import uk.nhs.hcdn.barcodes.gs1.keys.globalTradeItemNumbers.GlobalTradeItemNumber;
import uk.nhs.hcdn.barcodes.gs1.server.subResources.AllTuplesSubResource;
import uk.nhs.hcdn.barcodes.gs1.server.subResources.BarcodeSubResource;
import uk.nhs.hcdn.common.http.server.sun.restEndpoints.clientError4xxs.NotFoundException;
import uk.nhs.hcdn.common.http.server.sun.restEndpoints.resourceStateSnapshots.AbstractWithSubResourcesResourceStateSnapshot;
import uk.nhs.hcdn.common.http.server.sun.restEndpoints.resourceStateSnapshots.subResources.SubResource;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import static uk.nhs.hcdn.barcodes.Digits.digits;
import static uk.nhs.hcdn.barcodes.gs1.keys.globalTradeItemNumbers.GlobalTradeItemNumber.parseGlobalTradeItemNumber;

public final class Gs1CompanyPrefixResourceStateSnapshot extends AbstractWithSubResourcesResourceStateSnapshot
{
	@NotNull
	private final AllTuplesSubResource allTuplesSubResource;
	@NotNull
	private final Gs1CompanyPrefixIndex gs1CompanyPrefixIndex;
	@NotNull
	private final Map<Tuple, BarcodeSubResource> barcodeSubResourceIndex;

	public Gs1CompanyPrefixResourceStateSnapshot(@NotNull final GregorianCalendar lastModified, @NotNull final Tuple... tuples)
	{
		super(lastModified);
		allTuplesSubResource = new AllTuplesSubResource(tuples);
		gs1CompanyPrefixIndex = new Index(tuples);
		barcodeSubResourceIndex = new HashMap<>(tuples.length);
		for (final Tuple tuple : tuples)
		{
			barcodeSubResourceIndex.put(tuple, new BarcodeSubResource(tuple));
		}
	}

	@NotNull
	@Override
	public SubResource find(@NotNull final String rawPath) throws NotFoundException
	{
		if (rawPath.isEmpty())
		{
			return allTuplesSubResource;
		}
		if (rawPath.contains("/"))
		{
			throw new NotFoundException("/ is not used");
		}
		return barcodeSubResource(rawPath);
	}

	@NotNull
	private SubResource barcodeSubResource(@NotNull final CharSequence pathSegmentRepresentingBarcodeDigits) throws NotFoundException
	{
		final Digits digits;
		try
		{
			digits = digits(pathSegmentRepresentingBarcodeDigits);
		}
		catch (IllegalArgumentException e)
		{
			throw new NotFoundException("Barcode is not composed of digits 0-9", e);
		}

		final GlobalTradeItemNumber globalTradeItemNumber;
		try
		{
			globalTradeItemNumber = parseGlobalTradeItemNumber(digits, true);
		}
		catch (DigitsAreNotForAGlobalTradeItemNumberException e)
		{
			throw new NotFoundException("Digits are not for a GTIN", e);
		}
		catch (IncorrectNumberOfDigitsIllegalStateException e)
		{
			throw new NotFoundException("Incorrect number of digits", e);
		}
		catch (IncorrectCheckDigitIllegalStateException e)
		{
			throw new NotFoundException("Incorrect check digit", e);
		}

		@Nullable final Tuple tuple = gs1CompanyPrefixIndex.find(globalTradeItemNumber.gs1CompanyPrefixAndItem());
		if (tuple == null)
		{
			throw new NotFoundException("No matching GS1 Company Prefix");
		}
		return barcodeSubResourceIndex.get(tuple);
	}
}
