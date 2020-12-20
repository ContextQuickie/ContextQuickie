package contextquickie.tortoise.hg.entries;

public class AnnotateFiles extends AbstractTortoiseHgEntry
{
  /**
   * Constructor.
   * 
   * @param iconPath
   * The path containing the icon for this instance.
   */
  public AnnotateFiles(String iconPath)
  {
    this.setCommand("annotate");
    this.setLabel("Annotate Files");
    this.setIconPath(iconPath + "menublame.png");
    this.setMinItemsCount(1);
    this.setMaxItemsCount(1);
    this.setParameter1(Boolean.TRUE.toString());
  }
}
