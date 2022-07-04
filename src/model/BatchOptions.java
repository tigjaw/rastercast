package model;

public class BatchOptions {
	private String newPath;
	private String newName;
	private String newFormat;

	public BatchOptions() {
		this("", "", "");
	}

	public BatchOptions(String newPath, String newName, String newFormat) {
		this.newPath = newPath;
		this.newName = newName;
		this.newFormat = newFormat;
	}

	// GETTERS & SETTERS

	public String getNewPath() {
		return newPath;
	}

	public void setNewPath(String newPath) {
		this.newPath = newPath;
	}

	public String getNewName() {
		return newName;
	}

	public void setNewName(String newName) {
		this.newName = newName;
	}

	public String getNewFormat() {
		return newFormat;
	}

	public void setNewFormat(String newFormat) {
		this.newFormat = newFormat;
	}

}