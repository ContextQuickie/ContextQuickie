package contextquickie.tortoise.svn.entries;

import contextquickie.tortoise.svn.MenuTextIdentifier;

public class Checkout extends AbstractTortoiseSvnEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0000000000000001;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public Checkout(String iconPath)
  {
    super(MenuTextIdentifier.IDS_MENUCHECKOUT, "Chekout...");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menucheckout.ico");
    this.setCommand("checkout");
    this.setIsVisibleInWorkingCopy(false);
    this.setVisibleWithoutWorkingCopy(true);
    this.setMaxFolderCount(1);
    this.setMaxFileCount(0);
    this.setSupportingLinkedResources(false);
  }
}