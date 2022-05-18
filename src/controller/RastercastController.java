package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;

public class RastercastController {
	private String[] fileTypes;
	private List<File> images;
	private boolean deleteOriginal;

	public RastercastController() {
		this(new String[] { "png", "jpg", "gif", "tif", });
		// add bmp option?
	}

	public RastercastController(String[] fileTypes) {
		this.fileTypes = fileTypes;
		this.deleteOriginal = false;
	}

	public String loadImages(File[] chosenFiles) {
		images = new LinkedList<>();
		List<String> list = Arrays.asList(fileTypes);
		String successLog = "Opening: \n";
		String failLog = "Unsupported file-types: \n";

		for (File file : chosenFiles) {
			String fileName = file.getName();
			String fileType = stripExtension(fileName, true);

			if (list.contains(fileType)) {
				images.add(file);
				successLog += fileName + "\n";
			} else {
				failLog += fileName + "\n";
			}
		}
		return successLog + "\n" + failLog;
	}

	public String saveImages(String imgType) {
		String successLog = "\n Saving:\n";
		String failLog = "\n Failed to Save:\n";

		for (File file : images) {
			BufferedImage inputImg;

			final String parent = file.getParent();
			final String fileName = file.getName();

			try {
				inputImg = ImageIO.read(file);

				if (imgType.equals("jpg")) {
					int width = inputImg.getWidth();
					int height = inputImg.getHeight();
					int type = BufferedImage.TYPE_INT_RGB;
					BufferedImage jpg = new BufferedImage(width, height, type);
					jpg.getGraphics().drawImage(inputImg, 0, 0, null);
					inputImg = jpg;
				}
			} catch (IOException e) {
				failLog += file.getName();
				e.printStackTrace();
				continue;
			}

			final String strippedFileName = stripExtension(fileName, false);
			final String strippedFilePath = parent + "\\" + strippedFileName;

			File outputImg = new File(strippedFilePath + "." + imgType);

			try {
				boolean written = ImageIO.write(inputImg, imgType, outputImg);
				if (written) {
					successLog += "\n" + outputImg.getName();
				} else {
					throw new IOException();
				}
			} catch (IOException e) {
				failLog += "\n" + outputImg.getName();
				e.printStackTrace();
			}
		}
		return successLog + "\n" + failLog;
	}

	public String deleteImages(boolean delete) {
		if (delete) {
			for (File img : images) {
				img.delete();
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
	 * @param str (String) - the text to strip
	 * @param getExtension (boolean)
	 * @return (String) - the stripped String */
	private String stripExtension(String str, boolean getExtension) {
		if (str == null) {
			return null;
		}
		// Get position of last '.'.
		int pos = str.lastIndexOf(".");
		// If there wasn't any '.' just return the string as is.
		if (pos == -1) {
			return str;
		}

		if (getExtension) {
			// return the string, following the dot
			return str.substring(pos + 1, str.length());
		} else {
			// return the string, up to the dot.
			return str.substring(0, pos);
		}
	}

	public String[] getFileTypes() {
		return fileTypes;
	}

	public void setFileTypes(String[] fileTypes) {
		this.fileTypes = fileTypes;
	}

	public List<File> getImages() {
		return images;
	}

	public void setImages(List<File> images) {
		this.images = images;
	}

	public boolean isDeleteOriginal() {
		return deleteOriginal;
	}

	public void setDeleteOriginal(boolean deleteOriginal) {
		this.deleteOriginal = deleteOriginal;
	}

}