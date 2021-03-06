package contextquickie.tortoise.git.entries;

public class DeleteKeepLocal extends AbstractTortoiseGitEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0000000004000000;

  /**
   * The menu text identifier for this class.
   */
  public static final int MenuTextIdentifier = 271;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public DeleteKeepLocal(String iconPath, String iconExtension)
  {
    super(MenuTextIdentifier, "Delete (keep local)");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menudelete" + iconExtension);
    this.setCommand("remove");
    this.setParameter1("/keep");
  }
}