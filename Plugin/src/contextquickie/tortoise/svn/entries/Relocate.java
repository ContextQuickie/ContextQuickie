package contextquickie.tortoise.svn.entries;

public class Relocate extends AbstractTortoiseSvnEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0000000000080000;

  /**
   * The menu text identifier for this class.
   */
  public static final int MenuTextIdentifier = 188;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public Relocate(String iconPath)
  {
    super(MenuTextIdentifier, "Relocate...");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menurelocate.ico");
    this.setCommand("relocate");
    this.setMaxItemsCount(0);
  }
} // TODO: Disabled