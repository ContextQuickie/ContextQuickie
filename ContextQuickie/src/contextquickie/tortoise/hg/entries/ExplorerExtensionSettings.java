package contextquickie.tortoise.hg.entries;

public class ExplorerExtensionSettings extends AbstractTortoiseHgEntry
{
  /**
   * Constructor.
   * 
   * @param iconPath
   * The path containing the icon for this instance.
   */
  public ExplorerExtensionSettings(String iconPath)
  {
    this.setCommand("shellconf");
    this.setLabel("Explorer Extension Settings");
    this.setHelpText("Configure Explorer extension");
    this.setIconPath(iconPath + "settings_user.ico");
    this.setIsVisibleInWorkingCopy(true);
    this.setVisibleWithoutWorkingCopy(true);
    this.setMaxFileCount(0);
  }
}
