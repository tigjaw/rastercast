package controller;

import java.io.File;

import core.RasterCast;
import model.BatchOptions;

public class BatchImage {
	private File file;
	private BatchOptions batchOptions;

	public BatchImage(File file) {
		this.file = file;
		this.batchOptions = new BatchOptions();
	}

	// FILE METHODS

	public String getOldPath() {
		return RasterCast.stripExt(file.getPath(), false);
	}

	public String getOldName() {
		return RasterCast.stripExt(file.getName(), false);
	}

	public String getOldFormat() {
		return RasterCast.stripExt(file.getName(), true);
	}

	// BATCH OPTIONS

	public void changePath(String path) {
		batchOptions.setNewPath(path);
	}

	public void changeName(String name) {
		batchOptions.setNewName(name);
	}

	public void changeFormat(String format) {
		batchOptions.setNewFormat(format);
	}

	// GETTERS & SETTERS

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public BatchOptions getBatchOptions() {
		return batchOptions;
	}

	public void setBatchOptions(BatchOptions batchOptions) {
		this.batchOptions = batchOptions;
	}
}