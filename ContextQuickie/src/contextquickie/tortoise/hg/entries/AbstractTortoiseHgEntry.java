package contextquickie.tortoise.hg.entries;

import java.util.function.BiPredicate;

import contextquickie.tortoise.TortoiseEnvironment;
import contextquickie.tortoise.TortoiseMenuEntry;

public abstract class AbstractTortoiseHgEntry extends TortoiseMenuEntry implements BiPredicate<TortoiseMenuEntry, TortoiseEnvironment>
{
  /**
   * Constructor.
   */
  protected AbstractTortoiseHgEntry()
  {
    this.setMenuId(1); // TODO
    this.setCommandId("ContextQuickie.commands.TortoiseHg.TortoiseHgCommand");
    this.addVisibilityChecker(this);
  }

  @Override
  public boolean test(TortoiseMenuEntry t, TortoiseEnvironment u)
  {
    // TODO Auto-generated method stub
    return true;
  }
}
