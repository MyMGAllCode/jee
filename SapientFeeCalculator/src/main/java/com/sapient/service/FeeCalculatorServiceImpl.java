package com.sapient.service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.sapient.entity.SummaryReport;
import com.sapient.entity.Transactions;
import com.sapient.exception.ErrorCodes;
import com.sapient.exception.SapientException;
import com.sapient.filereader.SapientFileReader;

@Component
public class FeeCalculatorServiceImpl implements FeeCalculatorService {
	private static Logger logger = LoggerFactory.getLogger(FeeCalculatorServiceImpl.class);
	List<String> addedTtxnIdList = new ArrayList<String>();
	@Autowired
	SapientFileReader sapientFileReader;

	@Override
	public List<SummaryReport> calculateProcessingFee(Object fileObject ) throws SapientException {
		logger.info("calculateProcessingFee()::In");
		List<SummaryReport> summaryReportList = new ArrayList<SummaryReport>();
		addedTtxnIdList.clear();
		List<Transactions> txnList=null;
		try {
			if(fileObject.getClass()==File.class){
				txnList = sapientFileReader.readFile((File)fileObject);	
			}else{
				txnList=sapientFileReader.readFileFromBrowserUploaded((InputStream)fileObject);	
			}
			
			// Getting List of Required Params
			List<String> clientIds = txnList.stream().map(Transactions::getClientId).distinct()
					.collect(Collectors.toList());
			logger.info("clientIds:" + clientIds);
			List<String> securityIds = txnList.stream().map(Transactions::getSecurityId).distinct()
					.collect(Collectors.toList());
			logger.info("securityIds:" + securityIds);

			List<Date> transactionDates = txnList.stream().map(Transactions::getTransactionDate).distinct()
					.collect(Collectors.toList());
			logger.info("transactionDates:" + transactionDates);
			// Adding intraday transaction report
			summaryReportList=reportOnSameDateByClientIdAndSID(txnList, clientIds, securityIds, transactionDates);
			// Adding Normal Transactions report
			for (Transactions txn : txnList) {
				SummaryReport report = new SummaryReport();
				if (!addedTtxnIdList.contains(txn.getExternalTransactionId())) {
					if ("Y".equals(txn.getPriorityFlag())) {// checking market
															// value
						report.setClientId(txn.getClientId());
						report.setTransactionType(txn.getTransactionType());
						report.setTransactionDate(txn.getTransactionDate());
						report.setPrority(txn.getPriorityFlag());
						report.setProcessingFee(500.00);
					} else if ("N".equals(txn.getPriorityFlag()) && "SELL".equals(txn.getTransactionType())
							|| "WITHDRAW".equals(txn.getTransactionType())) {
						report.setClientId(txn.getClientId());
						report.setTransactionType(txn.getTransactionType());
						report.setTransactionDate(txn.getTransactionDate());
						report.setPrority("N");
						report.setProcessingFee(100.00);
					} else {
						report.setClientId(txn.getClientId());
						report.setTransactionType(txn.getTransactionType());
						report.setTransactionDate(txn.getTransactionDate());
						report.setPrority("N");
						report.setProcessingFee(50.00);

					}
					addedTtxnIdList.add(txn.getExternalTransactionId());
					summaryReportList.add(report);
				}
			}
			logger.info("summaryReportList size"+summaryReportList.size());
			return summaryReportList;
		} catch (SapientException se) {
			throw new SapientException(se.getErrorCode(),se.getErrorMessage());
		}
		catch (Exception e) {
			throw new SapientException(ErrorCodes.INTERNAL_ERROR,e.getMessage());
		}

	}
/**
 * This Method add the report for same date for same clientId and securityId
 */
	@Override
	public List<SummaryReport> reportOnSameDateByClientIdAndSID(List<Transactions> txnList,List<String> clientIds,List<String>  securityIds,List<Date>  transactionDates) throws SapientException {
		logger.info("reportOnSameDateByClientIdAndSID()::In");
		List<SummaryReport> summaryReportList=new ArrayList<SummaryReport>();
		addedTtxnIdList.clear();
		try{for (String clientId : clientIds) {// clientId
			for (String securityId : securityIds) {// securityIds
				for (Date transactionDate : transactionDates) {// transactionDates
					List<Transactions> sameDayTransactionList = txnList.stream()
							.filter((txn) -> clientId.equals(txn.getClientId())
									&& securityId.equals(txn.getSecurityId())
									&& transactionDate.equals(txn.getTransactionDate()))
							.collect(Collectors.toList());					
					if (sameDayTransactionList.size() > 1)// Checking more than one transaction  for same client and date
															
					{ // checking atleast one BUY and one SELL
						if ((sameDayTransactionList.stream().filter((txn) -> "BUY".equals(txn.getTransactionType()))
								.collect(Collectors.toList()).size() > 0)
								&& (sameDayTransactionList.stream()
										.filter((txn) -> "SELL".equals(txn.getTransactionType()))
										.collect(Collectors.toList()).size() > 0)) {
							for (Transactions txn : sameDayTransactionList) {
								SummaryReport report = new SummaryReport();
								if (!addedTtxnIdList.contains(txn.getExternalTransactionId())) {
									if ("Y".equals(txn.getPriorityFlag())) {// checking market value
																			
										report.setClientId(txn.getClientId());
										report.setTransactionType(txn.getTransactionType());
										report.setTransactionDate(txn.getTransactionDate());
										report.setPrority(txn.getPriorityFlag());
										report.setProcessingFee(500.00);
									} else {
										report.setClientId(txn.getClientId());
										report.setTransactionType(txn.getTransactionType());
										report.setTransactionDate(txn.getTransactionDate());
										report.setPrority(txn.getPriorityFlag());
										report.setProcessingFee(10.00);
									} // End else
									if (!StringUtils.isEmpty(report)) {
										addedTtxnIdList.add(txn.getExternalTransactionId());
										summaryReportList.add(report);
									} // if
								}
							} // end For

						} // End If
					} // End If

				} // End for
			} // End For
		} // End For
			 
		}catch (Exception e) {
			throw new SapientException(ErrorCodes.INTERNAL_ERROR,e.getMessage());
		}
		return summaryReportList;
	}
@Override
public File convertMultipartFileToFile(MultipartFile uploadedFile) throws SapientException {
	logger.info("convertMultipartFileToFile()::In");
	File convFile =null;
	try {
		convFile= new File( uploadedFile.getOriginalFilename());
		uploadedFile.transferTo(convFile);
	} catch (IllegalStateException | IOException e) {
		throw new SapientException(ErrorCodes.INTERNAL_ERROR,e.getMessage());
	}catch (Exception  e) {
		throw new SapientException(ErrorCodes.FILE_READING_ERROR,e.getMessage());
	}
    return convFile;	
}

}
