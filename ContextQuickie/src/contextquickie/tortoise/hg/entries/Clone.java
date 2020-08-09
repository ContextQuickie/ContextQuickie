package contextquickie.tortoise.hg.entries;

public class Clone extends AbstractTortoiseHgEntry
{
  /**
   * Constructor.
   * 
   * @param iconPath
   * The path containing the icon for this instance.
   */
  public Clone(String iconPath)
  {
    this.setCommand("clone");
    this.setLabel("Clone...");
    this.setHelpText("Create clone here from source");
    this.setIconPath(iconPath + "menuclone.ico");
    this.setIsVisibleInWorkingCopy(false);
    this.setVisibleWithoutWorkingCopy(true);
  }
}
