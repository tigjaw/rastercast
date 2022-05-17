package view;

public class LookFeel {
	/** For Linux and Solaris, if GTK+ 2.2 or later is installed */
	public final static String GTK = "com.sun.java.swing.plaf.gtk.GTKLookAndFeel";
	/** For Linux and Solaris, if GTK+ 2.2 or later is not installed */
	public final static String MOTIF = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
	/** For Windows */
	public final static String WINDOWS = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
	/** Java L&F ("Metal") that looks the same on all platforms. */
	public final static String CROSS_PLAT = "javax.swing.plaf.metal.MetalLookAndFeel";
	/** L&G native to the system it is running on */
	public final static String SYSTEM = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
}