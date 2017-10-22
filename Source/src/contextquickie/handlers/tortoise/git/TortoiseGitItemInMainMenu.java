package contextquickie.handlers.tortoise.git;

import contextquickie.handlers.tortoise.AbstractTortoiseItemInMainMenu;
import contextquickie.preferences.PreferenceConstants;
import contextquickie.tools.Registry;

/**
 * @author ContextQuickie
 *
 *         Property tester which checks if a specific Tortoise Git entry is part
 *         of the main menu or not. It is used to show/hide the Tortoise add-in
 *         context menu entries.
 */
public class TortoiseGitItemInMainMenu extends AbstractTortoiseItemInMainMenu
{
  /**
   * Default constructor.
   * 
   */
  public TortoiseGitItemInMainMenu()
  {
    super(
        PreferenceConstants.TORTOISE_GIT, 
        TortoiseGitMenuItems.MENUCREATEREPOS | TortoiseGitMenuItems.MENUSYNC | TortoiseGitMenuItems.MENUCOMMIT,
        TortoiseGitMenuItems.MENUCLONE,
        TortoiseGitMenuItems.class);
  }

  @Override
  protected final void readSettingsFromRegistry()
  {
    final String registryLocation = "HKEY_CURRENT_USER\\Software\\TortoiseGit";
    String registryValue;

    registryValue = Registry.readKey(registryLocation, "ContextMenuEntries");
    if (registryValue != null)
    {
      this.setContextMenuEntries(Long.decode(registryValue));
    }

    registryValue = Registry.readKey(registryLocation, "ContextMenuEntrieshigh");
    if (registryValue != null)
    {
      this.setContextMenuEntriesHigh(Long.decode(registryValue));
    }
  }
}
