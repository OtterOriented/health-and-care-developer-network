package uk.nhs.hcdn.barcodes.gs1.barCodeTypes;

import uk.nhs.hcdn.barcodes.Alphanumeracy;
import uk.nhs.hcdn.barcodes.Directionality;
import uk.nhs.hcdn.barcodes.Numeracy;
import uk.nhs.hcdn.barcodes.gs1.globalTradeItemNumbers.GlobalTradeItemNumberFormat;
import uk.nhs.hcdn.barcodes.gs1.Gs1BarCodeFamily;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import uk.nhs.hcdn.common.EnumeratedVariableArgumentsHelper;

import java.util.Set;

import static java.util.Collections.emptySet;
import static uk.nhs.hcdn.barcodes.Alphanumeracy.NoneAlphanumeric;
import static uk.nhs.hcdn.barcodes.Numeracy.*;
import static uk.nhs.hcdn.barcodes.Directionality.Omnidirectional;
import static uk.nhs.hcdn.barcodes.gs1.Gs1BarCodeFamily.EANUPC;
import static uk.nhs.hcdn.barcodes.gs1.globalTradeItemNumbers.GlobalTradeItemNumberFormat.*;

public enum EanUpcGs1BarCodeType implements Gs1BarCodeType
{
	UpcAVersion("UPC-A Version", TwelveNumeric, GTIN_12),
	Ean13Version("EAN-13 Version", ThirteenNumeric, GTIN_13),
	UpcEVersion("UPC-E Version", TwelveNumeric, GTIN_12),
	Ean8Version("EAN-8 Version", EightNumeric, GTIN_8),
	;

	private static final Set<String> FormerNames = emptySet();

	@NotNull
	private final String actualName;

	@NotNull
	private final Set<GlobalTradeItemNumberFormat> globalTradeItemNumberFormats;

	@NotNull
	private final Numeracy numeracy;

	EanUpcGs1BarCodeType(@NonNls @NotNull final String actualName, @NotNull final Numeracy numeracy, @NotNull final GlobalTradeItemNumberFormat... globalTradeItemNumberFormats)
	{
		this.actualName = actualName;
		this.globalTradeItemNumberFormats = EnumeratedVariableArgumentsHelper.unmodifiableSetOf(globalTradeItemNumberFormats);
		this.numeracy = numeracy;
	}

	@NonNls
	@NotNull
	@Override
	public String actualName()
	{
		return actualName;
	}

	@SuppressWarnings("RefusedBequest")
	@Override
	@NotNull
	public String toString()
	{
		return actualName();
	}

	@SuppressWarnings("ReturnOfCollectionOrArrayField")
	@NotNull
	@Override
	public Set<String> formerActualNames()
	{
		return FormerNames;
	}

	@NotNull
	@Override
	public Gs1BarCodeFamily barCodeFamily()
	{
		return EANUPC;
	}

	@NotNull
	@Override
	public Numeracy numeracy()
	{
		return numeracy;
	}

	@NotNull
	@Override
	public Alphanumeracy alphanumeracy()
	{
		return NoneAlphanumeric;
	}

	@NotNull
	@Override
	public Directionality directionality()
	{
		return Omnidirectional;
	}

	@SuppressWarnings("ReturnOfCollectionOrArrayField")
	@NotNull
	@Override
	public Set<GlobalTradeItemNumberFormat> gtins()
	{
		return globalTradeItemNumberFormats;
	}
}
