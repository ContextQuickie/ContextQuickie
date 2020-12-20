package contextquickie;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.commands.AbstractParameterValueConverter;
import org.eclipse.core.commands.ParameterValueConversionException;

public class ObjectParameterConverter extends AbstractParameterValueConverter
{
  private static Map<String, Object> entries = new HashMap<String, Object>();

  @Override
  public Object convertToObject(String parameterValue) throws ParameterValueConversionException
  {
    if (entries.containsKey(parameterValue))
    {
      return entries.get(parameterValue);
    }
    return null;
  }

  @Override
  public String convertToString(Object parameterValue) throws ParameterValueConversionException
  {
    String hashCode = Integer.toString(parameterValue.hashCode());
    entries.put(hashCode, parameterValue);
    return hashCode;
  }

  public static void clearEntries()
  {
    entries.clear();
  }
}
