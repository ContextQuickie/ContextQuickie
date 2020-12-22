package contextquickie.tortoise.svn.entries;

import contextquickie.tortoise.svn.MenuTextIdentifier;

public class RevisionGraph extends AbstractTortoiseSvnEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0000000000020000;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public RevisionGraph(String iconPath)
  {
    super(MenuTextIdentifier.IDS_MENUREVISIONGRAPH, "Revision graph");
    this.setMenuId(MenuIdentifier);;
    this.setIconPath(iconPath + "menurevisiongraph.ico");
    this.setCommand("revisiongraph");
    this.setMaxItemsCount(1);
    this.setSupportingLinkedResources(false);
  }
}