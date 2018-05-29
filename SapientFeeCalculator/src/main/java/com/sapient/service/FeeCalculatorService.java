package com.sapient.service;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.sapient.entity.SummaryReport;
import com.sapient.entity.Transactions;
import com.sapient.exception.SapientException;

public interface FeeCalculatorService {
	public List<SummaryReport> calculateProcessingFee(Object fileObject) throws SapientException;//Upload file from main class
	public File convertMultipartFileToFile(MultipartFile uploadedFile) throws SapientException;//Upload file from browser
	public List<SummaryReport> reportOnSameDateByClientIdAndSID(List<Transactions> txnList,List<String> clientIds,List<String>  securityIds,List<Date>  transactionDates ) throws SapientException;
	

}
