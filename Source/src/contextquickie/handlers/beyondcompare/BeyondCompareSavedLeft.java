package contextquickie.handlers.beyondcompare;

/**
 * @author ContextQuickie
 *
 *         Enumeration of values indicating the type of the left side of the
 *         comparison.
 */
public enum BeyondCompareSavedLeft {
	/**
	 * Left side is a file.
	 */
	File,
	/**
	 * Left side is a directory.
	 */
	Directory,
	/**
	 * Left side not present or unknown.
	 */
	None
}
