package contextquickie.tortoise.git.entries;

public class EditConflicts extends AbstractTortoiseGitEntry
{
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0000000000040000;

  /**
   * The menu text identifier for this class.
   */
  public static final int MenuTextIdentifier = 186;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public EditConflicts(String iconPath)
  {
    super(MenuTextIdentifier, "Edit conflicts");
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menuresolve.ico");
    this.setCommand("conflicteditor");
    this.setMaxItemsCount(0);
  }
} // TODO: Disabled