package contextquickie.tortoise.hg.entries;

public class GlobalSettings extends AbstractTortoiseHgEntry
{
  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public GlobalSettings(String iconPath)
  {
    this.setCommand("userconf");
    this.setLabel("Global Settings");
    this.setHelpText("Configure user wide settings");
    this.setIconPath(iconPath + "settings_user.ico");
    this.setIsVisibleInWorkingCopy(true);
    this.setVisibleWithoutWorkingCopy(true);
    this.setMaxFileCount(0);
  }
}
