package contextquickie.handlers.tortoise.git;

import contextquickie.handlers.tortoise.TortoiseCommand;
import contextquickie.preferences.PreferenceConstants;

/**
 * @author ContextQuickie
 * 
 *         Class which executes all Tortoise Git commands based on the passed
 *         parameters.
 */
public class TortoiseGitCommand extends TortoiseCommand {
	@Override
	protected String getCommandPathName() {
		return PreferenceConstants.P_TORTOISE_GIT_PATH;
	}

	@Override
	protected String getCommandIdName() {
		return "ContextQuickie.commands.TortoiseGit.TortoiseGitCommand.CommandID";
	}

	@Override
	protected String getRequiresPathName() {
		return "ContextQuickie.commands.TortoiseGit.TortoiseGitCommand.RequiresPath";
	}

	@Override
	protected String getParameter1Name() {
		return "ContextQuickie.commands.TortoiseGit.TortoiseGitCommand.Parameter1";
	}
}
