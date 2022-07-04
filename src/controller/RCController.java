package controller;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import core.RasterCast;

/** Controller for the RasterCast demo Application
 * @author <a href="https://github.com/tigjaw">Tigjaw</a> (Joshua Woodyatt)
 * @since 2022 */
public class RCController {
	private List<BatchImage> batch;

	// CONSTRUCTORS

	public RCController() {
		batch = new LinkedList<BatchImage>();
	}

	// CONTROLLER METHODS

	public boolean loadImages(File[] selectedImages) {
		boolean loaded = false;
		for (File image : selectedImages) {
			String fileName = image.getName();
			if (isCompatible(fileName)) {
				batch.add(new BatchImage(image));
				loaded = true;
			}
		}
		return loaded;
	}

	public boolean saveImages(String imageFormat) {
		boolean written = false;
		for (BatchImage image : batch) {
			File intput = image.getFile();
			File output = createNewFile(intput, imageFormat);
			written = write(intput, imageFormat, output);
		}
		return written;
	}

	/** Deletes the original files
	 * @param delete (boolean) - delete files if true
	 * @return (String) - text to log */
	public boolean deleteImages(boolean delete) {
		if (delete) {
			for (BatchImage image : batch) {
				image.getFile().delete();
			}
		}
		return delete;
	}

	// RASTERCAST METHODS

	private boolean isCompatible(String fileName) {
		String extension = stripExtension(fileName);
		return RasterCast.contains(extension);
	}

	private String stripExtension(String fileName) {
		return RasterCast.stripExt(fileName, true);
	}

	private File createNewFile(File file, String imageFormat) {
		return RasterCast.createNewFile(file, imageFormat);
	}

	private boolean write(File file, String imageFormat, File outputImage) {
		return RasterCast.write(file, imageFormat, outputImage);
	}

	// GETTERS & SETTERS

	public List<BatchImage> getBatch() {
		return batch;
	}

	public void setBatch(List<BatchImage> batch) {
		this.batch = batch;
	}

}