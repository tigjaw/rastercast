package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileFilter;

import core.RasterCast;
import utils.RCStrings;

public class ComponentFactory {

	public static JTextArea createLogArea() {
		return createLogArea(25, 50);
	}

	public static JTextArea createLogArea(int rows, int cols) {
		JTextArea logArea = new JTextArea(rows, cols);
		logArea.setMargin(new Insets(5, 5, 5, 5));
		logArea.setEditable(false);
		return logArea;
	}

	public static JComboBox<String> createFileTypeComboBox() {
		return new JComboBox<String>(RasterCast.formatsAsArray());
	}

	public static JFileChooser createFileChooser() {
		return createFileChooser(RCStrings.TXT_CHOOSER);
	}

	public static JFileChooser createFileChooser(String title) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle(title);
		fileChooser.setMultiSelectionEnabled(true);

		for (FileFilter filter : RasterCast.FILE_FILTERS) {
			fileChooser.addChoosableFileFilter(filter);
		}
		return fileChooser;
	}

	public static IconButton createOpenButton() {
		return createButton(RCStrings.TXT_OPEN, true, RCStrings.ICON_OPEN_MAIN,
				RCStrings.ICON_OPEN_ROLLOVER, RCStrings.ICON_OPEN_PRESSED);
	}

	public static IconButton createSaveButton() {
		return createButton(RCStrings.TXT_SAVE, true, RCStrings.ICON_SAVE_MAIN,
				RCStrings.ICON_SAVE_ROLLOVER, RCStrings.ICON_SAVE_PRESSED);
	}

	public static IconButton createButton(String toolTipText, boolean isOpaque, String iconPath,
			String rolloverIconPath, String pressedIconPath) {
		IconButton button = new IconButton(toolTipText, isOpaque);
		button.addIcon(iconPath);
		button.addRolloverIcon(rolloverIconPath);
		button.addPressedIcon(pressedIconPath);
		return button;
	}

	public static JCheckBox createCheckBox() {
		return createCheckBox(RCStrings.TXT_DELETE);
	}

	public static JCheckBox createCheckBox(String title) {
		return new JCheckBox(title);
	}

	public static JPanel createMainPanel() {
		return createPanel(new BorderLayout());
	}

	public static JPanel createPanel() {
		return new JPanel();
	}

	public static JPanel createPanel(LayoutManager layout) {
		return new JPanel(layout);
	}

	public static JScrollPane createScrollePane(Component view) {
		return new JScrollPane(view);
	}

}