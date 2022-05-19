package view;

import java.awt.Insets;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileFilter;

import controller.RastercastController;

public class ComponentCreator implements Buildable {
	private RastercastController controller;

	// CONSTRUCTORS

	public ComponentCreator(RastercastView view) {
		this.controller = view.getController();
	}

	// FACTORY METHODS

	@Override
	public JTextArea createLogArea() {
		return createLogArea(25, 25);
	}

	@Override
	public JTextArea createLogArea(int rows, int cols) {
		JTextArea logArea = new JTextArea(rows, cols);
		logArea.setMargin(new Insets(5, 5, 5, 5));
		logArea.setEditable(false);
		return logArea;
	}

	@Override
	public JComboBox<String> createFileTypeComboBox() {
		return new JComboBox<String>(controller.getImageTypesAsArray());
	}

	@Override
	public JFileChooser createFileChooser(String title) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle(title);
		fileChooser.setMultiSelectionEnabled(true);

		for (FileFilter filter : controller.getFileFilters()) {
			fileChooser.addChoosableFileFilter(filter);
		}
		return fileChooser;
	}

	// SETTERS & GETTERS

	public RastercastController getController() {
		return controller;
	}

	public void setController(RastercastController controller) {
		this.controller = controller;
	}

}