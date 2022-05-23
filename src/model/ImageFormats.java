package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import net.sf.image4j.codec.ico.ICOEncoder;

public class ImageFormats {
	private List<FileFilter> fileFilters;

	public ImageFormats() {
		this(new String[] { "png", "jpg", "gif", "tif", "bmp", "ico" });
	}

	public ImageFormats(String[] fileExtensions) {
		this.fileFilters = new LinkedList<FileFilter>();

		boolean parses = false;
		for (String imageFormat : fileExtensions) {
			parses = add(imageFormat);
		}
		if (!parses) {
			System.out.println("no valid image format");
		}
	}

	// METHODS

	protected boolean add(String imageFormat) {
		FileFilter filter = createFileFilter(imageFormat);
		if (filter != null) {
			fileFilters.add(filter);
			return true;
		} else {
			return false;
		}
	}

	private FileFilter createFileFilter(String fileExtension) {
		FileFilter filter = null;
		switch (fileExtension.toLowerCase()) {
			case "jpg":
			case "jpeg":
				filter = createFilter("jpg", "jpeg");
				break;
			case "tif":
			case "tiff":
				filter = createFilter("tif", "tiff");
				break;
			case "png":
				filter = createFilter("png");
				break;
			case "gif":
				filter = createFilter("gif");
				break;
			case "bmp":
				filter = createFilter("bmp");
				break;
			case "ico":
				filter = createFilter("ico");
				break;
		}
		return filter;
	}

	private FileNameExtensionFilter createFilter(String ext) {
		return new FileNameExtensionFilter(ext, ext);
	}

	private FileNameExtensionFilter createFilter(String ext1, String ext2) {
		return new FileNameExtensionFilter(ext1, ext1, ext2);
	}

	public BufferedImage applyColorModel(BufferedImage inputImage, String imageFormat) {
		switch (imageFormat.toLowerCase()) {
			case "jpg":
			case "bmp":
				int width = inputImage.getWidth();
				int height = inputImage.getHeight();
				int type = BufferedImage.TYPE_INT_RGB;
				BufferedImage rgb = new BufferedImage(width, height, type);
				rgb.getGraphics().drawImage(inputImage, 0, 0, null);
				inputImage = rgb;
				break;
		}
		return inputImage;
	}

	public boolean encode(BufferedImage inputImage, String imageFormat, File outputImage) {
		boolean parses = true;
		try {
			if (imageFormat.equalsIgnoreCase("ico")) {
				ICOEncoder.write(inputImage, outputImage);
			} else {
				parses = ImageIO.write(inputImage, imageFormat, outputImage);
			}
		} catch (IOException e) {
			e.printStackTrace();
			parses = false;
		}
		return parses;
	}

	public boolean contains(String fileExtension) {
		for (FileFilter filter : fileFilters) {
			String desc = filter.getDescription();
			if (desc.equalsIgnoreCase(fileExtension)) {
				return true;
			}
		}
		return false;
	}

	public String[] asArray() {
		int size = fileFilters.size();
		String[] exts = new String[size];

		for (int i = 0; i < size; i++) {
			FileFilter imageFormat = fileFilters.get(i);
			String desc = imageFormat.getDescription();

			exts[i] = desc;
		}

		return exts;
	}

	// GETTERS & SETTERS

	public List<FileFilter> getFileFilters() {
		return fileFilters;
	}

	public void setFileFilters(List<FileFilter> fileFilters) {
		this.fileFilters = fileFilters;
	}

}