package contextquickie.tortoise.git.entries;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IResource;
import org.eclipse.jface.preference.IPreferenceStore;

import contextquickie.Activator;
import contextquickie.preferences.PreferenceConstants;
import contextquickie.tools.ContextMenuEnvironment;
import contextquickie.tools.ProcessWrapper;
import contextquickie.tools.StringUtil;
import contextquickie.tortoise.TortoiseEnvironment;
import contextquickie.tortoise.TortoiseMenuEntry;
import contextquickie.tortoise.TortoiseWorkingCopyDetect;
import contextquickie.tortoise.Translation;
import contextquickie.tortoise.git.TortoiseGitMenuBuilder;

public abstract class AbstractTortoiseGitEntry extends TortoiseMenuEntry
{

   
  /**
   * The instance using for translating the menu entries.
   */
  protected static final Translation translation = new Translation(PreferenceConstants.TORTOISE_GIT);

  static
  {
    setPreferenceConstants(PreferenceConstants.TORTOISE_GIT);;
  }

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
    
    final String executable = Activator.getDefault().getPreferenceStore().getString(getPreferenceConstants().getPath());
    
    final boolean requiresPath = this.getEntryRequiresPath();
    final boolean supportsLinkedResources = this.isSupportingLinkedResources();;

    arguments.add("/command:" + command);
    Set<IResource> currentResources = null;
    if (requiresPath)
    {
      if (supportsLinkedResources == true)
      {
        currentResources = this.getSelectedResources();
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

  protected boolean bisectActive(final TortoiseEnvironment environment)
  {
    final String workingCopyRoot = environment.getWorkingCopyRoot();
    if (workingCopyRoot != null)
    {
      File gitDirectory = new File(workingCopyRoot);
      if (gitDirectory.exists() && gitDirectory.isDirectory())
      {
        File bisectStartFile = new File(gitDirectory, "BISECT_START");
        if (bisectStartFile.exists() && bisectStartFile.isFile())
        {
          return true;
        }
      }
    }

    return false;
  }
  
  /**
   * Checks if the context menu entry is visible in the main menu.
   * 
   * @param entry
   *          The name of the entry.
   * @return <b>true</b> if the menu entry is visible in the main menu;
   *         otherwise false.
   */
  protected boolean isEntryBitSet(final long entry, final long lowRegistryEntry, long highRegistryEntry)
  {
    final long int32BitMaxValue = 0xFFFFFFFFL;
    final long compareValue;
    final long entryValue;

    if (entry > int32BitMaxValue)
    {
      entryValue = entry >> Integer.SIZE;
      compareValue = highRegistryEntry;
    }
    else
    {
      entryValue = entry;
      compareValue = lowRegistryEntry;
    }

    return (entryValue & compareValue) != 0;
  }
}
