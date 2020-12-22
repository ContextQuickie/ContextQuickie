package contextquickie.tortoise.git.entries;

public class SwitchCheckout extends AbstractTortoiseGitEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0000000000000080;

  /**
   * The menu text identifier for this class.
   */
  public static final int MenuTextIdentifier = 128;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public SwitchCheckout(String iconPath)
  {
    super(MenuTextIdentifier, "Switch/Checkout...");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menuswitch.ico");
    this.setCommand("switch");
    this.setMaxFolderCount(1);
    this.setMaxItemsCount(1);
  }
}