/**
 * 
 */
package com.sapient.filereader;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.sapient.constants.Constants;
import com.sapient.entity.Transactions;
import com.sapient.exception.ErrorCodes;
import com.sapient.exception.SapientException;
import com.sapient.util.Utility;

/**
 * @author DKP
 *
 */
@Component
public class SapientFileReaderImpl implements SapientFileReader {
	private static Logger logger = LoggerFactory.getLogger(SapientFileReaderImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sapient.filereader.FileReader#readeFile(java.lang.String)
	 */
	@Override
	public List<Transactions> readFile(File uploadedFile) throws SapientException {
		logger.info("readFile()::In");
		List<Transactions> listTransactions =null;
		try {
			String fileExtension=Utility.getFileExtension(uploadedFile);
			if(StringUtils.isEmpty(fileExtension)){
			
			}else if(fileExtension.equals(Constants.XML)){
				
				//Utility.readXmlFile(uploadedFile);
			}else if(fileExtension.equals(Constants.XLSX)||fileExtension.equals(Constants.XLS)){
				listTransactions=Utility.readxlsxFile(uploadedFile);
			}else if(fileExtension.equals(Constants.TXT)){
			//readtxtFile
			}else if(fileExtension.equals(Constants.CSV)){
				listTransactions=Utility.readxlsxFile(uploadedFile);
				}
				
		}catch(SapientException ex){
			throw new SapientException(ex.getErrorCode(),ex.getErrorMessage());
			
		}catch(Exception ex){
			throw new SapientException(ErrorCodes.INTERNAL_ERROR,ex.getMessage());
			
		}
			
		return listTransactions;
	}

	@Override
	public List<Transactions> readFileFromBrowserUploaded(InputStream inputStream) throws SapientException {
		logger.info("readFileFromBrowserUploaded()::In");
		List<Transactions> listTransactions =null;
		try {
			
		listTransactions=Utility.readxlsxFileUploadedFromBrowser(inputStream);
				
		}catch(SapientException ex){
			throw new SapientException(ex.getErrorCode(),ex.getErrorMessage());
			
		}catch(Exception ex){
			throw new SapientException(ErrorCodes.INTERNAL_ERROR,ex.getMessage());
			
		}
			
		return listTransactions;
	}

}
