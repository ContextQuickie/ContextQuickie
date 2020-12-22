package contextquickie.tortoise.svn.entries;

public class Help extends AbstractTortoiseSvnEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x4000000000000000L;

  /**
   * The menu text identifier for this class.
   */
  public static final int MenuTextIdentifier = 190;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public Help(String iconPath, String iconExtension)
  {
    super(MenuTextIdentifier, "Help");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menuhelp" + iconExtension);
    this.setCommand("help");
    this.setEntryRequiresPath(false);
    this.setVisibleWithoutWorkingCopy(true);
  }
}