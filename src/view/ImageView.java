package view;

import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import controller.BatchImage;

@SuppressWarnings("serial")
public class ImageView extends JPanel {
	private RCView view; // view
	private BatchImage image; // model
	// UI components
	private JLabel oldFormat;
	private JLabel path, name;

	public ImageView(RCView view, BatchImage image) {
		super(new FlowLayout(FlowLayout.LEFT));
		this.view = view;
		this.image = image;
		createAndShowPanel();
	}

	private void createAndShowPanel() {
		createComponents();
		addBorders();
		addActions();
		addComponents();
	}

	private void createComponents() {
		oldFormat = createLabel(image.getOldFormat());
		path = createLabel(image.getOldPath());
		name = createLabel(image.getOldName());
	}

	private JLabel createLabel(String text) {
		JLabel label = new JLabel(text);
		label.setToolTipText(text);
		label.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
		return label;
	}

	private void addBorders() {
		Border border = BorderFactory.createEtchedBorder();
		setBorder(border);
		oldFormat.setBorder(border);
		path.setBorder(border);
		name.setBorder(border);
	}

	private void addActions() {
		// TODO add action listeners
	}

	public void changePath(String newPath) {
		path.setText(newPath);
	}

	public void changeName(String newName) {
		name.setText(newName);
	}

	private void addComponents() {
		add(oldFormat);
		add(path);
		add(name);
	}

	// GETTERS & SETTERS

	public RCView getView() {
		return view;
	}

	public void setView(RCView view) {
		this.view = view;
	}

	public BatchImage getImage() {
		return image;
	}

	public void setImage(BatchImage image) {
		this.image = image;
	}

}