package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;

public class RastercastController {
	private String[] fileTypes;
	private File file;
	private boolean deleteOriginal;

	public RastercastController() {
		this(new String[] { "png", "jpg", "gif", "tif", });
		// add bmp option
	}

	public RastercastController(String[] fileTypes) {
		this.fileTypes = fileTypes;
		this.deleteOriginal = false;
	}

	public String loadFile(File file) {
		String fileType = stripExtension(file.getName(), true);

		// Convert the array to list
		List<String> list = Arrays.asList(fileTypes);

		if (list.contains(fileType)) {
			this.file = file;
			return "Opening: " + file.getName();
		} else {
			return "file-type (." + fileType + ") not supported";
		}
	}

	public String saveFile(Object selectedItem) {
		String log = "";
		BufferedImage input_image;

		try {
			input_image = ImageIO.read(file);
		} catch (IOException e2) {
			log += "Failed to read image";
			e2.printStackTrace();
			return log;
		}

		final String parent = file.getParent();
		log += "\n" + "File Path: " + parent;

		final String fileName = file.getName();
		log += "\n" + "File Name: " + fileName;

		final String strippedFileName = stripExtension(fileName, false);
		log += "\n" + "Stripped File Name: " + strippedFileName;

		final String strippedFilePath = parent + "\\" + strippedFileName;
		log += "\n" + "Stripped File Path: " + strippedFilePath;

		final String fileType = (String) selectedItem;
		log += "\n" + "Convert to: " + fileType;
		File outputfile = new File(strippedFilePath + "." + fileType);

		if (deleteOriginal) {
			file.delete();
		}

		try {
			log += "\n" + "Saving: " + strippedFileName + "." + fileType;
			ImageIO.write(input_image, fileType, outputfile);
			log += "\n" + "Image Converted Successfully!";
		} catch (IOException e1) {
			log += "\n" + "Failed to write file";
			e1.printStackTrace();
		}
		return log;
	}

	private String stripExtension(String str, boolean wantExtension) {
		if (str == null) {
			return null;
		}
		// Get position of last '.'.
		int pos = str.lastIndexOf(".");
		// If there wasn't any '.' just return the string as is.
		if (pos == -1) {
			return str;
		}

		if (wantExtension) {
			return str.substring(pos + 1, str.length());
		} else {
			// Otherwise return the string, up to the dot.
			return str.substring(0, pos);
		}
	}

	public String[] getFileTypes() {
		return fileTypes;
	}

	public void setFileTypes(String[] fileTypes) {
		this.fileTypes = fileTypes;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileName() {
		return file.getName();
	}

	public boolean isDeleteOriginal() {
		return deleteOriginal;
	}

	public void setDeleteOriginal(boolean deleteOriginal) {
		this.deleteOriginal = deleteOriginal;
	}

}