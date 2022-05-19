package main;

import javax.swing.SwingUtilities;

import controller.RastercastController;
import view.RastercastView;

/** Can convert between several image formats, in any direction.
 * See the default formats in the UIValues class.
 * @author Tigjaw */
public class RunRastercast implements Runnable {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new RunRastercast());
	}

	@Override
	public void run() {
		new RastercastView(new RastercastController());
	}

}