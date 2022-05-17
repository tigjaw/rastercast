package view;

import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	private JTextArea log;
	private JFileChooser fileChooser;
	private JComboBox<String> fileTypeChooser;

	public RastercastView() {
		this(new RastercastController());
	}

	public RastercastView(RastercastController controller) {
		this.controller = controller;
		createAndShowGUI();
	}

	private void createAndShowGUI() {
		manageUI();
		createComponents();
		createPanels();
		addComponents();
		addActions();
		showFrame();
	}

	private void manageUI() {
		// set look and feel
		try {
			UIManager.setLookAndFeel(LookFeel.WINDOWS);
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
		UIManager.put("swing.boldMetal", Boolean.FALSE);
	}

	private void createComponents() {
		// create UI components
		log = new JTextArea(25, 25);
		log.setMargin(new Insets(5, 5, 5, 5));
		log.setEditable(false);

		fileChooser = new JFileChooser();
		fileChooser.setMultiSelectionEnabled(true);
		fileTypeChooser = new JComboBox<String>(controller.getFileTypes());

		openButton = new JButton("Open a File...");
		saveButton = new JButton("Save a File as...");
		deleteCheckBox = new JCheckBox("Delete Original");
	}

	private void createPanels() {
		// create UI panels
		mainPanel = new JPanel(new BorderLayout());
		buttonPanel = new JPanel();
		logScrollPane = new JScrollPane(log);
	}

	private void addComponents() {
		// add components to panels
		buttonPanel.add(openButton);
		buttonPanel.add(saveButton);
		buttonPanel.add(fileTypeChooser);
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
		JFrame frame = new JFrame("Rastercast");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.add(mainPanel);

		frame.setLocationRelativeTo(null);
		frame.pack();
		frame.setVisible(true);
	}

	private void loadImages() {
		final int returnVal = fileChooser.showOpenDialog(mainPanel);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			log(controller.loadFiles(fileChooser.getSelectedFiles()));
		} else {
			log("Open command cancelled by user.");
		}
	}

	private void deleteOriginals() {
		boolean deleteOrignals = deleteCheckBox.isSelected();
		String log = controller.deleteImages(deleteOrignals);
		log(log);
	}

	private void saveImages() {
		String fileType = (String) fileTypeChooser.getSelectedItem();
		String log = controller.saveImages(fileType);
		log(log);
	}

	private void log(String logThis) {
		log.append(logThis + "\n");
	}

	/** Returns an ImageIcon, or null if the path was invalid. */
	protected static ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = RastercastView.class.getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

	public JComboBox<String> getFileType() {
		return fileTypeChooser;
	}

	public void setFileType(JComboBox<String> fileType) {
		this.fileTypeChooser = fileType;
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

	public JTextArea getLog() {
		return log;
	}

	public void setLog(JTextArea log) {
		this.log = log;
	}

	public JFileChooser getFileChooser() {
		return fileChooser;
	}

	public void setFileChooser(JFileChooser fileChooser) {
		this.fileChooser = fileChooser;
	}

	public JComboBox<String> getFileTypeChooser() {
		return fileTypeChooser;
	}

	public void setFileTypeChooser(JComboBox<String> fileTypeChooser) {
		this.fileTypeChooser = fileTypeChooser;
	}
}