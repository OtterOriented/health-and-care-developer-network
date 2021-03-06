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

package uk.nhs.hdn.common.hazelcast.hazelcastDataReaders;

import org.jetbrains.annotations.NotNull;

import java.io.DataInput;
import java.io.IOException;
import java.util.UUID;

public final class UuidHazelcastDataReader extends AbstractHazelcastDataReader<UUID>
{
	@NotNull public static final HazelcastDataReader<UUID> UuidHazelcastDataReaderInstance = new UuidHazelcastDataReader();

	private UuidHazelcastDataReader()
	{
	}

	@NotNull
	@Override
	public UUID readData(@NotNull final DataInput in) throws IOException
	{
		return new UUID(in.readLong(), in.readLong());
	}
}
