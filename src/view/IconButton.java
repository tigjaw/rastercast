package view;

import java.awt.Image;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class IconButton extends JButton {

	/** No ToolTip. Is Opaque. Set Main Icon Path.
	 * @param path */
	public IconButton(String path) {
		this(true, path);
	}

	/** No ToolTip. Set Opacity. Set Main Icon Path.
	 * @param path
	 * @param isOpaque */
	public IconButton(boolean isOpaque, String path) {
		this(null, isOpaque, path);
	}

	/** Set ToolTip. Is Opaque. Set Main Icon Path.
	 * @param toolTipText
	 * @param path */
	public IconButton(String toolTipText, String path) {
		this(toolTipText, true, path);
	}

	public IconButton(String text, String path, String rolloverPath, String pressedPath) {
		this(text, true, path, rolloverPath, pressedPath);
	}

	public IconButton(String text, boolean isOpaque, String... paths) {
		paint(isOpaque);
		if (text != null) {
			setToolTipText(text);
		}
		if (paths != null) {
			if (paths.length > 0) {
				addIcon(paths[0]);
			}
			if (paths.length > 1) {
				addRolloverIcon(paths[1]);
			}
			if (paths.length > 2) {
				addPressedIcon(paths[2]);
			}
			if (paths.length > 3) {
				addDisabledIcon(paths[3]);
			}
		}
	}

	public void addIcon(String path) {
		setIcon(buildIcon(path));
	}

	public void addRolloverIcon(String path) {
		setRolloverIcon(buildIcon(path));
	}

	public void addPressedIcon(String path) {
		setPressedIcon(buildIcon(path));
	}

	public void addDisabledIcon(String path) {
		setDisabledIcon(buildIcon(path));
	}

	private ImageIcon buildIcon(String path) {
		ImageIcon icon = new ImageIcon(path);
		Image newimg = icon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		icon = new ImageIcon(newimg);
		return icon;
	}

	private void paint(boolean isOpaque) {
		setBorderPainted(isOpaque);
		setBorder(null);
		setMargin(new Insets(0, 0, 0, 0));
		setContentAreaFilled(isOpaque);
		setFocusPainted(isOpaque);
		setOpaque(isOpaque);
	}

}