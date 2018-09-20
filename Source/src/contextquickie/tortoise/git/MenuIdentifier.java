package contextquickie.tortoise.git;

/**
 * @author ContextQuickie
 *
 *         Menu identifiers for Tortoise Git.
 */
public class MenuIdentifier
{
  /**
   * Sync menu entry.
   */
  public static final long MENUSYNC = 0x0000000000000002;

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
   * Send mail menu entry.
   */
  public static final long MENUSENDMAIL = 0x0000000000000100;

  /**
   * Export menu entry.
   */
  public static final long MENUEXPORT = 0x0000000000000200;

  /**
   * Create repository menu entry.
   */
  public static final long MENUCREATEREPOS = 0x0000000000000400;

  /**
   * Create branch menu entry.
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
   * Submodule Update menu entry.
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
   * Browse References menu entry.
   */
  public static final long MENUREFBROWSE = 0x0000000000080000;

  /**
   * Check for modifications menu entry.
   */
  public static final long MENUSHOWCHANGED = 0x0000000000100000;

  /**
   * Ignore menu entry.
   */
  public static final long MENUIGNORE = 0x0000000000200000;

  /**
   * Show Reflog menu entry.
   */
  public static final long MENUREFLOG = 0x0000000000400000;

  /**
   * Blame menu entry.
   */
  public static final long MENUBLAME = 0x0000000000800000;

  /**
   * Repo browser menu entry.
   */
  public static final long MENUREPOBROWSE = 0x0000000001000000;

  /**
   * Apply patch menu entry.
   */
  public static final long MENUAPPLYPATCH = 0x0000000002000000;

  /**
   * Delete (keep local) menu entry.
   */
  public static final long MENUREMOVEKEEP = 0x0000000004000000;

  /**
   * SVN Rebase menu entry.
   */
  public static final long MENUSVNREBASE = 0x0000000008000000;

  /**
   * SVN DCommit menu entry.
   */
  public static final long MENUSVNDCOMMIT = 0x0000000010000000;

  /**
   * Import SVN Ignore menu entry.
   */
  public static final long MENUSVNIGNORE = 0x0000000040000000;

  /**
   * "Show log of this folder" menu entry.
   */
  public static final long MENULOGSUBMODULE = 0x0000000100000000L;

  /**
   * Diff with previous version menu entry.
   */
  public static final long MENUPREVDIFF = 0x0000000200000000L;

  /**
   * "Paste" menu entry.
   */
  public static final long MENUCLIPPASTE = 0x0000000400000000L;

  /**
   * Pull menu entry.
   */
  public static final long MENUPULL = 0x0000000800000000L;

  /**
   * Push menu entry.
   */
  public static final long MENUPUSH = 0x0000001000000000L;

  /**
   * Clone menu entry.
   */
  public static final long MENUCLONE = 0x0000002000000000L;

  /**
   * Create tag menu entry.
   */
  public static final long MENUTAG = 0x0000004000000000L;

  /**
   * "Create Patch Serial..." menu entry.
   */
  public static final long MENUFORMATPATCH = 0x0000008000000000L;

  /**
   * "Apply Patch Serial..." menu entry.
   */
  public static final long MENUIMPORTPATCH = 0x0000010000000000L;

  /**
   * "Diff later" menu entry.
   */
  public static final long MENUDIFFLATER = 0x0000020000000000L;

  /**
   * Fetch menu entry.
   */
  public static final long MENUFETCH = 0x0000040000000000L;

  /**
   * Rebase menu entry.
   */
  public static final long MENUREBASE = 0x0000080000000000L;

  /**
   * Stash Save menu entry.
   */
  public static final long MENUSTASHSAVE = 0x0000100000000000L;

  /**
   * Stash Apply menu entry.
   */
  public static final long MENUSTASHAPPLY = 0x0000200000000000L;

  /**
   * Stash List menu entry.
   */
  public static final long MENUSTASHLIST = 0x0000400000000000L;

  /**
   * Submodule add menu entry.
   */
  public static final long MENUSUBADD = 0x0000800000000000L;

  /**
   * Submodule Sync menu entry.
   */
  public static final long MENUSUBSYNC = 0x0001000000000000L;

  /**
   * Stash Pop menu entry.
   */
  public static final long MENUSTASHPOP = 0x0002000000000000L;

  /**
   * "Diff Two Commits" menu entry.
   */
  public static final long MENUDIFFTWO = 0x0004000000000000L;

  /**
   * Bisect start menu entry.
   */
  public static final long MENUBISECT = 0x0008000000000000L;

  /**
   * SVN Fetch menu entry.
   */
  public static final long MENUSVNFETCH = 0x0080000000000000L;

  /**
   * Revision Graph menu entry.
   */
  public static final long MENUREVISIONGRAPH = 0x0100000000000000L;

  /**
   * Daemon menu entry.
   */
  public static final long MENUDAEMON = 0x0200000000000000L;

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
}
