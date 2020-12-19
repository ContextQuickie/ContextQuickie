package contextquickie.preferences;

/**
 * Constant definitions for plug-in preferences.
 */
public final class PreferenceConstants
{
  /**
   * Configuration item for enabling/disabling Beyond Compare.
   */
  public static final String P_BEYOND_COMPARE_ENABLED = "BeyondCompareEnabled";

  /**
   * Configuration item for the path to the Beyond Compare executable.
   */
  public static final String P_BEYOND_COMPARE_PATH = "BeyondComparePath";

  /**
   * Configuration item for the registry path containing the left side for
   * comparison.
   */
  public static final String P_BEYOND_COMPARE_SHELL_REG_PATH = "BeyondCompareShellRegistryPath";

  /**
   * Configuration item for the registry key containing the left side for
   * comparison.
   */
  public static final String P_BEYOND_COMPARE_SHELL_REG_KEY = "BeyondCompareShellRegistryKey";

  private static final String[] SupportedTortoiseHgVersions = new String[] { "5.5" };

  private static final String[] SupportedTortoiseGitVersions = new String[] { "2.6", "2.7", "2.8", "2.9", "2.10", "2.11" };

  private static final String[] SupportedTortoiseSvnVersions = new String[] { "1.8", "1.9", "1.10", "1.11", "1.12", "1.13", "1.14" };

  /**
   * Configuration items for the configuration of TortoiseSVN.
   */
  public static final TortoisePreferenceConstants TORTOISE_SVN = new TortoisePreferenceConstants("TortoiseSvn", ".svn", "TortoiseSVN", SupportedTortoiseSvnVersions);

  /**
   * Configuration items for the configuration of TortoiseGit.
   */
  public static final TortoisePreferenceConstants TORTOISE_GIT = new TortoisePreferenceConstants("TortoiseGit", ".git", SupportedTortoiseGitVersions);

  /**
   * Configuration items for the configuration of TortoiseHg.
   */
  public static final TortoisePreferenceConstants TORTOISE_HG = new TortoisePreferenceConstants("TortoiseHg", ".hg", SupportedTortoiseHgVersions);

  /**
   * Configuration item for enabling/disabling showing the progress for external tools.
   */
  public static final String SHOW_PROGRESS_FOR_EXTERNAL_TOOLS = "ShowProgressForExternalTools";

  /**
   * Configuration item for enabling/disabling refresh of the workspace after execution of external applications.
   */
  public static final String REFRESH_WORKSPACE_AFTER_EXECUTION = "RefreshWorkspaceAfterExecution";

  /**
   * Prevents from creating instances.
   */
  private PreferenceConstants()
  {
  }
}
