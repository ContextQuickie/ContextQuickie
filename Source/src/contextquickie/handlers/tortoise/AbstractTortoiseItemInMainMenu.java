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
   * Value of the registry key ContextMenuEntries.
   */
  private long contextMenuEntries;

  /**
   * Value of the registry key ContextMenuEntriesHigh.
   */
  private long contextMenuEntriesHigh;
  
  /**
   * The class containing the menu item numbers.
   */
  private Class<?> menuItems;

  /**
   * Default constructor.
   * 
   * @param tortoisePreferences
   *          The preferences which are required for execution.
   * @param contextMenuEntriesDefault
   *          The default value of the registry key of ContextMenuEntries
   * @param contextMenuEntriesHighDefault
   *          The default value of the registry key of ContextMenuEntriesHigh
   * @param menuItemsClass
   *          The class containing the menu item numbers.
   */
  protected AbstractTortoiseItemInMainMenu(final TortoisePreferenceConstants tortoisePreferences, final long contextMenuEntriesDefault,
      final long contextMenuEntriesHighDefault, final Class<?> menuItemsClass)
  {
    this.preferences = tortoisePreferences;
    this.contextMenuEntries = contextMenuEntriesDefault;
    this.contextMenuEntriesHigh = contextMenuEntriesHighDefault;
    this.menuItems = menuItemsClass;
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
  private boolean isEntryInMainMenu(final String entry)
  {
    final long int32BitMaxValue = 0xFFFFFFFFL;
    long entryValue = 0;
    final long compareValue;
    boolean result = false;
    Exception reflectionException = null;
    try
    {
      entryValue = this.menuItems.getDeclaredField(entry).getLong(null);
    }
    catch (IllegalArgumentException e)
    {
      reflectionException = e;
    }
    catch (IllegalAccessException e)
    {
      reflectionException = e;
    }
    catch (NoSuchFieldException e)
    {
      reflectionException = e;
    }
    catch (SecurityException e)
    {
      reflectionException = e;
    }

    if (reflectionException != null)
    {
      reflectionException.printStackTrace();
    }
    else
    {
      if (entryValue > int32BitMaxValue)
      {
        entryValue = entryValue >> Integer.SIZE;
        compareValue = this.contextMenuEntriesHigh;
      }
      else
      {
        compareValue = this.contextMenuEntries;
      }

      if ((entryValue & compareValue) != 0)
      {
        result = true;
      }
    }

    return result;
  }

  /**
   * Sets the value of the registry entry "ContextMenuEntries".
   * 
   * @param value
   *          The value.
   */
  protected final void setContextMenuEntries(final long value)
  {
    this.contextMenuEntries = value;
  }

  /**
   * Sets the value of the registry entry "ContextMenuEntriesHigh".
   * 
   * @param value
   *          The value.
   */
  protected final void setContextMenuEntriesHigh(final long value)
  {
    this.contextMenuEntriesHigh = value;
  }
}
