package contextquickie.preferences;

/**
 * @author ContextQuickie
 *
 *         Class which collects all settings for Tortoise tools.
 */
public class TortoisePreferenceConstants
{
  /**
   * The prefix which is used to generate the settings.
   */
  private String prefix;

  /**
   * The name of a directory which is used to detect a working copy folder.
   */
  private String workingCopyFolderName;

  /**
   * The directory in the registry which contains the user specific settings.
   */
  private String registryUserDirectory;

  /**
   * Creates a new instance of the {@link TortoisePreferenceConstants} class.
   * The directory in the registry which contains the user specific settings
   * must be identical to the settings prefix.
   * 
   * @param settingsPrefix
   *          The prefix which is used for generating the constants.
   * 
   * @param wcFolderName
   *          The name of the folder indicating a working copy.
   */
  public TortoisePreferenceConstants(final String settingsPrefix, final String wcFolderName)
  {
    this(settingsPrefix, wcFolderName, settingsPrefix);
  }

  /**
   * Creates a new instance of the {@link TortoisePreferenceConstants} class.
   * 
   * @param settingsPrefix
   *          The prefix which is used for generating the constants.
   * 
   * @param wcFolderName
   *          The name of the folder indicating a working copy.
   * 
   * @param regUserDir
   *          The directory in the registry which contains the user specific
   *          settings.
   */
  public TortoisePreferenceConstants(final String settingsPrefix, final String wcFolderName, final String regUserDir)
  {
    this.prefix = settingsPrefix;
    this.workingCopyFolderName = wcFolderName;
    this.registryUserDirectory = regUserDir;
  }

  /**
   * @return Configuration item for the enabled/disabled switch.
   */
  public String getEnabled()
  {
    return this.prefix + "Enabled";
  }

  /**
   * @return Configuration item for the enabled/disabled switch for scanning for
   *         linked resources.
   */
  public String getScanForLinkedResources()
  {
    return this.prefix + "ScanForLinkedResources";
  }

  /**
   * @return Configuration item for the path to the Tortoise executable.
   */
  public String getPath()
  {
    return this.prefix + "Path";
  }

  /**
   * @return Configuration item for the path to the Tortoise merge executable.
   */
  public String getMergePath()
  {
    return this.prefix + "MergePath";
  }

  /**
   * @return Configuration item which indicates if a working copy detection is
   *         performed or not.
   */
  public String getWorkingCopyDetection()
  {
    return this.prefix + "WorkingCopyDetection";
  }

  /**
   * @return Configuration item which indicates if the context menu
   *         configuration is taken from the registry.
   */
  public String getUseMenuConfigFromRegistry()
  {
    return this.prefix + "UseMenuConfigFromRegistry";
  }

  /**
   * @return The name of the folder indicating a working copy.
   */
  public String getWorkingCopyFolderName()
  {
    return this.workingCopyFolderName;
  }

  /**
   * @return The directory in the registry which contains the user specific settings.
   */
  public String getRegistryUserDirectory()
  {
    return "HKEY_CURRENT_USER\\Software\\" + this.registryUserDirectory;
  }
}
