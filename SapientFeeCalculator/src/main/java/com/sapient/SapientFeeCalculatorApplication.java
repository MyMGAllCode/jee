package com.sapient;

import java.io.File;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.ApplicationContext;

import com.sapient.entity.SummaryReport;
import com.sapient.exception.SapientException;
import com.sapient.service.FeeCalculatorService;
@SpringBootApplication
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class SapientFeeCalculatorApplication {
	
	
	public static void main(String[] args) {
	ApplicationContext ctx=	SpringApplication.run(SapientFeeCalculatorApplication.class, args);
	
	FeeCalculatorService  feeCalculatorService= ctx.getBean(FeeCalculatorService.class);
	try {
		//File file=new File("E:\\MGSTS\\SapientFeeCalculator\\SapientData.xlsx");
		File file=new File("webapp\\exceldata\\SapientData.xlsx");
		List<SummaryReport> report=feeCalculatorService.calculateProcessingFee(file);
		System.out.println("report is:n");
		for(SummaryReport sr:report)
		System.out.println(sr.toString());
	} catch (SapientException e) {
		System.out.println("Error Code:"+e.getErrorCode()+",Error Message:"+e.getErrorMessage());
		
	}
	}
}
