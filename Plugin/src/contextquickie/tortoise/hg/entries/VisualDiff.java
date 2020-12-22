package contextquickie.tortoise.hg.entries;

public class VisualDiff extends AbstractTortoiseHgEntry
{
  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public VisualDiff(String iconPath)
  {
    this.setCommand("vdiff");
    this.setLabel("Visual Diff");
    this.setHelpText("View changes using GUI diff tool");
    this.setIconPath(iconPath + "TortoiseMerge.png");
  }
}
