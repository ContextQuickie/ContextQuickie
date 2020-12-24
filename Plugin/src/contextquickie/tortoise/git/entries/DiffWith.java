package contextquickie.tortoise.git.entries;

import java.io.File;

import contextquickie.tools.ContextMenuEnvironment;

public class DiffWith extends AbstractTortoiseGitEntry
{
  private static final String defaultLabel = "Diff with \"%ls\"";

  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0000020000000000L;

  /**
   * The menu text identifier for this class.
   */
  public static final int MenuTextIdentifier = 234;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public DiffWith(String iconPath)
  {
    super(MenuTextIdentifier, defaultLabel);
    this.setMenuId(MenuIdentifier);
    this.setIconPath(iconPath + "menucompare.ico");
    this.setMaxFileCount(1);
    this.setMaxItemsCount(1);
  }

  @Override
  public boolean isVisible(ContextMenuEnvironment environment)
  {
    Boolean isVisible = false;
    if (super.isVisible(environment))
    {
      final String leftSide = this.getLeftSideForDiff();
      if (leftSide != null)
      {
        String label = translation.getTranslatedString(MenuTextIdentifier, defaultLabel);
        label = label.replace("%ls", "%s");
        label = String.format(label, new File(leftSide).getName());
        this.setLabel(label);
        isVisible = true;
      }
    }

    return isVisible;
  }

  @Override
  public void executeCommand()
  {
    this.executeDiffWithLeftSideCommand();
  }
}
