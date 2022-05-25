package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

import controller.RCController;
import utils.RCStrings;

public class RCView {
	private RCController controller;
	private JPanel mainPanel, buttonPanel;
	private JScrollPane logScrollPane;
	private JButton openButton, saveButton;
	private JCheckBox deleteCheckBox;
	private JTextArea logArea;
	private JFileChooser fileChooser;
	private JComboBox<String> fileTypeComboBox;

	public RCView() {
		this(new RCController());
	}

	public RCView(RCController controller) {
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
			UIManager.setLookAndFeel(RCStrings.LF_WINDOWS);
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
		// create UI components
		logArea = ComponentFactory.createLogArea();

		fileChooser = ComponentFactory.createFileChooser();
		fileTypeComboBox = ComponentFactory.createFileTypeComboBox();

		openButton = ComponentFactory.createOpenButton();
		saveButton = ComponentFactory.createSaveButton();
		deleteCheckBox = ComponentFactory.createCheckBox();

		// create UI panels
		mainPanel = ComponentFactory.createMainPanel();
		buttonPanel = ComponentFactory.createPanel();
		logScrollPane = ComponentFactory.createScrollePane(logArea);
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
		JFrame frame = new JFrame(RCStrings.TITLE);
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
			log("\nNo Files Selected.");
		}
	}

	private void saveImages() {
		String imageFormat = (String) fileTypeComboBox.getSelectedItem();
		String log = controller.saveImages(imageFormat);
		log(log);
	}

	private void deleteOriginals() {
		boolean deleteOrignals = deleteCheckBox.isSelected();
		String log = controller.deleteImages(deleteOrignals);
		log(log);
	}

	// LOG

	/** Logs info to Log JTextArea
	 * @param logThis (String) - text to log */
	private void log(String logThis) {
		logArea.append(logThis + "\n");
	}

	// GETTERS & SETTERS

	public RCController getController() {
		return controller;
	}

	public void setController(RCController controller) {
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