package contextquickie.tortoise.hg.entries;

public class AboutTortioseHg extends AbstractTortoiseHgEntry
{
  /**
   * Constructor.
   * 
   * @param iconPath
   * The path containing the icon for this instance.
   */
  public AboutTortioseHg(String iconPath)
  {
    this.setCommand("about");
    this.setLabel("About TortoiseHg");
    this.setHelpText("Show About Dialog");
    this.setIconPath(iconPath + "menuabout.png");
    this.setIsVisibleInWorkingCopy(true);
    this.setVisibleWithoutWorkingCopy(true);
  }
}
