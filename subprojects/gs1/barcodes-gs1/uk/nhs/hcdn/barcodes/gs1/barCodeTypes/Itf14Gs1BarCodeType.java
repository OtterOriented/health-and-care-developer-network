/*
 * © Crown copyright 2013
 * Licensed under the Apache License, Version 2.0 (http://www.apache.org/licenses/LICENSE-2.0.html)
 */

package uk.nhs.hcdn.barcodes.gs1.barCodeTypes;

import uk.nhs.hcdn.barcodes.Alphanumeracy;
import uk.nhs.hcdn.barcodes.Directionality;
import uk.nhs.hcdn.barcodes.Numeracy;
import uk.nhs.hcdn.barcodes.gs1.keys.globalTradeItemNumbers.GlobalTradeItemNumberFormat;
import uk.nhs.hcdn.barcodes.gs1.Gs1BarCodeFamily;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import uk.nhs.hcdn.common.EnumeratedVariableArgumentsHelper;

import java.util.Set;

import static java.util.Collections.emptySet;

public enum Itf14Gs1BarCodeType implements Gs1BarCodeType
{
	Itf14,
	;
	;

	private static final Set<String> FormerNames = emptySet();

	private static final Set<GlobalTradeItemNumberFormat> GlobalTradeItemNumberFormats = EnumeratedVariableArgumentsHelper.unmodifiableSetOf(GlobalTradeItemNumberFormat.GTIN_12, GlobalTradeItemNumberFormat.GTIN_13, GlobalTradeItemNumberFormat.GTIN_14);

	@NonNls
	@NotNull
	@Override
	public String actualName()
	{
		return "ITF-14";
	}

	@SuppressWarnings("RefusedBequest")
	@Override
	@NotNull
	public String toString()
	{
		return actualName();
	}

	@NotNull
	@Override
	public Gs1BarCodeFamily barCodeFamily()
	{
		return Gs1BarCodeFamily.ITF_14;
	}

	@NotNull
	@Override
	public Numeracy numeracy()
	{
		return Numeracy.FourteenNumeric;
	}

	@NotNull
	@Override
	public Alphanumeracy alphanumeracy()
	{
		return Alphanumeracy.NoneAlphanumeric;
	}

	@NotNull
	@Override
	public Directionality directionality()
	{
		return Directionality.NotOmnidirectional;
	}

	@SuppressWarnings("ReturnOfCollectionOrArrayField")
	@NotNull
	@Override
	public Set<GlobalTradeItemNumberFormat> gtins()
	{
		return GlobalTradeItemNumberFormats;
	}

	@Override
	public boolean canCarryApplicationIdentifiers()
	{
		return false;
	}

	@SuppressWarnings("ReturnOfCollectionOrArrayField")
	@NotNull
	@Override
	public Set<String> formerActualNames()
	{
		return FormerNames;
	}
}
