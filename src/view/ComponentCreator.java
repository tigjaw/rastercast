package view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileFilter;

import core.RasterCast;
import utils.RCStrings;

public class ComponentCreator {

	public JFileChooser createFileChooser() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle(RCStrings.TXT_CHOOSER);
		fileChooser.setMultiSelectionEnabled(true);

		for (FileFilter filter : RasterCast.FILE_FILTERS) {
			fileChooser.addChoosableFileFilter(filter);
		}
		return fileChooser;
	}

	public IconButton createAddToBatchButton() {
		return new IconButton(RCStrings.TXT_OPEN, RCStrings.ICON_OPEN_MAIN,
				RCStrings.ICON_OPEN_ROLLOVER, RCStrings.ICON_OPEN_PRESSED);
	}

	public IconButton createClearBatchButton() {
		return new IconButton(RCStrings.TXT_CLEAR, RCStrings.ICON_CLEAR_MAIN,
				RCStrings.ICON_CLEAR_ROLLOVER, RCStrings.ICON_CLEAR_PRESSED);
	}

	public IconButton createSaveBatchButton() {
		return new IconButton(RCStrings.TXT_SAVE, RCStrings.ICON_SAVE_MAIN,
				RCStrings.ICON_SAVE_ROLLOVER, RCStrings.ICON_SAVE_PRESSED);
	}

	public JCheckBox createCheckBox() {
		return new JCheckBox(RCStrings.TXT_DELETE);
	}

	public JPanel createBatchPanel() {
		GridLayout oneColumn = new GridLayout(0, 1, 10, 10);
		JPanel batchPanel = new JPanel(oneColumn);
		batchPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		return batchPanel;
	}

	public JScrollPane createScrollPane(JPanel panel) {
		JScrollPane scroller = new JScrollPane(panel);
		scroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroller.setPreferredSize(new Dimension(500, 300));
		return scroller;
	}

}