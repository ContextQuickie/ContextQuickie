package contextquickie.tortoise.hg.entries;

public class DndSynchronize extends AbstractTortoiseHgEntry
{
  /**
   * Constructor.
   * 
   * @param iconPath
   * The path containing the icon for this instance.
   */
  public DndSynchronize(String iconPath)
  {
    this.setCommand("dndsynch");
    this.setLabel("DnD Synchronize");
    this.setHelpText("Synchronize with dragged repository");
    this.setIconPath(iconPath + "menusynch.ico");
  }
}
