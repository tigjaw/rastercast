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

/** {@link RasterCast} - batch image converter.
 * <p>
 * Converts between various image formats and even create .ico files.
 * <p>
 * Formats supported by default:
 * <ul>
 * <li>jpg (jpeg)</li>
 * <li>tif (tiff)</li>
 * <li>png</li>
 * <li>gif</li>
 * <li>bmp</li>
 * <li>ico</li>
 * </ul>
 * @author <a href="https://github.com/tigjaw">Tigjaw</a> (Joshua Woodyatt)
 * @since 2022
 * @see net.sf.image4j.codec.ico.ICOEncoder */
public class RasterCast {
	/** The {@link List} of compatible {@link FileFilter}s */
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

	/** Use this method to add an additional file format to the list of
	 * {@link RasterCast.FILE_FILTERS}.
	 * <p>
	 * Parse one string to add a {@link FileFilter} where the {@code FileFilter}
	 * description is the same as its file extension.
	 * <p>
	 * Every additional parsed parameter will be an additional extension.
	 * <p>
	 * Example usages:
	 * <p>
	 * {@code add("png")}
	 * <p>
	 * {@code add("jpg", "jpeg")}
	 * @param formats {@link String} to add */
	public static void add(String... formats) {
		FileFilter filter;
		if (formats.length == 1) {
			filter = createFilter(formats[0]);
		} else {
			filter = createFilter(formats[0], formats[1]);
		}
		FILE_FILTERS.add(filter);
	}

	/** Takes a {@link List} of {@link File}s and outputs them as the chosen
	 * format.
	 * @param files {@link List<File>} to convert
	 * @param format {@link String} to convert to */
	public static void write(List<File> files, String format) {
		for (File file : files) {
			File outputImage = createNewFile(file, format);
			write(file, format, outputImage);
		}
	}

	/** Writes an image to the chosen file format using {@link ImageIO} or
	 * {@link ICOEncoder}.
	 * <p>
	 * Checks if the chosen format is an ico or not and then writes to the
	 * output {@link File}.
	 * <p>
	 * Use this write method if you wish to provide the output file yourself.
	 * @param input ({@link File}) to be written.
	 * @param format ({@link String}) image format to convert to.
	 * @param output ({@link File}) to be written to.
	 * @return ({@link Boolean}) - {@code false} if file was not written */
	public static boolean write(File input, String format, File output) {
		boolean parses = true;
		try {
			BufferedImage inputImage = read(input, format);
			if (format.equalsIgnoreCase("ico")) {
				ICOEncoder.write(inputImage, output);
			} else {
				parses = ImageIO.write(inputImage, format, output);
			}
		} catch (IOException e) {
			e.printStackTrace();
			parses = false;
		}
		return parses;
	}

	/** Writes an image to the chosen file format using {@link ImageIO} or
	 * {@link ICOEncoder}.
	 * <p>
	 * Creates the output {@link File} from the input {@code File} parameter
	 * with {@code createNewFile(input, format)}, checks if the chosen format is
	 * an ico or not and then writes the file.
	 * @param input ({@link File}) to be written.
	 * @param format ({@link String}) to convert to.
	 * @return ({@link Boolean}) - {@code false} if file was not written */
	public static boolean write(File input, String format) {
		boolean parses = true;
		try {
			BufferedImage inputImage = read(input, format);
			File outputImage = createNewFile(input, format);
			if (format.equalsIgnoreCase("ico")) {
				ICOEncoder.write(inputImage, outputImage);
			} else {
				parses = ImageIO.write(inputImage, format, outputImage);
			}
		} catch (IOException e) {
			e.printStackTrace();
			parses = false;
		}
		return parses;
	}

