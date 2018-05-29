package com.sapient;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sapient.entity.SummaryReport;
import com.sapient.entity.Transactions;
import com.sapient.exception.ErrorCodes;
import com.sapient.exception.SapientException;
import com.sapient.filereader.SapientFileReader;
import com.sapient.service.FeeCalculatorService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SapientFeeCalculatorApplicationTests {
	@Autowired
	FeeCalculatorService feeCalculatorService;
	@Autowired
	SapientFileReader sapientFileReader;

	@Test
	public void contextLoads() {
		
		
	}
	@Test
	public void feeCalculator() throws ParseException{
		//File file=new File("E:\\MGSTS\\SapientFeeCalculator\\SapientData.xlsx");
		File file=new File("webapp\\exceldata\\SapientData.xlsx");
		
		List<SummaryReport>reportList=null;
		try {
			
			reportList=feeCalculatorService.calculateProcessingFee(file);
			//Testing Report List Size
			assertThat(reportList.size()).isEqualTo(20);
			
		} catch (SapientException e) {
			System.out.println("ErrorCode:"+e.getErrorCode()+"Error Message:"+e.getErrorMessage());
		}
		
	}
	
	@Test
	public void reportforSameDateAndClientIdSID() throws ParseException{
		try{
		//Testing Txn Report size for same day Buy and Sell by same ClientId, TransactionDate
		//File uploadedFile=new File("E:\\MGSTS\\SapientFeeCalculator\\SapientData.xlsx");
		File uploadedFile=new File("webapp\\exceldata\\SapientData.xlsx");
		List<Transactions> txnList = sapientFileReader.readFile(uploadedFile);
		List<SummaryReport>reportList=null;
		List<String>clientIds=new ArrayList<String>();
		clientIds.add("AS");
		List<String>securityIds=new ArrayList<String>();
		securityIds.add("REL");
		List<Date>transactionDates=new ArrayList<Date>();
		Date date=new SimpleDateFormat("dd-MMM-yyyy").parse("06-Nov-2013");
		transactionDates.add(date);
		reportList=feeCalculatorService.reportOnSameDateByClientIdAndSID(txnList,clientIds,  securityIds, transactionDates);
		assertThat(reportList.size()).isEqualTo(2);
		}catch (SapientException e) {
			System.out.println("ErrorCode:"+e.getErrorCode()+"Error Message:"+e.getErrorMessage());
		}
		catch (Exception e) {
			System.out.println("ErrorCode:"+ErrorCodes.INTERNAL_ERROR+"Error Message:"+e.getMessage());
		}
		
	}

}
