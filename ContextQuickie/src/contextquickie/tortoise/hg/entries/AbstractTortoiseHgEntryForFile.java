package contextquickie.tortoise.hg.entries;

public class AbstractTortoiseHgEntryForFile extends AbstractTortoiseHgEntry
{
  /**
   * Constructor.
   */
  protected AbstractTortoiseHgEntryForFile()
  {
    this.setMaxFileCount(1);
    this.setMinFileCount(1);
  }
}
