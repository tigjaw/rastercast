package core;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import net.sf.image4j.codec.ico.ICOEncoder;

public class RasterCast {
	public static List<FileFilter> FILE_FILTERS;

	static {
		FILE_FILTERS = new LinkedList<FileFilter>();
		add("jpg", "jpeg");
		add("tif", "tiff");
		add("png");
		add("gif");
		add("bmp");
		add("ico");
	}

	// PUBLIC METHODS

	public static void add(String... exts) {
		FileFilter filter;
		if (exts.length == 1) {
			filter = createFilter(exts[0]);
		} else {
			filter = createFilter(exts[0], exts[1]);
		}
		FILE_FILTERS.add(filter);
	}

	public static void write(List<File> images, String format) {
		for (File file : images) {
			BufferedImage inputImage;
			File outputImage = createNewFile(file, format);
			try {
				inputImage = ImageIO.read(file);
				write(inputImage, format, outputImage);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static boolean write(BufferedImage inputImage, String imageFormat, File outputImage) {
		boolean parses = true;
		try {
			inputImage = applyColorModel(inputImage, imageFormat);
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

	public static boolean contains(String fileExtension) {
		for (FileFilter filter : FILE_FILTERS) {
			String desc = filter.getDescription();
			if (desc.equalsIgnoreCase(fileExtension)) {
				return true;
			}
		}
		return false;
	}

	public static String[] imageFormatsAsArray() {
		int size = FILE_FILTERS.size();
		String[] exts = new String[size];

		for (int i = 0; i < size; i++) {
			FileFilter imageFormat = FILE_FILTERS.get(i);
			String desc = imageFormat.getDescription();

			exts[i] = desc;
		}

		return exts;
	}

	// PRIVATE METHODS

	private static FileFilter createFilter(String ext) {
		return new FileNameExtensionFilter(ext, ext);
	}

	private static FileFilter createFilter(String ext1, String ext2) {
		return new FileNameExtensionFilter(ext1, ext1, ext2);
	}

	private static BufferedImage applyColorModel(BufferedImage inputImage, String imageFormat) {
		switch (imageFormat.toLowerCase()) {
			case "jpg":
			case "jpeg":
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

	private static File createNewFile(File file, String imageFormat) {
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
	private static String stripExtension(String fileName, boolean getExtension) {
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

}