package contextquickie.tortoise.svn.entries;

public class CreateRepositoryHere extends AbstractTortoiseSvnEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0000000000000400;

  /**
   * The menu text identifier for this class.
   */
  public static final int MenuTextIdentifier = 123;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public CreateRepositoryHere(String iconPath)
  {
    super(MenuTextIdentifier, "Create repository here");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menucreaterepos.ico");
    this.setCommand("repocreate");
    this.setMaxItemsCount(0);
  }
} // TODO: Disabled