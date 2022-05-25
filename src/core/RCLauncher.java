package core;

import javax.swing.SwingUtilities;

import controller.RCController;
import view.RCView;

/** Can convert between several image formats, in any direction.
 * See the default formats in the UIValues class.
 * @author Tigjaw */
public class RCLauncher implements Runnable {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new RCLauncher());
	}

	@Override
	public void run() {
		new RCView(new RCController());
	}

}