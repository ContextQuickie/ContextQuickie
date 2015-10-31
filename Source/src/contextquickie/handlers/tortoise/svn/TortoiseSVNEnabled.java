package contextquickie.handlers.tortoise.svn;

import contextquickie.handlers.tortoise.TortoiseEnabled;
import contextquickie.preferences.PreferenceConstants;

/**
 * @author ContextQuickie
 *
 *         Property tester which checks if Tortoise SVN is enabled in the
 *         settings. It is used to show/hide the Tortoise SVN context menu
 *         entries.
 */
public class TortoiseSVNEnabled extends TortoiseEnabled {
	@Override
	protected String getPluginEnabledPreferenceName() {
		return PreferenceConstants.P_TORTOISE_SVN_ENABLED;
	}

	@Override
	protected String getPluginWorkingCopyDetectionPreferenceName() {
		return PreferenceConstants.P_TORTOISE_SVN_WORKING_COPY_DETECTION;
	}

	@Override
	protected String getWokringCopyFolderName() {
		return ".svn";
	}

}
