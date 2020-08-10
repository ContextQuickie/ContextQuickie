package contextquickie.tortoise.hg.entries;

public class CreateRepositoryHere extends AbstractTortoiseHgEntryForFolder
{
  /**
   * Constructor.
   * 
   * @param iconPath
   * The path containing the icon for this instance.
   */
  public CreateRepositoryHere(String iconPath)
  {
    this.setCommand("init");
    this.setLabel("Create Repository Here");
    this.setHelpText("Create a new repository");
    this.setIconPath(iconPath + "menucreaterepos.ico");
    this.setIsVisibleInWorkingCopy(false);
    this.setVisibleWithoutWorkingCopy(true);
  }
}
