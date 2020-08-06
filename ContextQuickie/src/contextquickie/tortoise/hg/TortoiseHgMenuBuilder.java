package contextquickie.tortoise.hg;

import java.util.Collections;
import java.util.List;

import org.eclipse.jface.action.IContributionItem;

import contextquickie.base.AbstractMenuBuilder;
import contextquickie.preferences.PreferenceConstants;

public class TortoiseHgMenuBuilder extends AbstractMenuBuilder
{

  public TortoiseHgMenuBuilder()
  {
    super(PreferenceConstants.TORTOISE_HG.getEnabled());
  }

  @Override
  protected List<IContributionItem> getMenuEntries()
  {
    // TODO Auto-generated method stub
    return Collections.<IContributionItem>emptyList();
  }

}
