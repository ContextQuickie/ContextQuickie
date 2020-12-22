package contextquickie.tortoise.git.entries;

public class Paste extends AbstractTortoiseGitEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0000000400000000L;

  /**
   * The menu text identifier for this class.
   */
  public static final int MenuTextIdentifier = 225;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public Paste(String iconPath)
  {
    super(MenuTextIdentifier, "Paste");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menusendmail.ico");
    this.setCommand("settings");
    this.setMaxItemsCount(0);
  }
} // TODO: Disabled, also not supported by TortoiseGit