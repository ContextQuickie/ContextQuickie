package contextquickie.tortoise;

public class Version implements Comparable<Version>
{
  private int majorVersion = 0;
  
  private int minorVersion = 0;
  
  /**
   * Constructor.
   * 
   * @param version
   *      The string from which the version is created.
   */
  public Version(String version)
  {
    String[] versionItems = version.split("\\.");
    
    this.majorVersion = Integer.parseInt(versionItems[0]);
    
    if (versionItems.length > 1)
    {      
      this.minorVersion = Integer.parseInt(versionItems[1]);
    }
  }
  
  @Override
  public int hashCode()
  {
    final int prime = 31;
    int result = 1;
    result = prime * result + majorVersion;
    result = prime * result + minorVersion;
    return result;
  }

  @Override
  public boolean equals(Object obj)
  {
    if (this == obj)
    {
      return true;
    }
    else if (obj == null)
    {
      return false;
    }
    else if (getClass() != obj.getClass())
    {
      return false;
    }
    else
    {
      Version other = (Version) obj;
      if ((majorVersion != other.majorVersion) || (minorVersion != other.minorVersion))
      {
        return false;
      }
    }
    
    return true;
  }

  /**
   * Constructor.
   * @param major
   *      The major version.
   * @param minor
   *      The minor version.
   */
  public Version(int major, int minor)
  {
    this.setMajorVersion(major);
    this.setMinorVersion(minor);
  }

  /**
   * Gets the major version.
   */
  public int getMajorVersion()
  {
    return this.majorVersion;
  }

  /**
   * Sets the major version.
   */
  public void setMajorVersion(int value)
  {
    this.majorVersion = value;
  }

  /**
   * Gets the minor version.
   */
  public int getMinorVersion()
  {
    return this.minorVersion;
  }

  /**
   * Sets the minor version.
   */
  public void setMinorVersion(int value)
  {
    this.minorVersion = value;
  }

  @Override
  public int compareTo(Version arg0)
  {
    int result = 0;
    int majorCompareResult = Integer.compare(this.majorVersion, arg0.majorVersion);
    int minorCompareResult = Integer.compare(this.minorVersion, arg0.minorVersion);
    if (majorCompareResult != 0)
    {
      result = majorCompareResult;
    }
    else if (minorCompareResult != 0)
    {
      result = minorCompareResult;
    }
    
    return result;
  }
}
