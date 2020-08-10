package contextquickie.tortoise.hg.entries;

public class ViewFileStatus extends AbstractTortoiseHgEntry
{
  /**
   * Constructor.
   * 
   * @param iconPath
   *          The path containing the icon for this instance.
   */
  public ViewFileStatus(String iconPath)
  {
    this.setCommand("status");
    this.setLabel("View File Status");
    this.setHelpText("Repository status & changes");
    this.setIconPath(iconPath + "menushowchanged.png");
  }
}
