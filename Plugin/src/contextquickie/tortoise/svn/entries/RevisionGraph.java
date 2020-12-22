package contextquickie.tortoise.svn.entries;

public class RevisionGraph extends AbstractTortoiseSvnEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0000000000020000;

  /**
   * The menu text identifier for this class.
   */
  public static final int MenuTextIdentifier = 233;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public RevisionGraph(String iconPath)
  {
    super(MenuTextIdentifier, "Revision graph");
    this.setMenuId(MenuIdentifier);;
    this.setIconPath(iconPath + "menurevisiongraph.ico");
    this.setCommand("revisiongraph");
    this.setMaxItemsCount(1);
    this.setSupportingLinkedResources(false);
  }
}