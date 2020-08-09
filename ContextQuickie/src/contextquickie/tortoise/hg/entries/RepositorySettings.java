package contextquickie.tortoise.hg.entries;

public class RepositorySettings extends AbstractTortoiseHgEntry
{
  /**
   * Constructor.
   * 
   * @param iconPath
   * The path containing the icon for this instance.
   */
  public RepositorySettings(String iconPath)
  {
    this.setCommand("repoconf");
    this.setLabel("Repository Settings");
    this.setHelpText("Configure repository settings");
    this.setIconPath(iconPath + "settings_repo.ico");
  }
}
