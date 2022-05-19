package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import controller.RastercastController;

public class RastercastView {
	private RastercastController controller;
	private JPanel mainPanel, buttonPanel;
	private JScrollPane logScrollPane;
	private JButton openButton, saveButton;
	private JCheckBox deleteCheckBox;
	private JTextArea logArea;
	private JFileChooser fileChooser;
	private JComboBox<String> fileTypeComboBox;

	public RastercastView() {
		this(new RastercastController());
	}

	public RastercastView(RastercastController controller) {
		this.controller = controller;
		createAndShowGUI();
	}

	// GUI METHODS

	private void createAndShowGUI() {
		manageUI();
		createComponents();
		addComponents();
		addActions();
		showFrame();
	}

	private void manageUI() {
		// set look and feel
		try {
			UIManager.setLookAndFeel(UIValues.LF_WINDOWS);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		// Turn off metal's use of bold fonts
		// UIManager.put("swing.boldMetal", Boolean.FALSE);
	}

	private void createComponents() {
		Buildable comp = new ComponentCreator(this);

		// create UI components
		logArea = comp.createLogArea();

		fileChooser = comp.createFileChooser(UIValues.TXT_CHOOSER);
		fileTypeComboBox = comp.createFileTypeComboBox();

		openButton = new JButton(UIValues.TXT_OPEN);
		saveButton = new JButton(UIValues.TXT_SAVE);
		deleteCheckBox = new JCheckBox(UIValues.TXT_DELETE);

		// create UI panels
		mainPanel = new JPanel(new BorderLayout());
		buttonPanel = new JPanel();
		logScrollPane = new JScrollPane(logArea);
	}

	private void addComponents() {
		// add components to panels
		buttonPanel.add(openButton);
		buttonPanel.add(saveButton);
		buttonPanel.add(fileTypeComboBox);
		buttonPanel.add(deleteCheckBox);

		mainPanel.add(buttonPanel, BorderLayout.PAGE_START);
		mainPanel.add(logScrollPane, BorderLayout.CENTER);
	}

	public void addActions() {

		openButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loadImages();
			}
		});

		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveImages();
				deleteOriginals();
			}
		});
	}

	private void showFrame() {
		JFrame frame = new JFrame(UIValues.TITLE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.add(mainPanel);

		frame.setLocationRelativeTo(null);
		frame.pack();
		frame.setVisible(true);
	}

	// CONTROLLER METHODS

	private void loadImages() {
		final int returnVal = fileChooser.showOpenDialog(mainPanel);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			log(controller.loadImages(fileChooser.getSelectedFiles()));
		} else {
			log("No Files Selected.");
		}
	}

	private void saveImages() {
		String fileType = (String) fileTypeComboBox.getSelectedItem();
		String log = controller.saveImages(fileType);
		log(log);
	}

	private void deleteOriginals() {
		boolean deleteOrignals = deleteCheckBox.isSelected();
		String log = controller.deleteImages(deleteOrignals);
		log(log);
	}

	/** Returns an ImageIcon, or null if the path was invalid. */
	protected static ImageIcon createImageIcon(String path) {
		URL imgURL = RastercastView.class.getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

	// LOGGINS

	/** Logs info to Log JTextArea
	 * @param logThis (String) - text to log */
	private void log(String logThis) {
		logArea.append(logThis + "\n");
	}

	// GETTERS & SETTERS

	public void setFileType(JComboBox<String> fileTypeComboBox) {
		this.fileTypeComboBox = fileTypeComboBox;
	}

	public RastercastController getController() {
		return controller;
	}

	public void setController(RastercastController controller) {
		this.controller = controller;
	}

	public JPanel getMainPanel() {
		return mainPanel;
	}

	public void setMainPanel(JPanel mainPanel) {
		this.mainPanel = mainPanel;
	}

	public JPanel getButtonPanel() {
		return buttonPanel;
	}

	public void setButtonPanel(JPanel buttonPanel) {
		this.buttonPanel = buttonPanel;
	}

	public JScrollPane getLogScrollPane() {
		return logScrollPane;
	}

	public void setLogScrollPane(JScrollPane logScrollPane) {
		this.logScrollPane = logScrollPane;
	}

	public JButton getOpenButton() {
		return openButton;
	}

	public void setOpenButton(JButton openButton) {
		this.openButton = openButton;
	}

	public JButton getSaveButton() {
		return saveButton;
	}

	public void setSaveButton(JButton saveButton) {
		this.saveButton = saveButton;
	}

	public JCheckBox getDeleteCheckBox() {
		return deleteCheckBox;
	}

	public void setDeleteCheckBox(JCheckBox deleteCheckBox) {
		this.deleteCheckBox = deleteCheckBox;
	}

	public JTextArea getLogArea() {
		return logArea;
	}

	public void setLogArea(JTextArea logArea) {
		this.logArea = logArea;
	}

	public JFileChooser getFileChooser() {
		return fileChooser;
	}

	public void setFileChooser(JFileChooser fileChooser) {
		this.fileChooser = fileChooser;
	}

	public JComboBox<String> getFileTypeComboBox() {
		return fileTypeComboBox;
	}

	public void setFileTypeComboBox(JComboBox<String> fileTypeComboBox) {
		this.fileTypeComboBox = fileTypeComboBox;
	}

}