	/** Reads the input {@link File} using {@link ImageIO} and applies a colour
	 * model in the case it is a "png" or "bmp" to be written.
	 * @param input ({@link File}) to be read.
	 * @param format ({@link String}) to convert to.
	 * @return {@link BufferedImage} to write
	 * @throws IOException if an error occurs during
	 *         {@code ImageIO.read(input)} */
	public static BufferedImage read(File input, String format) throws IOException {
		BufferedImage inputImage = ImageIO.read(input);
		return applyColorModel(inputImage, format);
	}

	/** Returns a new {@link File} with same name but with the specified file
	 * format. Gets the parent and name from the {@code File}, strips the
	 * extension from the name, and creates a new path with the specified format
	 * appended onto the file name.
	 * @param file {@link File} to convert.
	 * @param format {@link String} to convert to.
	 * @return {@link File} with chosen format */
	public static File createNewFile(File file, String format) {
		String parent = file.getParent();
		String fileName = file.getName();

		String strippedFileName = stripExt(fileName, false);
		String strippedFilePath = parent + "\\" + strippedFileName;

		return new File(strippedFilePath + "." + format);
	}

	/** Checks if the passed string matches a compatible file extension.
	 * Checks if the passed file extension is contained in the {@link List} of
	 * {@link FileFilter}s
	 * @param ext {@link String} to check
	 * @return {@link Boolean} - false if not compatible */
	public static boolean contains(String ext) {
		for (FileFilter filter : FILE_FILTERS) {
			String desc = filter.getDescription();
			if (desc.equalsIgnoreCase(ext)) {
				return true;
			}
		}
		return false;
	}

	/** Returns the {@link List} of {@link FileFilter} as an array
	 * @return {@link String[]} of {@code FileFilter} */
	public static String[] formatsAsArray() {
		int size = FILE_FILTERS.size();
		String[] exts = new String[size];

		for (int i = 0; i < size; i++) {
			FileFilter imageFormat = FILE_FILTERS.get(i);
			String desc = imageFormat.getDescription();

			exts[i] = desc;
		}

		return exts;
	}

	/** Strips the string at the last index of the "." in the filename.
	 * <p>
	 * If {@code getExtension} is true, then it returns the stripped extension
	 * (without the file name) by returning the contents of the String after the
	 * "." in the file-name.
	 * <p>
	 * If {@code getExtension} is false, then it returns the stripped file name
	 * (without the file extension) by returning the contents of the
	 * {@code String} prior to the "." in the file-name.
	 * @param fileName ({@link String}) to strip
	 * @param getExtension ({@link Boolean}) retrieve extension or filename
	 * @return {@link String} - the stripped text */
	public static String stripExt(String fileName, boolean getExtension) {
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

	// PRIVATE METHODS

	/** Creates a {@link FileFilter} for a file format that only has one file
	 * extension, such as png, gif, and bmp.
	 * @param ext {@link String} - the {@code FileNameExtensionFilter}
	 *        description and extension
	 * @return {@link FileNameExtensionFilter} with specified description and
	 *         extension */
	private static FileFilter createFilter(String ext) {
		return new FileNameExtensionFilter(ext, ext);
	}

	/** Creates a {@link FileFilter} for a file format that has two file
	 * extensions, such as jpg (jpeg) and tif (tiff)
	 * @param ext1 {@link String} - the {@code FileNameExtensionFilter}
	 *        description and first extension
	 * @param ext2 {@link String} - the second file extension
	 * @return {@link FileNameExtensionFilter} with specified description and
	 *         extensions */
	private static FileFilter createFilter(String ext1, String ext2) {
		return new FileNameExtensionFilter(ext1, ext1, ext2);
	}

	/** Applies a colour model to the {@link BufferedImage} if the converted
	 * file format is a bmp or jpg.
	 * @param inputImage {@link BufferedImage} to apply the colour model to
	 * @param format {@link String} to convert to
	 * @return {@link BufferedImage} with applied colour model */
	private static BufferedImage applyColorModel(BufferedImage inputImage, String format) {
		switch (format.toLowerCase()) {
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

}