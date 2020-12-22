package contextquickie.tortoise.svn.entries;

import contextquickie.tortoise.svn.MenuTextIdentifier;

public class CreateRepositoryHere extends AbstractTortoiseSvnEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0000000000000400;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public CreateRepositoryHere(String iconPath)
  {
    super(MenuTextIdentifier.IDS_MENUCREATEREPOS, "Create repository here");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menucreaterepos.ico");
    this.setCommand("repocreate");
    this.setMaxItemsCount(0);
  }
} // TODO: Disabled