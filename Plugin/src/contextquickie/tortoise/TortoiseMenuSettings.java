package contextquickie.tortoise;

/**
 * @author ContextQuickie
 *
 *         Class which stores Tortoise menu settings.
 */
public class TortoiseMenuSettings
{
  /**
   * The text of the sub menu.
   */
  private String subMenuText;
  
  /**
   * The path to the icon for the main menu.
   */
  private String subMenuIconPath;
  
  /**
   * The prefix for the main menu.
   */
  private String mainMenuPrefix;
  
  /**
   * The default value of the registry key ContextMenuEntries.
   */
  private long contextMenuEntriesDefault;
  
  /**
   * The default value of the registry key ContextMenuEntriesHigh.
   */
  private long contextMenuEntriesHighDefault;

  /**
   * @return The path to the icon for the sub menu.
   */
  public String getSubMenuIconPath()
  {
    return subMenuIconPath;
  }

  /**
   * @param value The path to the icon for the sub menu.
   */
  public void setSubMenuIconPath(final String value)
  {
    this.subMenuIconPath = value;
  }

  /**
   * @return The text of the sub menu.
   */
  public String getSubMenuText()
  {
    return subMenuText;
  }

  /**
   * @param value The text of the sub menu.
   */
  public void setSubMenuText(final String value)
  {
    this.subMenuText = value;
  }

  /**
   * @return The prefix for the main menu.
   */
  public String getMainMenuPrefix()
  {
    return mainMenuPrefix;
  }

  /**
   * @param value The prefix for the main menu.
   */
  public void setMainMenuPrefix(final String value)
  {
    this.mainMenuPrefix = value;
  }

  /**
   * @return The default value of the registry key ContextMenuEntries.
   */
  public long getContextMenuEntriesDefault()
  {
    return contextMenuEntriesDefault;
  }

  /**
   * @param value The default value of the registry key ContextMenuEntries.
   */
  public void setContextMenuEntriesDefault(final long value)
  {
    this.contextMenuEntriesDefault = value;
  }

  /**
   * @return The default value of the registry key ContextMenuEntriesHigh.
   */
  public long getContextMenuEntriesHighDefault()
  {
    return contextMenuEntriesHighDefault;
  }

  /**
   * @param value The default value of the registry key ContextMenuEntriesHigh.
   */
  public void setContextMenuEntriesHighDefault(final long value)
  {
    this.contextMenuEntriesHighDefault = value;
  }
}
