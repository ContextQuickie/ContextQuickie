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

  /**
   * Configuration items for the configuration of Tortoise SVN.
   */
  public static final TortoisePreferenceConstants TORTOISE_SVN = new TortoisePreferenceConstants("TortoiseSvn", ".svn", "TortoiseSVN");

  /**
   * Configuration items for the configuration of Tortoise Git.
   */
  public static final TortoisePreferenceConstants TORTOISE_GIT = new TortoisePreferenceConstants("TortoiseGit", ".git");

  /**
   * Configuration items for the configuration of Tortoise Hg.
   */
  public static final TortoisePreferenceConstants TORTOISE_HG = new TortoisePreferenceConstants("TortoiseHg", ".hg");

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
