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

package uk.nhs.hdn.common.http.server.sun.restEndpoints;

import org.jetbrains.annotations.NotNull;
import uk.nhs.hdn.common.http.server.sun.restEndpoints.methodEndpoints.MethodEndpoint;
import uk.nhs.hdn.common.http.server.sun.restEndpoints.resourceStateSnapshots.ResourceStateSnapshot;

import static uk.nhs.hdn.common.http.server.sun.restEndpoints.methodEndpoints.NotFoundMethodEndpoint.NotFoundMethodEndpointInstance;
import static uk.nhs.hdn.common.http.server.sun.restEndpoints.resourceStateSnapshots.NotFoundResourceStateSnapshot.NotFoundResourceStateSnapshotInstance;

public final class RootDenialRestEndpoint extends AbstractMethodRoutingRestEndpoint<ResourceStateSnapshot>
{
	@NotNull public static final RootDenialRestEndpoint RootDenialRestEndpointInstance = new RootDenialRestEndpoint();

	private RootDenialRestEndpoint()
	{
		super("/", NoAuthentication);
	}

	@SuppressWarnings("unchecked")
	@NotNull
	@Override
	protected MethodEndpoint<ResourceStateSnapshot> methodEndpoint(@NotNull final String method)
	{
		return (MethodEndpoint<ResourceStateSnapshot>) NotFoundMethodEndpointInstance;
	}

	@NotNull
	@Override
	protected ResourceStateSnapshot snapshotOfStateThatIsInvariantForRequest()
	{
		return NotFoundResourceStateSnapshotInstance;
	}
}
