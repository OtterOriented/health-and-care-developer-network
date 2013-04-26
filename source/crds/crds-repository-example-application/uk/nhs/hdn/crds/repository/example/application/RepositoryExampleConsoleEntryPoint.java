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

package uk.nhs.hdn.crds.repository.example.application;

import joptsimple.OptionParser;
import joptsimple.OptionSet;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import uk.nhs.hdn.common.commandLine.AbstractConsoleEntryPoint;
import uk.nhs.hdn.crds.repository.example.DemonstrateRecordChangesRunnable;
import uk.nhs.hdn.common.sql.postgresql.PostgresqlConnectionHelper;
import uk.nhs.hdn.common.sql.postgresql.PostgresqlListenerRunnable;
import uk.nhs.hdn.common.sql.postgresql.ProcessNotificationUser;

import java.sql.SQLException;

import static java.lang.System.out;
import static uk.nhs.hdn.crds.repository.example.SchemaCreation.createOrReplaceSchema;

public final class RepositoryExampleConsoleEntryPoint extends AbstractConsoleEntryPoint
{
	@NonNls @NotNull private static final String AsDatasetIdsOption = "as-dataset-ids";

	@SuppressWarnings("UseOfSystemOutOrSystemErr")
	public static void main(@NotNull final String... commandLineArguments)
	{
		AbstractConsoleEntryPoint.execute(RepositoryExampleConsoleEntryPoint.class, commandLineArguments);
	}

	@Override
	protected boolean options(@NotNull final OptionParser options)
	{
		options.accepts(AsDatasetIdsOption, "returns results as Dataset Ids (UUIDs)").withOptionalArg().ofType(boolean.class).describedAs("true if as dataset ids; false or unspecified to produce dataset names");
		return true;
	}

	@Override
	protected void execute(@NotNull final OptionSet optionSet) throws SQLException
	{
		@SuppressWarnings("DuplicateStringLiteralInspection") final PostgresqlConnectionHelper postgresqlConnectionHelper = new PostgresqlConnectionHelper("localhost", "postgres", "repository", "postgres", "postgres");
		run(postgresqlConnectionHelper);
	}

	private static void run(final PostgresqlConnectionHelper postgresqlConnectionHelper) throws SQLException
	{
		try
		{
			createOrReplaceSchema(postgresqlConnectionHelper);

			new Thread(new DemonstrateRecordChangesRunnable(postgresqlConnectionHelper), "Demonstrate Record Changes so we have some live data to be notified about").start();

			try (PostgresqlListenerRunnable patients = new PostgresqlListenerRunnable(postgresqlConnectionHelper, "patients", new ProcessNotificationUser()
			{
				@SuppressWarnings("UseOfSystemOutOrSystemErr")
				@Override
				public void processNotification(@NotNull final String channel, @NotNull final String message)
				{
					out.printf("%1$s %2$s%n", channel, message);
				}
			}))
			{
				patients.run();
			}
		}
		finally
		{
			postgresqlConnectionHelper.close();
		}
	}

}