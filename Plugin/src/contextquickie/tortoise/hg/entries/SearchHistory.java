package contextquickie.tortoise.hg.entries;

public class SearchHistory extends AbstractTortoiseHgEntry
{
  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public SearchHistory(String iconPath)
  {
    this.setCommand("grep");
    this.setLabel("Search History");
    this.setHelpText("Search file revisions for patterns");
    this.setIconPath(iconPath + "menurepobrowse.png");
    this.setMaxFileCount(0);
  }
}
