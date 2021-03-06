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

package uk.nhs.hdn.barcodes.gs1.client;

import org.jetbrains.annotations.NotNull;
import uk.nhs.hdn.barcodes.gs1.organisation.Tuple;
import uk.nhs.hdn.common.http.client.HttpClient;
import uk.nhs.hdn.common.http.client.JavaHttpClient;
import uk.nhs.hdn.common.http.client.getHttpResponseUsers.GetHttpResponseUser;
import uk.nhs.hdn.common.http.client.json.JsonGetHttpResponseUser;

import static uk.nhs.hdn.barcodes.gs1.organisation.TuplesArrayJsonSchema.TuplesSchemaUsingParserInstance;
import static uk.nhs.hdn.common.http.UrlHelper.toUrl;
import static uk.nhs.hdn.common.http.client.connectionConfigurations.ChunkedUploadsConnectionConfiguration.DoesNotSupportChunkedUploads;

public final class HttpClientHelper
{
	@NotNull
	public static final GetHttpResponseUser<Tuple[]> ResponseUser = new JsonGetHttpResponseUser<>(TuplesSchemaUsingParserInstance);

	private HttpClientHelper()
	{
	}

	@NotNull
	public static HttpClient forListAllKnownCompanyPrefixes(final boolean useHttps, @NotNull final String domainName, final char portNumber)
	{
		return new JavaHttpClient(toUrl(useHttps, domainName, portNumber, "/gs1/organisation/"), DoesNotSupportChunkedUploads);
	}

	@NotNull
	public static HttpClient forACompanyPrefix(final boolean useHttps, @NotNull final String hostname, final char portNumber, @NotNull final CharSequence digits)
	{
		return new JavaHttpClient(toUrl(useHttps, hostname, portNumber, "/gs1/organisation/" + digits), DoesNotSupportChunkedUploads);
	}
}
