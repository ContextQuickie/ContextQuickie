package contextquickie.tortoise.git.entries;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IResource;

import contextquickie.Activator;
import contextquickie.preferences.PreferenceConstants;
import contextquickie.tools.ContextMenuEnvironment;
import contextquickie.tools.ProcessWrapper;
import contextquickie.tools.StringUtil;
import contextquickie.tortoise.TortoiseMenuConstants;
import contextquickie.tortoise.TortoiseMenuEntry;
import contextquickie.tortoise.Translation;

public abstract class AbstractTortoiseGitEntry extends TortoiseMenuEntry
{
  private Set<IResource> selectedResources;
  
  /**
   * The instance using for translating the menu entries.
   */
  protected static final Translation translation = new Translation(PreferenceConstants.TORTOISE_GIT);

  /**
   * Constructor.
   * 
   * @param menuTextIdentifier
   *          The identifier for the menu text.
   * 
   * @param defaultLabel
   *          The default value for the menu text.
   */
  protected AbstractTortoiseGitEntry(final int menuTextIdentifier, final String defaultLabel)
  {
    this.setLabel(translation.getTranslatedString(menuTextIdentifier, defaultLabel));
  }

  @Override
  public void executeCommand()
  {
    final List<String> arguments = new ArrayList<String>();
    final String command = this.getCommand();
    
    final String executable = Activator.getDefault().getPreferenceStore().getString(this.getPreferenceConstants().getPath());
    
    final boolean requiresPath = this.getEntryRequiresPath();
    final boolean supportsLinkedResources = this.isSupportingLinkedResources();;

    arguments.add("/command:" + command);
    Set<IResource> currentResources = null;
    if (requiresPath)
    {
      if (supportsLinkedResources == true)
      {
        currentResources = this.selectedResources;
      }
      else
      {
        currentResources = new ContextMenuEnvironment().getSelectedResources();
      }

      final String pathArgument = String.join("*", currentResources.stream().map(resource -> resource.getLocation().toOSString()).collect(Collectors.toSet()));
      arguments.add("/path:" + StringUtil.quoteString(pathArgument));
    }

    final String parameter1 = this.getParameter1();
    if (parameter1 != null)
    {
      arguments.add(parameter1);
    }
    
    new ProcessWrapper().executeCommand(executable, currentResources, arguments);
  }

  @Override
  public Boolean isVisible(ContextMenuEnvironment environment)
  {
    this.selectedResources = environment.getSelectedResources();
    return super.isVisible(environment);
  }
}
