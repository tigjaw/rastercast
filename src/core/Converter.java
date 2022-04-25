package core;

// to read input and write output images
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.awt.image.BufferedImage; // This class will read the incoming BMP
										// image
import javax.imageio.ImageIO; // This class will convert BMP to PNG

/* FileChooserDemo.java uses these files:
 * images/Open16.gif
 * images/Save16.gif */
@SuppressWarnings("serial")
public class Converter extends JPanel
		implements ActionListener {
	static private final String newline = "\n";
	private JButton openButton, saveButton;
	private JCheckBox deleteCheckBox;
	private JTextArea log;
	private JFileChooser fc;
	private File file;
	// private String parent,fileName,strippedFileName;

	public Converter() {
		super(new BorderLayout());

		// Create the log first, because the action listeners
		// need to refer to it.
		log = new JTextArea(5, 20);
		log.setMargin(new Insets(5, 5, 5, 5));
		log.setEditable(false);
		JScrollPane logScrollPane = new JScrollPane(log);

		// Create a file chooser
		fc = new JFileChooser();

		// Uncomment one of the following lines to try a different
		// file selection mode. The first allows just directories
		// to be selected (and, at least in the Java look and feel,
		// shown). The second allows both files and directories
		// to be selected. If you leave these lines commented out,
		// then the default mode (FILES_ONLY) will be used.
		//
		// fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		// fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

		// Create the open button. We use the image from the JLF
		// Graphics Repository (but we extracted it from the jar).
		openButton = new JButton("Open a File...");
		openButton.addActionListener(this);

		// Create the save button. We use the image from the JLF
		// Graphics Repository (but we extracted it from the jar).
		saveButton = new JButton("Save a File...");
		saveButton.addActionListener(this);
		// checkbox to delete original
		deleteCheckBox = new JCheckBox("Delete Original");

		// For layout purposes, put the buttons in a separate panel
		JPanel buttonPanel = new JPanel(); // use FlowLayout
		buttonPanel.add(openButton);
		buttonPanel.add(saveButton);
		buttonPanel.add(deleteCheckBox);

		// Add the buttons and the log to this panel.
		add(buttonPanel, BorderLayout.PAGE_START);
		add(logScrollPane, BorderLayout.CENTER);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == openButton) {
			// Handle open button action.
			loadFile();
			log.setCaretPosition(log.getDocument().getLength());
		} else if (e.getSource() == saveButton) {
			// Handle save button action.
			saveFile();
			log.setCaretPosition(log.getDocument().getLength());
		}
	}

	private void loadFile() {
		final int returnVal = fc.showOpenDialog(Converter.this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			file = fc.getSelectedFile();
			System.out.println("Opening: " + file.getName());
			log("Opening: " + file.getName());
		} else {
			log("Open command cancelled by user.");
		}
	}

	private void saveFile() {
		// This is where a real application would save the file.
		BufferedImage input_image = null;
		try {
			input_image = ImageIO.read(file);
		} catch (IOException e2) {
			log.append("Failed to read image");
			e2.printStackTrace();
		} // read bmp into input_image object
		final String parent = file.getParent();
		log("File Path: " + parent);
		final String fileName = file.getName(); // name with extension
		log("File Name: " + fileName);
		final String strippedFileName = stripExtension(fileName);
		log("Stripped File Name: " + strippedFileName);
		final String strippedFilePath = parent + "\\" + strippedFileName;
		log("File Path: " + strippedFilePath);
		// create new output file
		File outputfile = new File(strippedFilePath + ".png");
		if (deleteCheckBox.isSelected()) {
			log("Delete original: " + (deleteCheckBox.isSelected()==true?"yes":"no"));
		}
		try {
			// write PNG output to file
			log("Saving: " + file.getName());
			ImageIO.write(input_image, "png", outputfile);
			log("Image Converted Successfully!");
		} catch (IOException e1) {
			log("Failed to write file");
			e1.printStackTrace();
		}
	}
	
	private String stripExtension(String str) {
		if (str == null)
			return null;
		// Get position of last '.'.
		int pos = str.lastIndexOf(".");
		// If there wasn't any '.' just return the string as is.
		if (pos == -1)
			return str;
		// Otherwise return the string, up to the dot.
		return str.substring(0, pos);
	}
	
	private void log(String logThis) {
		log.append(logThis + newline);
	}

	/** Returns an ImageIcon, or null if the path was invalid. */
	protected static ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = Converter.class.getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

	/** Create the GUI and show it. For thread safety,
	 * this method should be invoked from the
	 * event dispatch thread. */
	private static void createAndShowGUI() {
		// Create and set up the window.
		JFrame frame = new JFrame("FileChooserDemo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Add content to the window.
		frame.add(new Converter());

		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		// Schedule a job for the event dispatch thread:
		// creating and showing this application's GUI.
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				// Turn off metal's use of bold fonts
				UIManager.put("swing.boldMetal", Boolean.FALSE);
				createAndShowGUI();
			}
		});
	}
}