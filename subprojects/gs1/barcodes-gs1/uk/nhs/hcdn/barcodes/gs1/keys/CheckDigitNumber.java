/*
 * © Crown copyright 2013
 * Licensed under the Apache License, Version 2.0 (http://www.apache.org/licenses/LICENSE-2.0.html)
 */

package uk.nhs.hcdn.barcodes.gs1.keys;

import org.jetbrains.annotations.NotNull;
import uk.nhs.hcdn.barcodes.Digit;
import uk.nhs.hcdn.barcodes.DigitList;

public interface CheckDigitNumber extends DigitList
{
	@SuppressWarnings("NonBooleanMethodNameMayNotStartWithQuestion")
	@NotNull
	Digit checkDigit();
}
