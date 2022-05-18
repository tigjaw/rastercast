package view;

import java.awt.Insets;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.RastercastController;

public class ComponentCreator implements Buildable {
	private RastercastController controller;
	private String[] fileTypes;

	public ComponentCreator(RastercastView view) {
		this.controller = view.getController();
		this.fileTypes = controller.getFileTypes();
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
		return new JComboBox<String>(fileTypes);
	}

	@Override
	public JFileChooser createFileChooser(String title) {
		return createFileChooser(fileTypes, title);
	}

	private JFileChooser createFileChooser(String[] fileFilters, String title) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle(title);
		fileChooser.setMultiSelectionEnabled(true);

		for (String ff : fileFilters) {
			FileFilter filter = addFileFilter(ff);
			if (filter != null) {
				fileChooser.addChoosableFileFilter(filter);
			}
		}
		return fileChooser;
	}

	private FileFilter addFileFilter(String fileFilter) {
		FileFilter filter = null;
		if (fileFilter.equalsIgnoreCase("jpg") || fileFilter.equalsIgnoreCase("jpeg")) {
			filter = new FileNameExtensionFilter("jpg", new String[] { "jpg", "jpeg" });
		}
		if (fileFilter.equalsIgnoreCase("png")) {
			filter = new FileNameExtensionFilter("png", new String[] { "png" });
		}
		if (fileFilter.equalsIgnoreCase("gif")) {
			filter = new FileNameExtensionFilter("gif", new String[] { "gif" });
		}
		if (fileFilter.equalsIgnoreCase("tif") || fileFilter.equalsIgnoreCase("tiff")) {
			filter = new FileNameExtensionFilter("tif", new String[] { "tif", "tiff" });
		}
		return filter;
	}

	// SETTERS & GETTERS

	public RastercastController getController() {
		return controller;
	}

	public void setController(RastercastController controller) {
		this.controller = controller;
	}

	public String[] getFileTypes() {
		return fileTypes;
	}

	public void setFileTypes(String[] fileTypes) {
		this.fileTypes = fileTypes;
	}

}