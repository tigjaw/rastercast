package utils;

public class RCStrings {

	/** Frame title */
	public final static String TITLE = "Rastercast";

	// GUI LOOK & FEEL STRINGS

	/** L&F for Linux and Solaris, if GTK+ 2.2 or later is installed */
	public final static String LF_GTK = "com.sun.java.swing.plaf.gtk.GTKLookAndFeel";
	/** L&F for Linux and Solaris, if GTK+ 2.2 or later is not installed */
	public final static String LF_MOTIF = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
	/** L&F for Windows */
	public final static String LF_WINDOWS = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
	/** L&F for cross-platform (Metal) */
	public final static String LF_CROSS = "javax.swing.plaf.metal.MetalLookAndFeel";
	/** L&F native to the system it is running on */
	public final static String LF_SYSTEM = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";

	// RESOURCE PATHS

	private final static String DIR_RES = "res\\";
	private final static String ICON_ROLLOVER = "_rollover";
	private final static String ICON_PRESSED = "_pressed";
	private final static String ICON_FILETYPE = ".png";

	/** location for "open" button icon */
	private final static String ICON_OPEN = DIR_RES + "icon_open";
	public final static String ICON_OPEN_MAIN = ICON_OPEN + ICON_FILETYPE;
	public final static String ICON_OPEN_ROLLOVER = ICON_OPEN + ICON_ROLLOVER + ICON_FILETYPE;
	public final static String ICON_OPEN_PRESSED = ICON_OPEN + ICON_PRESSED + ICON_FILETYPE;

	/** location for "save" button icon */
	private final static String ICON_SAVE = DIR_RES + "icon_save";
	public final static String ICON_SAVE_MAIN = ICON_SAVE + ICON_FILETYPE;
	public final static String ICON_SAVE_ROLLOVER = ICON_SAVE + ICON_ROLLOVER + ICON_FILETYPE;
	public final static String ICON_SAVE_PRESSED = ICON_SAVE + ICON_PRESSED + ICON_FILETYPE;

	// GUI TEXT

	/** the type of file being handled */
	private final static String TXT_FILE = "Images";

	/** text for "open" button */
	public final static String TXT_OPEN = "Open " + TXT_FILE + "...";
	/** text for "save" button */
	public final static String TXT_SAVE = "Save " + TXT_FILE + " as...";
	/** Title text for FileFilter JFileChooser */
	public final static String TXT_CHOOSER = "Rastercast > " + TXT_OPEN;
	/** Title text for "Delete Originals" JCheckBox */
	public final static String TXT_DELETE = "Delete Originals";

}