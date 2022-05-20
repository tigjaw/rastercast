package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.filechooser.FileFilter;

import model.ImageFormats;

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
		String successLog = "";
		String failLog = "";
		images = new LinkedList<>();

		for (File image : selectedImages) {
			String fileName = image.getName();
			String extension = stripExtension(fileName, true);
			if (imageFormats.contains(extension)) {
				images.add(image);
				successLog += "\n> " + fileName;
			} else {
				failLog += "\n\t> " + fileName;
			}
		}

		if (successLog.isEmpty() == false) {
			successLog = "\nOpening:\n" + successLog;
		}
		if (failLog.isEmpty() == false) {
			failLog = "\n\n\tUnsupported file-types:\n" + failLog;
		}
		return successLog + failLog;
	}

	public String saveImages(String imageFormat) {
		String successLog = "";
		String failLog = "";

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

				// jpg requires RGB colour model
				if (imageFormat.equalsIgnoreCase("jpg")) {
					int width = inputImage.getWidth();
					int height = inputImage.getHeight();
					int type = BufferedImage.TYPE_INT_RGB;
					BufferedImage jpg = new BufferedImage(width, height, type);
					jpg.getGraphics().drawImage(inputImage, 0, 0, null);
					inputImage = jpg;
				}

				written = ImageIO.write(inputImage, imageFormat, outputImage);

			} catch (IOException e) {
				written = false;
				e.printStackTrace();
			}

			if (written) {
				successLog += "\n> " + outputImage.getName();
			} else {
				failLog += "\n\t> " + outputImage.getName();
			}
		}

		if (successLog.isEmpty() == false) {
			successLog = "\nSaving:\n" + successLog;
		}
		if (failLog.isEmpty() == false) {
			failLog = "\n\n\tFailed to Save:\n" + failLog;
		}

		return successLog + failLog;
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
		if (delete) {
			for (File image : images) {
				image.delete();
			}
			return "\n" + "Deleting Originals.";
		} else {
			return "\n" + "Keeping Originals.";
		}
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