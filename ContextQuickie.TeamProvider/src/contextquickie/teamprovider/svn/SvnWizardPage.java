package contextquickie.teamprovider.svn;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class SvnWizardPage extends WizardPage
{
  private String projectName;
  
  private String projectPath;
  
  private String workingCopy;
  
  protected SvnWizardPage(String pageName)
  {
    super(pageName);
    this.setTitle("Share Project: ContextQuickie");
  }

  @Override
  public void createControl(Composite parent)
  {
    Composite container = new Composite(parent, SWT.NONE);
    GridLayout layout = new GridLayout();
    container.setLayout(layout);
    layout.numColumns = 2;
    Label label1 = new Label(container, SWT.NONE);
    label1.setText("Project:");
    
    Label labelProjectName = new Label(container, SWT.NONE);
    labelProjectName.setText(this.projectName);
    
    Label label2 = new Label(container, SWT.NONE);
    label2.setText("Project Path:");
    
    Label labelProjectPath = new Label(container, SWT.NONE);
    labelProjectPath.setText(this.projectPath);
    
    Label label3 = new Label(container, SWT.NONE);
    label3.setText("Working Copy:");
    
    Label labelWorkingCopy = new Label(container, SWT.NONE);
    if (this.workingCopy != null)
    {
      labelWorkingCopy.setText(this.workingCopy);
    }
    else
    {
      labelWorkingCopy.setText("Not found");
    }
   
    this.setControl(container);
  }
  
  public void setProjectName(String projectName)
  {
    this.projectName = projectName;
  }
  
  public void setProjectPath(String projectPath)
  {
    this.projectPath = projectPath;
  }
  
  public void setWorkingCopy(String workingCopy)
  {
    this.workingCopy = workingCopy;
  }

}
