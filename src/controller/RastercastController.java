package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.filechooser.FileFilter;

import model.ImageFormats;
import view.RLog;

public class RastercastController {
	private ImageFormats imageFormats;
	private List<File> images;
	private boolean deleteOriginals;

	// CONSTRUCTORS

	public RastercastController() {
		this(new ImageFormats());
	}

	public RastercastController(String[] imageFormats) {
		this(new ImageFormats(imageFormats));
	}

	public RastercastController(ImageFormats imageFormats) {
		this.images = new LinkedList<>();
		this.imageFormats = imageFormats;
		this.deleteOriginals = false;
	}

	// CONTROLLER METHODS

	public String loadImages(File[] selectedImages) {
		RLog.action("Opened:");
		RLog.actionFails("Failed to open:");
		images = new LinkedList<>();

		for (File image : selectedImages) {
			String fileName = image.getName();
			String extension = stripExtension(fileName, true);
			if (imageFormats.contains(extension)) {
				images.add(image);
				RLog.success(fileName);
			} else {
				RLog.failure(fileName);
			}
		}
		return RLog.asString();
	}

	public String saveImages(String imageFormat) {
		RLog.action("Saved:");
		RLog.actionFails("Failed to save:");

		for (File file : images) {
			String parent = file.getParent();
			String fileName = file.getName();

			String strippedFileName = stripExtension(fileName, false);
			String strippedFilePath = parent + "\\" + strippedFileName;

			BufferedImage inputImage;
			File outputImage = new File(strippedFilePath + "." + imageFormat);

			boolean written = false;

			try {
				inputImage = ImageIO.read(file);
				written = write(inputImage, imageFormat, outputImage);
			} catch (IOException e) {
				e.printStackTrace();
			}

			fileName = outputImage.getName();
			if (written) {
				RLog.success(fileName);
			} else {
				RLog.failure(fileName);
			}
		}

		return RLog.asString();
	}

	private boolean write(BufferedImage inputImage, String imageFormat, File outputImage) {
		inputImage = imageFormats.applyColorModel(inputImage, imageFormat);
		return imageFormats.encode(inputImage, imageFormat, outputImage);
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
		RLog.action("Deleted Originals.");
		if (delete) {
			for (File image : images) {
				image.delete();
			}
		} else {
			RLog.action("Kept Originals.");
		}
		return RLog.getACTION();
	}

	// GETTERS & SETTERS

	public String[] getImageTypesAsArray() {
		return imageFormats.asArray();
	}

	public List<FileFilter> getFileFilters() {
		return imageFormats.getFileFilters();
	}

	public ImageFormats getImageFormats() {
		return imageFormats;
	}

	public void setImageFormats(ImageFormats imageFormats) {
		this.imageFormats = imageFormats;
	}

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