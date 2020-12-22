package contextquickie.tortoise.hg.entries;

public class ShelveChanges extends AbstractTortoiseHgEntry
{
  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public ShelveChanges(String iconPath)
  {
    this.setCommand("shelve");
    this.setLabel("Shelve Changes");
    this.setHelpText("Move changes between working dir and patch");
    this.setIconPath(iconPath + "menucommit.png");
  }
}
