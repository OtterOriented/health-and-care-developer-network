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

package uk.nhs.hdn.common.http.server.sun.restEndpoints.resourceStateSnapshots;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uk.nhs.hdn.common.http.server.sun.restEndpoints.resourceStateSnapshots.resourceContents.ResourceContent;

import static uk.nhs.hdn.common.GregorianCalendarHelper.utcNow;

public final class NotFoundResourceStateSnapshot extends AbstractResourceStateSnapshot
{
	public NotFoundResourceStateSnapshot()
	{
		super(utcNow());
	}

	@NotNull
	@Override
	public ResourceContent content(@NotNull final String rawRelativeUriPath, @Nullable final String rawQueryString)
	{
		throw new UnsupportedOperationException("Should never be called");
	}
}