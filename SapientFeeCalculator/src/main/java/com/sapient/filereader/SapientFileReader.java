package com.sapient.filereader;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import com.sapient.entity.Transactions;
import com.sapient.exception.SapientException;
public interface SapientFileReader {
	
	public List<Transactions> readFile(File uploadedFile) throws SapientException;
	public List<Transactions> readFileFromBrowserUploaded(InputStream inputStream) throws SapientException;

}
