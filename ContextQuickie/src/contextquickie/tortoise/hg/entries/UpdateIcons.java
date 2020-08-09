package contextquickie.tortoise.hg.entries;

public class UpdateIcons extends AbstractTortoiseHgEntry
{
  /**
   * Constructor.
   * 
   * @param iconPath
   * The path containing the icon for this instance.
   */
  public UpdateIcons(String iconPath)
  {
    this.setCommand("thgstatus");
    this.setLabel("Update Icons");
    this.setHelpText("Update icons for this repository");
    this.setIconPath(iconPath + "refresh_overlays.png");
  }
}
