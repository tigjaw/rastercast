package view;

public class UIValues {

	/** Frame title */
	public final static String TITLE = "Rastercast";

	/** Text to describe type of file being handled */
	public final static String TXT_FILE = "Images";

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
	public final static String SYSTEM = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";

	// GUI TEXT

	/** text for 'open' button */
	public final static String TXT_OPEN = "Open " + TXT_FILE + "...";
	/** text for 'save' button */
	public final static String TXT_SAVE = "Save " + TXT_FILE + " as...";
	/** Title text for JFileChooser */
	public final static String TXT_CHOOSER = "Rastercast > " + TXT_OPEN;
	/** Title text for JComboBox */
	public final static String TXT_DELETE = "Delete Originals";

}