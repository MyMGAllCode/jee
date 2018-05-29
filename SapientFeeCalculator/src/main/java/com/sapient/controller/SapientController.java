package com.sapient.controller;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.sapient.entity.SummaryReport;
import com.sapient.exception.ErrorCodes;
import com.sapient.exception.SapientException;
import com.sapient.service.FeeCalculatorService;


@RestController
public class SapientController {
	private static Logger logger = LoggerFactory.getLogger(SapientController.class);
	@Autowired
	FeeCalculatorService feeCalculatorService;
	@RequestMapping(value="/")
	public ModelAndView homePage(){
		return new ModelAndView("/sapientscript/build/index.html");
	}
	@RequestMapping(value="uploadTxnFile" ,method=RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE )
	public @ResponseBody List<SummaryReport> uploadTxnFile(@RequestParam("file") MultipartFile uploadFile)throws SapientException{
		logger.info("uploadTxnFile()::In"+uploadFile);
		
		List<SummaryReport> summaryReport=null;
		try{
		summaryReport=feeCalculatorService.calculateProcessingFee(new ByteArrayInputStream(uploadFile.getBytes()));
		}catch(SapientException se){
			throw new SapientException(se.getErrorCode(),se.getErrorMessage());
		}catch(Exception e){
			throw new SapientException(ErrorCodes.FILE_READING_ERROR,e.getMessage());
		}
		return summaryReport;
	}

}
