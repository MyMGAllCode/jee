package com.sapient.entity;

import java.io.File;
import java.io.Serializable;
/**
 * 
 * @author DKP
 *
 */

public class FileUploadRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public String toString() {
		return "FileUploadRequest [file=" + file + "]";
	}

	private File file;

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
