package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;

import core.RasterCast;
import utils.RCLog;

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
			String extension = stripExtension(fileName, true);
			if (RasterCast.contains(extension)) {
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
			BufferedImage inputImage;
			File outputImage = createNewFile(file, imageFormat);

			boolean written = false;

			try {
				inputImage = ImageIO.read(file);
				written = RasterCast.write(inputImage, imageFormat, outputImage);
			} catch (IOException e) {
				e.printStackTrace();
			}

			RCLog.log(outputImage.getName(), written);
		}

		return RCLog.asString();
	}

	private File createNewFile(File file, String imageFormat) {
		String parent = file.getParent();
		String fileName = file.getName();

		String strippedFileName = stripExtension(fileName, false);
		String strippedFilePath = parent + "\\" + strippedFileName;

		return new File(strippedFilePath + "." + imageFormat);
	}

	/** Strips the string at the last index of the "." in the filename.
	 * If getExtension is true, then it returns the stripped file-type (without
	 * the file name) by returning the contents of the String after the "." in
	 * the file-name. If getExtension is false, then it returns the stripped
	 * file-name (without the file extension) by returning the contents of the
	 * String prior to the "." in the file-name.
	 * @param fileName (String) - the text to strip
	 * @param getExtension (boolean)
	 * @return (String) - the stripped String */
	private String stripExtension(String fileName, boolean getExtension) {
		if (fileName == null) {
			return null;
		}
		// Get position of last '.'.
		int dot = fileName.lastIndexOf(".");
		// If there wasn't any '.' just return the string as is.
		if (dot == -1) {
			return fileName;
		}

		if (getExtension) {
			// return the string, following the dot
			return fileName.substring(dot + 1, fileName.length());
		} else {
			// return the string, up to the dot.
			return fileName.substring(0, dot);
		}
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