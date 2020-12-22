package contextquickie.tortoise.git.entries;

import java.io.File;

import contextquickie.preferences.PreferenceConstants;
import contextquickie.tools.ContextMenuEnvironment;
import rolandomagico.jniregistry.Registry;

public class DiffWith extends AbstractTortoiseGitEntry
{
  private String leftSideForCompare;
  
  private String rightSideForCompare;
  
  /**
   * The menu identifier for this class.
   */
  public static final long MenuIdentifier = 0x0000020000000000L;

  /**
   * The menu text identifier for this class.
   */
  public static final int MenuTextIdentifier = 232;

  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public DiffWith(String iconPath)
  {
    super(MenuTextIdentifier, "Diff later");
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
      if ((environment.getSelectedFiles().size() == 1) && (environment.getSelectedDirectories().isEmpty()))
      {
        String leftSide = new Registry().readStringValue(
            PreferenceConstants.TORTOISE_GIT.getRegistryUserDirectory(), 
            "DiffLater", 
            null);
        if (leftSide != null)
        {
          this.setLabel(translation.getTranslatedString(
              MenuTextIdentifier, "Diff with " + new File(leftSide).getName()));
          this.leftSideForCompare = leftSide;
          this.rightSideForCompare = environment.getSelectedFiles().iterator().next().toOSString();
          isVisible = true;
        }
      }
    }
    
    return isVisible;
  }
}
