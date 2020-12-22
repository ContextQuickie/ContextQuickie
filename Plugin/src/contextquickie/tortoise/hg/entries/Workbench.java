package contextquickie.tortoise.hg.entries;

public class Workbench extends AbstractTortoiseHgEntry
{
  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public Workbench(String iconPath)
  {
    this.setCommand("workbench");
    this.setLabel("Workbench");
    this.setHelpText("View change history in repository");
    this.setIconPath(iconPath + "menulog.png");
  }
}
