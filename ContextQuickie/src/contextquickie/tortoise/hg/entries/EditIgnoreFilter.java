package contextquickie.tortoise.hg.entries;

public class EditIgnoreFilter extends AbstractTortoiseHgEntry
{
  /**
   * Constructor.
   * 
   * @param iconPath
   * The path containing the icon for this instance.
   */
  public EditIgnoreFilter(String iconPath)
  {
    this.setCommand("hgignore");
    this.setLabel("Edit Ignore Filter");
    this.setHelpText("Edit repository ignore filter");
    this.setIconPath(iconPath + "ignore.png");
    this.setMaxFileCount(0);
  }
}
