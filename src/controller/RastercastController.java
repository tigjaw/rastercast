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

	public String loadFiles(File[] chosenFiles) {
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
		String successLog = "Saving: \n";
		String failLog = "Failed to Save: \n";

		for (File image : images) {
			BufferedImage inputImg;

			if (imgType.equals("jpg")) {
				// image = new BufferedImage(BufferedImage.TYPE_INT_RGB);
			}

			final String parent = image.getParent();
			final String fileName = image.getName();

			try {
				inputImg = ImageIO.read(image);
			} catch (IOException e) {
				failLog += image.getName();
				e.printStackTrace();
				continue;
			}

			final String strippedFileName = stripExtension(fileName, false);
			final String strippedFilePath = parent + "\\" + strippedFileName;

			File outputImg = new File(strippedFilePath + "." + imgType);

			try {
				successLog += "\n" + outputImg.getName();
				ImageIO.write(inputImg, imgType, outputImg);
			} catch (IOException e) {
				failLog += "\n" + outputImg.getName();
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