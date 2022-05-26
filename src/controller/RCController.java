package controller;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import core.RasterCast;
import utils.RCLog;

/** Controller for the RasterCast demo Application
 * @author <a href="https://github.com/tigjaw">Tigjaw</a> (Joshua Woodyatt)
 * @since 2022 */
public class RCController {
	private List<File> images;
	private boolean deleteOriginals;

	// CONSTRUCTORS

	public RCController() {
		this.images = new LinkedList<>();
		this.deleteOriginals = false;
	}

	// CONTROLLER METHODS

	public String loadImages(File[] selectedImages) {
		RCLog.action("Opened:");
		RCLog.actionFails("Failed to open:");

		images = new LinkedList<>();

		for (File image : selectedImages) {
			boolean loaded = false;
			String fileName = image.getName();
			if (isCompatible(fileName)) {
				images.add(image);
				loaded = true;
			}
			RCLog.log(fileName, loaded);
		}
		return RCLog.asString();
	}

	public String saveImages(String imageFormat) {
		RCLog.action("Saved:");
		RCLog.actionFails("Failed to save:");

		for (File file : images) {
			File output = createNewFile(file, imageFormat);
			boolean written = false;
			written = write(file, imageFormat, output);
			RCLog.log(output.getName(), written);
		}

		return RCLog.asString();
	}

	/** Deletes the original files
	 * @param delete (boolean) - delete files if true
	 * @return (String) - text to log */
	public String deleteImages(boolean delete) {
		RCLog.action("Deleted Originals.");
		if (delete) {
			for (File image : images) {
				image.delete();
			}
		} else {
			RCLog.action("Kept Originals.");
		}
		return RCLog.getACTION();
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

	public List<File> getImages() {
		return images;
	}

	public void setImages(List<File> images) {
		this.images = images;
	}

	public boolean isDeleteOriginals() {
		return deleteOriginals;
	}

	public void setDeleteOriginals(boolean deleteOriginals) {
		this.deleteOriginals = deleteOriginals;
	}

}