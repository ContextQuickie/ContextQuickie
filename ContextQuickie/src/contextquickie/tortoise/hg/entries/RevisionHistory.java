package contextquickie.tortoise.hg.entries;

public class RevisionHistory extends AbstractTortoiseHgEntry
{
  /**
   * Constructor.
   * 
   * @param iconPath
   * The path containing the icon for this instance.
   */
  public RevisionHistory(String iconPath)
  {
    this.setCommand("log");
    this.setLabel("Revision History");
    this.setIconPath(iconPath + "menulog.png");
    this.setMinItemsCount(1);
  }
}
