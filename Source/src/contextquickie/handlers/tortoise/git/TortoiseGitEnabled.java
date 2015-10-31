package contextquickie.handlers.tortoise.git;

import contextquickie.handlers.tortoise.TortoiseEnabled;
import contextquickie.preferences.PreferenceConstants;

/**
 * @author ContextQuickie
 *
 *         Property tester which checks if Tortoise Git is enabled in the
 *         settings. It is used to show/hide the Tortoise Git context menu
 *         entries.
 */
public class TortoiseGitEnabled extends TortoiseEnabled {
	@Override
	protected String getPluginEnabledPreferenceName() {
		return PreferenceConstants.P_TORTOISE_GIT_ENABLED;
	}

	@Override
	protected String getPluginWorkingCopyDetectionPreferenceName() {
		return PreferenceConstants.P_TORTOISE_GIT_WORKING_COPY_DETECTION;
	}

	@Override
	protected String getWokringCopyFolderName() {
		return ".git";
	}
}
