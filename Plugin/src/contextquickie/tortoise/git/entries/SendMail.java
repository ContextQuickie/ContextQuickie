package contextquickie.tortoise.git.entries;

public class SendMail extends AbstractTortoiseGitEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0000000000000100;

  /**
   * The menu text identifier for this class.
   */
  public static final int MenuTextIdentifier = 321;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public SendMail(String iconPath)
  {
    super(MenuTextIdentifier, "Send Mail...");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menusendmail.ico");
    this.setCommand("settings");
    this.setMaxItemsCount(0);
  }
} // TODO: Disabled