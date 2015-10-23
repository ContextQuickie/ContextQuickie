package contextquickie.handlers.tortoise.svn;

import contextquickie.handlers.tortoise.TortoiseCommand;
import contextquickie.preferences.PreferenceConstants;

/**
 * @author ContextQuickie
 * 
 *         Class which executes all Tortoise SVN commands based on the passed
 *         parameters.
 */
public class TortoiseSVNCommand extends TortoiseCommand {
	@Override
	protected String getCommandPathName() {
		return PreferenceConstants.P_TORTOISE_SVN_PATH;
	}

	@Override
	protected String getCommandIdName() {
		return "ContextQuickie.commands.TortoiseSVN.TortoiseSVNCommand.CommandID";
	}

	@Override
	protected String getRequiresPathName() {
		return "ContextQuickie.commands.TortoiseSVN.TortoiseSVNCommand.RequiresPath";
	}

	@Override
	protected String getParameter1Name() {
		return "ContextQuickie.commands.TortoiseSVN.TortoiseSVNCommand.Parameter1";
	}
}
