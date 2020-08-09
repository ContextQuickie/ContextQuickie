package contextquickie.tortoise.hg.entries;

public class GuessRenames extends AbstractTortoiseHgEntry
{
  /**
   * Constructor.
   * 
   * @param iconPath
   * The path containing the icon for this instance.
   */
  public GuessRenames(String iconPath)
  {
    this.setCommand("guess");
    this.setLabel("Guess Renames");
    this.setHelpText("Detect renames and copies");
    this.setIconPath(iconPath + "detect_rename.png");
  }
}
