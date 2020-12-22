package contextquickie.tortoise.svn.entries;

public class Settings extends AbstractTortoiseSvnEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x2000000000000000L;

  /**
   * The menu text identifier for this class.
   */
  public static final int MenuTextIdentifier = 133;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public Settings(String iconPath, String iconExtension)
  {
    super(MenuTextIdentifier, "Settings");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menusettings" + iconExtension);
    this.setCommand("settings");
    this.setEntryRequiresPath(false);
    this.setVisibleWithoutWorkingCopy(true);
  }
}