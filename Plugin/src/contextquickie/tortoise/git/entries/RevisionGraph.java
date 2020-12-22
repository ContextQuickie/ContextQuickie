package contextquickie.tortoise.git.entries;

public class RevisionGraph extends AbstractTortoiseGitEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0100000000000000L;

  /**
   * The menu text identifier for this class.
   */
  public static final int MenuTextIdentifier = 367;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public RevisionGraph(String iconPath)
  {
    super(MenuTextIdentifier, "Revision graph");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menurevisiongraph.ico");
    this.setCommand("revisiongraph");
    this.setMaxItemsCount(1);
  }
}