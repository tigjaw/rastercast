package main;

import javax.swing.SwingUtilities;

import controller.RastercastController;
import view.RastercastView;

/** Can convert between the following image formats: jpg, png, tiff, gif
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