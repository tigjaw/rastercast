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
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import controller.BatchImage;
import controller.RCController;
import core.RasterCast;
import utils.RCStrings;

public class RCView extends ComponentCreator {
	private RCController controller;
	private JFrame frame;
	private JPanel mainPanel, buttonPanel, batchPanel;
	private JScrollPane batchScroller;
	private JButton addToBatchBtn, clearBatchBtn, saveBatchBtn;
	private JComboBox<String> formatComboBox;
	private JCheckBox deleteOriginalsCheckBox;

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
		// buttons - building the batch
		addToBatchBtn = createAddToBatchButton();
		clearBatchBtn = createClearBatchButton();
		saveBatchBtn = createSaveBatchButton();

		// options - batch operations
		formatComboBox = new JComboBox<String>(RasterCast.formatsAsArray());
		deleteOriginalsCheckBox = createCheckBox();

		// panels - containing the UI
		mainPanel = new JPanel(new BorderLayout());
		buttonPanel = new JPanel();
		batchPanel = createBatchPanel();
		batchScroller = createScrollPane(batchPanel);
	}

	private void addComponents() {
		// add buttons
		buttonPanel.add(addToBatchBtn);
		buttonPanel.add(clearBatchBtn);
		buttonPanel.add(saveBatchBtn);

		// add batch options
		buttonPanel.add(formatComboBox);
		buttonPanel.add(deleteOriginalsCheckBox);

		// add panels
		mainPanel.add(buttonPanel, BorderLayout.PAGE_START);
		mainPanel.add(batchScroller, BorderLayout.CENTER);
	}

	public void addActions() {

		addToBatchBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				update(State.OPEN);
			}
		});

		clearBatchBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				update(State.CLEAR);
			}
		});

		saveBatchBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				update(State.SAVE);
			}
		});
	}

	private void showFrame() {
		frame = new JFrame(RCStrings.TITLE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.add(mainPanel);

		frame.setLocationRelativeTo(null);
		repack();
		frame.setVisible(true);
	}

	private enum State {
		OPEN, CLEAR, SAVE;
	}

	private void update(State state) {
		switch (state) {
			case OPEN:
				loadImages();
				break;
			case CLEAR:
				createNewBatch();
				break;
			case SAVE:
				saveImages();
				break;
		}
		updateBatchView();
	}

	private void updateBatchView() {
		// update the batch view
		batchPanel.removeAll();
		for (BatchImage image : controller.getBatch()) {
			ImageView imageView = new ImageView(this, image);
			batchPanel.add(imageView);
		}
		repack();
	}

	private void repack() {
		frame.revalidate();
		frame.repaint();
		frame.pack();
	}

	// CONTROLLER METHODS

	private void loadImages() {
		JFileChooser fileChooser = createFileChooser();
		final int returnVal = fileChooser.showOpenDialog(mainPanel);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			controller.loadImages(fileChooser.getSelectedFiles());
		}
	}

	private void createNewBatch() {
		controller = new RCController();
	}

	private void saveImages() {
		String imageFormat = getSelectedFormat();
		controller.saveImages(imageFormat);
		boolean deleteOriginals = deleteOriginalsCheckBox.isSelected();
		controller.deleteImages(deleteOriginals);
	}

	protected String getSelectedFormat() {
		return (String) formatComboBox.getSelectedItem();
	}

	// GETTERS & SETTERS

	public RCController getController() {
		return controller;
	}

	public void setController(RCController controller) {
		this.controller = controller;
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

}