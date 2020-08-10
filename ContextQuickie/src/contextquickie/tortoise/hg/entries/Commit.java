package contextquickie.tortoise.hg.entries;

public class Commit extends AbstractTortoiseHgEntry
{
  /**
   * Constructor.
   * 
   * @param iconPath
   * The path containing the icon for this instance.
   */
  public Commit(String iconPath)
  {
    this.setCommand("commit");
    this.setLabel("Commit...");
    this.setHelpText("Commit changes in repository");
    this.setIconPath(iconPath + "menucommit.png");
  }
}
