package contextquickie.teamprovider.svn;

import org.apache.subversion.javahl.callback.StatusCallback;
import org.apache.subversion.javahl.types.Status;

public class SvnStatusCallback implements StatusCallback
{
  private Status currentStatus;
  
  @Override
  public void doStatus(String path, Status status)
  {
    this.currentStatus = status;
  }

  public Status getStatus()
  {
    return currentStatus;
  }

  public void setCurrentStatus(Status value)
  {
    this.currentStatus = value;
  }

}
