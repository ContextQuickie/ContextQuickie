package contextquickie.handlers.tortoise.svn;

/**
 * @author ContextQuickie
 * 
 *         Enumeration of menu items for Tortoise SVN.
 *
 */
public final class TortoiseSvnMenuItems
{
  /**
   * Checkout menu entry.
   */
  public static final long MENUCHECKOUT = 0x0000000000000001;

  /**
   * Update menu entry.
   */
  public static final long MENUUPDATE = 0x0000000000000002;

  /**
   * Commit menu entry.
   */
  public static final long MENUCOMMIT = 0x0000000000000004;

  /**
   * Add menu entry.
   */
  public static final long MENUADD = 0x0000000000000008;

  /**
   * Revert menu entry.
   */
  public static final long MENUREVERT = 0x0000000000000010;

  /**
   * Cleanup menu entry.
   */
  public static final long MENUCLEANUP = 0x0000000000000020;

  /**
   * Resolve menu entry.
   */
  public static final long MENURESOLVE = 0x0000000000000040;

  /**
   * Switch menu entry.
   */
  public static final long MENUSWITCH = 0x0000000000000080;

  /**
   * Import menu entry.
   */
  public static final long MENUIMPORT = 0x0000000000000100;

  /**
   * Export menu entry.
   */
  public static final long MENUEXPORT = 0x0000000000000200;

  /**
   * Create repository menu entry.
   */
  public static final long MENUCREATEREPOS = 0x0000000000000400;

  /**
   * Branch/tag menu entry.
   */
  public static final long MENUCOPY = 0x0000000000000800;

  /**
   * Merge menu entry.
   */
  public static final long MENUMERGE = 0x0000000000001000;

  /**
   * Remove menu entry.
   */
  public static final long MENUREMOVE = 0x0000000000002000;

  /**
   * Rename menu entry.
   */
  public static final long MENURENAME = 0x0000000000004000;

  /**
   * Update to revision menu entry.
   */
  public static final long MENUUPDATEEXT = 0x0000000000008000;

  /**
   * Diff menu entry.
   */
  public static final long MENUDIFF = 0x0000000000010000;

  /**
   * Show log menu entry.
   */
  public static final long MENULOG = 0x0000000000020000;

  /**
   * Edit conflicts menu entry.
   */
  public static final long MENUCONFLICTEDITOR = 0x0000000000040000;

  /**
   * Relocate menu entry.
   */
  public static final long MENURELOCATE = 0x0000000000080000;

  /**
   * Check for modifications menu entry.
   */
  public static final long MENUSHOWCHANGED = 0x0000000000100000;

  /**
   * Ignore menu entry.
   */
  public static final long MENUIGNORE = 0x0000000000200000;

  /**
   * Repository browser menu entry.
   */
  public static final long MENUREPOBROWSE = 0x0000000000400000;

  /**
   * Blame menu entry.
   */
  public static final long MENUBLAME = 0x0000000000800000;

  /**
   * Create Patch menu entry.
   */
  public static final long MENUCREATEPATCH = 0x0000000001000000;

  /**
   * Apply Patch menu entry.
   */
  public static final long MENUAPPLYPATCH = 0x0000000002000000;

  /**
   * Revision graph menu entry.
   */
  public static final long MENUREVISIONGRAPH = 0x0000000004000000;

  /**
   * Get Lock menu entry.
   */
  public static final long MENULOCK = 0x0000000008000000;

  /**
   * Release Lock menu entry.
   */
  public static final long MENUUNLOCK = 0x0000000010000000;

  /**
   * Properties menu entry.
   */
  public static final long MENUPROPERTIES = 0x0000000020000000;

  /**
   * Diff with URL menu entry.
   */
  public static final long MENUURLDIFF = 0x0000000040000000;

  /**
   * Delete unversioned items menu entry.
   */
  public static final long MENUDELUNVERSIONED = 0x0000000080000000;

  /**
   * Merge all entry.
   */
  public static final long MENUMERGEALL = 0x0000000100000000L;

  /**
   * Diff with previous version menu entry.
   */
  public static final long MENUPREVDIFF = 0x0000000200000000L;

  /**
   * Paste menu entry.
   */
  public static final long MENUCLIPPASTE = 0x0000000400000000L;

  /**
   * Update to revision menu entry.
   */
  public static final long MENUUPGRADE = 0x0000000800000000L;

  /**
   * Diff later menu entry.
   */
  public static final long MENUDIFFLATER = 0x0000001000000000L;

  /**
   * Diff menu entry.
   */
  public static final long MENUDIFFNOW = 0x0000002000000000L;

  /**
   * TODO menu entry.
   */
  public static final long MENUUNIDIFF = 0x0000004000000000L;

  /**
   * Copy URL to clipboard menu entry.
   */
  public static final long MENUCOPYURL = 0x0000008000000000L;

  /**
   * Settings menu entry.
   */
  public static final long MENUSETTINGS = 0x2000000000000000L;

  /**
   * Help menu entry.
   */
  public static final long MENUHELP = 0x4000000000000000L;

  /**
   * About menu entry.
   */
  public static final long MENUABOUT = 0x8000000000000000L;

  /**
   * Prevents from creating instances.
   */
  private TortoiseSvnMenuItems()
  {
  }
}
