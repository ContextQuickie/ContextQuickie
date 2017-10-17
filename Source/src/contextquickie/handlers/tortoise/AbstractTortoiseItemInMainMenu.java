package contextquickie.handlers.tortoise;

import contextquickie.Activator;
import contextquickie.preferences.TortoisePreferenceConstants;
import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.jface.preference.IPreferenceStore;

/**
 * @author ContextQuickie
 *
 *         Property tester which checks if a specific Tortoise entry is part of
 *         the main menu or not. It is used to show/hide the Tortoise add-in
 *         context menu entries.
 */
public abstract class AbstractTortoiseItemInMainMenu extends PropertyTester
{
  /**
   * The preferences of the current instance.
   */
  private TortoisePreferenceConstants preferences;

  /**
   * Default constructor.
   * 
   * @param tortoisePreferences
   *          The preferences which are required for execution.
   */
  protected AbstractTortoiseItemInMainMenu(final TortoisePreferenceConstants tortoisePreferences)
  {
    this.preferences = tortoisePreferences;
  }

  @Override
  public final boolean test(final Object receiver, final String property, final Object[] args, final Object expectedValue)
  {
    final IPreferenceStore preferenceStore = Activator.getDefault().getPreferenceStore();
    final boolean useMenuConfigFromRegistry = preferenceStore.getBoolean(this.preferences.getUseMenuConfigFromRegistry());

    if (useMenuConfigFromRegistry == true)
    {
      this.readSettingsFromRegistry();
    }

    final boolean entryIsInMainMenu = this.isEntryInMainMenu(args[0].toString());
    boolean result = entryIsInMainMenu;
    if (expectedValue != null)
    {
      if (entryIsInMainMenu == (Boolean) expectedValue)
      {
        result = true;
      }
      else
      {
        result = false;
      }
    }

    return result;
  }

  /**
   * Reads the context menu settings from the registry.
   */
  protected abstract void readSettingsFromRegistry();

  /**
   * Checks if the context menu entry is visible in the main menu.
   * 
   * @param entry
   *          The name of the entry.
   * @return <b>true</b> if the menu entry is visible in the main menu;
   *         otherwise false.
   */
  protected abstract boolean isEntryInMainMenu(String entry);
}
