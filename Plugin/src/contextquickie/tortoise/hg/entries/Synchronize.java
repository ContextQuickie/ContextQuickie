package contextquickie.tortoise.hg.entries;

public class Synchronize extends AbstractTortoiseHgEntryForFolder
{
  /**
   * Constructor.
   * 
   * @param iconPath
   * The path containing the icon for this instance.
   */
  public Synchronize(String iconPath)
  {
    this.setCommand("synch");
    this.setLabel("Synchronize");
    this.setHelpText("Synchronize with remote repository");
    this.setIconPath(iconPath + "menusynch.png");
  }
}
