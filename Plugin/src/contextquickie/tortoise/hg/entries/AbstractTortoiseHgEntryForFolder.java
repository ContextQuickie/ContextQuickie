package contextquickie.tortoise.hg.entries;

public abstract class AbstractTortoiseHgEntryForFolder extends AbstractTortoiseHgEntry
{
  /**
   * Constructor.
   */
  protected AbstractTortoiseHgEntryForFolder()
  {
    this.setMaxFolderCount(1);
    this.setMinFolderCount(1);
  }
}
