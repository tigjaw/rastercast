package view;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;

public interface Buildable {

	public JTextArea createLogArea();

	public JTextArea createLogArea(int rows, int cols);

	public JComboBox<String> createFileTypeComboBox();

	public JFileChooser createFileChooser(String title);

}