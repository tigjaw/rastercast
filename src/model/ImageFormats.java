package model;

import java.util.LinkedList;
import java.util.List;

import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import view.UIValues;

public class ImageFormats {
	private List<FileFilter> fileFilters;

	public ImageFormats() {
		this(UIValues.FILE_FORMATS);
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
				filter = new FileNameExtensionFilter("jpg", new String[] { "jpg", "jpeg" });
				break;
			case "tif":
			case "tiff":
				filter = new FileNameExtensionFilter("tif", new String[] { "tif", "tiff" });
				break;
			case "bmp":
				filter = new FileNameExtensionFilter("bmp", new String[] { "bmp" });
				break;
			case "png":
				filter = new FileNameExtensionFilter("png", new String[] { "png" });
				break;
			case "gif":
				filter = new FileNameExtensionFilter("gif", new String[] { "gif" });
				break;
		}
		return filter;
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