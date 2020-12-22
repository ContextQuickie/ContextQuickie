package contextquickie.tortoise.hg.entries;

public class WebServer extends AbstractTortoiseHgEntryForFolder
{
  /**
   * Constructor.
   * 
   * @param iconPath
   *      The path containing the icon for this instance.
   */
  public WebServer(String iconPath)
  {
    this.setCommand("serve");
    this.setLabel("Web Server");
    this.setHelpText("Start web server for this repository");
    this.setIconPath(iconPath + "proxy.ico");
  }
}
