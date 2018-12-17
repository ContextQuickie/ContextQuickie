package contextquickie.windows;

public abstract class BaseLoader
{
  static
  {
    final String archDataModel = System.getProperty("sun.arch.data.model");
    System.loadLibrary("libraries/ContextQuickie.Windows" + archDataModel);
  }
}
