package contextquickie.preferences;

public class TortoisePreferenceConstants
{
  private String _prefix;
  
  private String _workingCopyFolderName;
  
  /**
   * Creates a new instance of the {@link TortoisePreferenceConstants} class.
   * @param prefix
   * The prefix which is used for generating the constants.
   * 
   * @param workingCopyFolderName
   * The name of the folder indicating a working copy.
   */
  public TortoisePreferenceConstants(String prefix, String workingCopyFolderName)
  {
    this._prefix = prefix;
    this._workingCopyFolderName = workingCopyFolderName;
  }
  
  /**
   * @return Configuration item for the enabled/disabled switch.
   */
  public String getEnabled()
  {
    return this._prefix + "Enabled";
  }
  
  /**
   * @return Configuration item for the enabled/disabled switch for scanning for linked resources.
   */
  public String getScanForLinkedResources()
  {
    return this._prefix + "ScanForLinkedResources";
  }
  
  /**
   * @return Configuration item for the path to the Tortoise executable.
   */
  public String getPath()
  {
    return this._prefix + "Path";
  }
  
  /**
   * @return Configuration item for the path to the Tortoise merge executable.
   */
  public String getMergePath()
  {
    return this._prefix + "MergePath";
  }
  
  /**
   * @return Configuration item which indicates if a working copy detection is performed or not.
   */
  public String getWorkingCopyDetection()
  {
    return this._prefix + "WorkingCopyDetection";
  }
  
  /**
   * @return Configuration item which indicates if the context menu configuration is taken from the registry.
   */
  public String getUseMenuConfigFromRegistry()
  {
    return this._prefix + "UseMenuConfigFromRegistry";
  }
  
  /**
   * @return The name of the folder indicating a working copy.
   */
  public String getWokringCopyFolderName()
  {
    return this._workingCopyFolderName;
  }
}
