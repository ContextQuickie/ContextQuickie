package contextquickie.handlers.tortoise;

/**
 * @author ContextQuickie
 *
 *         Menu entry configuration for Tortoise.
 */
public class TortoiseMenuEntry
{
  /**
   * The label of the entry.
   */
  private String label;

  /**
   * The path to the entry icon.
   */
  private String iconPath;

  /**
   * The command ID.
   */
  private String commandId;

  /**
   * The menu ID.
   */
  private long menuId;

  /**
   * The command which will be passed to the Tortoise program.
   */
  private String entryCommand;
  
  /**
   * A value indicating whether the path must be passed to the Tortoise program.
   */
  private Boolean entryRequiresPath;

  /**
   * Default constructor.
   * 
   * @param entryLabel
   *          The label of the entry.
   * @param entryCommandId
   *          The command ID of the entry.
   * @param entryMenuId
   *          The menu identifier.
   * @param icon
   *          The menu icon.
   * @param command
   *          The command which will be passed to the Tortoise program.
   * @param requiresPath
   *          A value indicating whether the path must be passed to the Tortoise
   *          program.
   */
  public TortoiseMenuEntry(final String entryLabel, final String entryCommandId, final long entryMenuId, final String icon, final String command,
      final Boolean requiresPath)
  {
    this.setLabel(entryLabel);
    this.setCommandId(entryCommandId);
    this.setIconPath(icon);
    this.setMenuId(entryMenuId);
    this.setCommand(command);
    this.setEntryRequiresPath(requiresPath);
  }

  /**
   * Gets the label of the instance.
   * 
   * @return the label
   */
  public String getLabel()
  {
    return label;
  }

  /**
   * Sets the label of the instance.
   * 
   * @param value
   *          the label to set
   */
  public void setLabel(final String value)
  {
    this.label = value;
  }

  /**
   * Gets the icon path.
   * 
   * @return the iconPath
   */
  public String getIconPath()
  {
    return iconPath;
  }

  /**
   * Sets the icon path.
   * 
   * @param value
   *          the iconPath to set
   */
  public void setIconPath(final String value)
  {
    this.iconPath = value;
  }

  /**
   * Gets the command ID.
   * 
   * @return the commandId
   */
  public String getCommandId()
  {
    return commandId;
  }

  /**
   * Sets the command ID.
   * 
   * @param value
   *          the commandId to set
   */
  public void setCommandId(final String value)
  {
    this.commandId = value;
  }

  /**
   * Gets the menu ID.
   * 
   * @return the menuId
   */
  public long getMenuId()
  {
    return menuId;
  }

  /**
   * Sets the menu ID.
   * 
   * @param value
   *          the menuId to set
   */
  public void setMenuId(final long value)
  {
    this.menuId = value;
  }

  /**
   * @return The command which will be passed to the Tortoise program.
   */
  public String getCommand()
  {
    return entryCommand;
  }

  /**
   * @param value
   *          The command which will be passed to the Tortoise program.
   */
  public void setCommand(final String value)
  {
    this.entryCommand = value;
  }

  /**
   * @return A value indicating whether the path must be passed to the Tortoise program.
   */
  public Boolean getEntryRequiresPath()
  {
    return entryRequiresPath;
  }

  /**
   * @param value A value indicating whether the path must be passed to the Tortoise program.
   */
  public void setEntryRequiresPath(final Boolean value)
  {
    this.entryRequiresPath = value;
  }
}
