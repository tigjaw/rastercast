package core;

import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

@SuppressWarnings("serial")
public class Converter extends JPanel implements ActionListener {
	private JButton openButton, saveButton;
	private JCheckBox deleteCheckBox;
	private JTextArea log;
	private JFileChooser fileChooser;
	private JComboBox<String> fileTypeChooser;
	private File file;

	public Converter() {
		super(new BorderLayout());

		log = new JTextArea(5, 20);
		log.setMargin(new Insets(5, 5, 5, 5));
		log.setEditable(false);
		JScrollPane logScrollPane = new JScrollPane(log);

		fileChooser = new JFileChooser();
		String[] fileTypes = { ".png", ".jpg", ".bmp", ".gif", ".tif" };
		fileTypeChooser = new JComboBox<String>(fileTypes);

		openButton = new JButton("Open a File...");
		openButton.addActionListener(this);

		saveButton = new JButton("Save a File as...");
		saveButton.addActionListener(this);

		deleteCheckBox = new JCheckBox("Delete Original");

		JPanel buttonPanel = new JPanel();
		buttonPanel.add(openButton);
		buttonPanel.add(saveButton);
		buttonPanel.add(fileTypeChooser);
		buttonPanel.add(deleteCheckBox);

		add(buttonPanel, BorderLayout.PAGE_START);
		add(logScrollPane, BorderLayout.CENTER);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == openButton) {
			loadFile();
			log.setCaretPosition(log.getDocument().getLength());
		} else if (e.getSource() == saveButton) {
			saveFile();
			log.setCaretPosition(log.getDocument().getLength());
		}
	}

	private void loadFile() {
		final int returnVal = fileChooser.showOpenDialog(Converter.this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			file = fileChooser.getSelectedFile();
			System.out.println("Opening: " + file.getName());
			log("Opening: " + file.getName());
		} else {
			log("Open command cancelled by user.");
		}
	}

	private void saveFile() {
		BufferedImage input_image = null;

		try {
			input_image = ImageIO.read(file);
		} catch (IOException e2) {
			log.append("Failed to read image");
			e2.printStackTrace();
		}

		final String parent = file.getParent();
		log("File Path: " + parent);

		final String fileName = file.getName();
		log("File Name: " + fileName);

		final String strippedFileName = stripExtension(fileName);
		log("Stripped File Name: " + strippedFileName);

		final String strippedFilePath = parent + "\\" + strippedFileName;
		log("Stripped File Path: " + strippedFilePath);

		String fileType = (String) fileTypeChooser.getSelectedItem();
		log("fileType: " + fileType);
		File outputfile = new File(strippedFilePath + fileType);

		if (deleteCheckBox.isSelected()) {
			log("Delete original: " + (deleteCheckBox.isSelected() == true ? "yes" : "no"));
			file.delete();
		}

		try {
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
		log.append(logThis + "\n");
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
		JFrame frame = new JFrame("FileChooserDemo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.add(new Converter());

		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				// Turn off metal's use of bold fonts
				UIManager.put("swing.boldMetal", Boolean.FALSE);
				createAndShowGUI();
			}
		});
	}

	public JComboBox<String> getFileType() {
		return fileTypeChooser;
	}

	public void setFileType(JComboBox<String> fileType) {
		this.fileTypeChooser = fileType;
	}
}