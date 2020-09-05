package contextquickie.tortoise.hg.entries;

public class Update extends AbstractTortoiseHgEntry
{
  /**
   * Constructor.
   * 
   * @param iconPath
   * The path containing the icon for this instance.
   */
  public Update(String iconPath)
  {
    this.setCommand("update");
    this.setLabel("Update...");
    this.setHelpText("Update working directory");
    this.setIconPath(iconPath + "menucheckout.png");
    this.setMaxFileCount(0);
  }
}
