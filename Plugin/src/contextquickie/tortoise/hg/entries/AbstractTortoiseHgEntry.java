package contextquickie.tortoise.hg.entries;

import contextquickie.tortoise.TortoiseMenuEntry;

public abstract class AbstractTortoiseHgEntry extends TortoiseMenuEntry
{
  /**
   * Constructor.
   */
  protected AbstractTortoiseHgEntry()
  {
    this.setMenuId(1); // TODO
    this.setMinItemsCount(1);
    this.setMaxItemsCount(1);
  }
}
