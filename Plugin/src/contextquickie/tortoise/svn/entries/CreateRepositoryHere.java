package contextquickie.tortoise.svn.entries;

import org.eclipse.core.runtime.IPath;

import contextquickie.tools.ContextMenuEnvironment;

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
    this.setMinFolderCount(1);
    this.setMaxFolderCount(1);
    this.setMaxItemsCount(1);
    this.setIsVisibleInWorkingCopy(false);
    this.setVisibleWithoutWorkingCopy(true);
  }

  @Override
  public boolean isVisible(ContextMenuEnvironment environment)
  {
    boolean isVisible = false;
    if (super.isVisible(environment))
    {
      IPath selectedDirectory = this.getEnvironment().getSelectedDirectories().iterator().next();
      if (selectedDirectory.toFile().list().length == 0)
      {
        isVisible = true;
      }
    }
    
    return isVisible;
  }
}