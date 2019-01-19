package contextquickie.teamprovider.svn;

import org.eclipse.team.core.ProjectSetCapability;
import org.eclipse.team.core.RepositoryProviderType;

public class SvnRepositoryProviderType extends RepositoryProviderType {

  @Override
  public ProjectSetCapability getProjectSetCapability()
  {
    return new SvnProjectSetCapability();
  }

}
