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
    if (workingCopyDetect.test(this.getSelectedResources(), workingCopyFolderName))
    {
      this.setWorkingCopyFound(true);
      this.setWorkingCopyRoot(workingCopyDetect.getWorkingCopyRoot());
    }
  }

  /**
   * @return A value indicating whether a working copy has been found or not.
   */
  public boolean isWorkingCopyFound()
  {
    return workingCopyFound;
  }

  /**
   * @param value A value indicating whether a working copy has been found or not.
   */
  public void setWorkingCopyFound(final boolean value)
  {
    this.workingCopyFound = value;
  }

  /**
   * @param value The working copy root.
   */
  public void setWorkingCopyRoot(String value)
  {
    this.workingCopyRoot = value;
  }

  /**
   * @return The working copy root.
   */
  public String getWorkingCopyRoot()
  {
    return workingCopyRoot;
  }
}
