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
		this.imageFormats = imageFormats;
		this.deleteOriginals = false;
	}

	// CONTROLLER METHODS

	public String loadImages(File[] selectedImages) {
		images = new LinkedList<>();
		String successLog = "Opening: \n";
		String failLog = "Unsupported file-types: \n";

		for (File image : selectedImages) {
			String fileName = image.getName();
			String extension = stripExtension(fileName, true);

			if (imageFormats.contains(extension)) {
				images.add(image);
				successLog += fileName + "\n";
			} else {
				failLog += fileName + "\n";
			}
		}
		return successLog + "\n" + failLog;
	}

	public String saveImages(String imageType) {
		String successLog = "\n Saving:\n";
		String failLog = "\n Failed to Save:\n";

		for (File file : images) {
			BufferedImage inputImage;

			final String parent = file.getParent();
			final String fileName = file.getName();

			try {
				inputImage = ImageIO.read(file);

				if (imageType.equals("jpg")) {
					int width = inputImage.getWidth();
					int height = inputImage.getHeight();
					int type = BufferedImage.TYPE_INT_RGB;
					BufferedImage jpg = new BufferedImage(width, height, type);
					jpg.getGraphics().drawImage(inputImage, 0, 0, null);
					inputImage = jpg;
				}
			} catch (IOException e) {
				failLog += file.getName();
				e.printStackTrace();
				continue;
			}

			final String strippedFileName = stripExtension(fileName, false);
			final String strippedFilePath = parent + "\\" + strippedFileName;

			File outputImage = new File(strippedFilePath + "." + imageType);

			try {
				boolean written = ImageIO.write(inputImage, imageType, outputImage);
				if (written) {
					successLog += "\n" + outputImage.getName();
				} else {
					throw new IOException();
				}
			} catch (IOException e) {
				failLog += "\n" + outputImage.getName();
				e.printStackTrace();
			}
		}
		return successLog + "\n" + failLog;
	}

	public String deleteImages(boolean delete) {
		if (delete) {
			for (File image : images) {
				image.delete();
			}
			return "\n" + "Deleting Originals";
		} else {
			return "\n" + "Keeping Originals";
		}
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