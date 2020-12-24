package contextquickie.tortoise;

import contextquickie.tools.ContextMenuEnvironment;

/**
 * Class for storing the current Tortoise environment configuration.
 * 
 * @author ContextQuickie
 */
public class TortoiseEnvironment extends ContextMenuEnvironment
{
  /**
   * A value indicating whether a working copy has been found or not.
   */
  private boolean workingCopyFound;

  /**
   * The root directory of the working copy.
   */
  private String workingCopyRoot;
  
  /**
   * Constructor.
   * 
   * @param workingCopyFolderName
   *      The name of the folder indicating a working copy.
   */
  public TortoiseEnvironment(String workingCopyFolderName)
  {
    TortoiseWorkingCopyDetect workingCopyDetect = new TortoiseWorkingCopyDetect();
    if (workingCopyDetect.isAnyResourceInWorkingCopy(this.getSelectedResources(), workingCopyFolderName))
    {
      this.setWorkingCopyFound(true);
      this.setWorkingCopyRoot(workingCopyDetect.getWorkingCopyRoot());
    }
  }

  /**
   * Gets a value indicating whether a working copy has been found or not.
   */
  public boolean isWorkingCopyFound()
  {
    return workingCopyFound;
  }

  /**
   * Sets a value indicating whether a working copy has been found or not.
   */
  public void setWorkingCopyFound(final boolean value)
  {
    this.workingCopyFound = value;
  }

  /**
   * Sets the working copy root.
   */
  public void setWorkingCopyRoot(String value)
  {
    this.workingCopyRoot = value;
  }

  /**
   * Gets the working copy root.
   */
  public String getWorkingCopyRoot()
  {
    return workingCopyRoot;
  }
}
