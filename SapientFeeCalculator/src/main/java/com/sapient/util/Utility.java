package com.sapient.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.sapient.entity.Transactions;
import com.sapient.exception.ErrorCodes;
import com.sapient.exception.SapientException;

public class Utility {
	@SuppressWarnings("deprecation")
	public static Transactions setTransactionList(List<String> rowData) throws ParseException {
		Transactions transactions = new Transactions();
		if (rowData != null && !CollectionUtils.isEmpty(rowData)) {
			transactions.setExternalTransactionId(rowData.get(0));
			transactions.setClientId(rowData.get(1));
			transactions.setSecurityId(rowData.get(2));
			transactions.setTransactionType(rowData.get(3));
			transactions.setTransactionDate(new SimpleDateFormat("dd-MMM-yyyy").parse(rowData.get(4)));
			transactions.setMarketValue(Double.parseDouble(rowData.get(5)));
			transactions.setPriorityFlag(rowData.get(6));
		}
		return transactions;
	}

	public static String getFileExtension(File file) {
		String extension = "";
		if (file != null && file.exists()) {
			String name = file.getName();
			extension = name.substring(name.lastIndexOf("."));
		}
		return extension;
	}
	
	public static List<Transactions> readxlsxFile(File uploadedFile) throws ParseException, SapientException{
		List<Transactions>listTransactions=new ArrayList<Transactions>();
		try{
		FileInputStream uploadedFileStream = new FileInputStream(uploadedFile);
		Workbook workbook = new XSSFWorkbook(uploadedFileStream);
		Sheet sheet = workbook.getSheetAt(0);// Getting Excel Sheet Data
		Iterator<Row> iterator = sheet.iterator();
		int temp = 0;
		while (iterator.hasNext()) {

			Transactions transactions = null;
			Row row = iterator.next();
			if (temp == 0) {
				temp++;
				continue;
			}
			Iterator<Cell> cellIterator = row.iterator();

			List<String> rowData = new ArrayList<String>();
			while (cellIterator.hasNext()) {
				Cell currentCell = cellIterator.next();// Get each cell
														// value of Row
				rowData.add(currentCell.toString());
				}
			transactions = Utility.setTransactionList(rowData);
			if (transactions != null)
				listTransactions.add(transactions);
			// logger.info("Transactions:"+transactions.toString());
		}
	} catch (FileNotFoundException fnex) {
		throw new SapientException(ErrorCodes.FILE_NOT_FOUND, fnex.getMessage());
	}

	catch (Exception ex) {
		throw new SapientException(ErrorCodes.FILE_READING_ERROR, ex.getMessage());
	}
	return listTransactions;
}
	public static List<Transactions> readxlsxFileUploadedFromBrowser(InputStream inputStream) throws ParseException, SapientException{
		List<Transactions>listTransactions=new ArrayList<Transactions>();
		try{
		Workbook workbook = new XSSFWorkbook((InputStream)inputStream);
		Sheet sheet = workbook.getSheetAt(0);// Getting Excel Sheet Data
		Iterator<Row> iterator = sheet.iterator();
		int temp = 0;
		while (iterator.hasNext()) {

			Transactions transactions = null;
			Row row = iterator.next();
			if (temp == 0) {
				temp++;
				continue;
			}
			Iterator<Cell> cellIterator = row.iterator();

			List<String> rowData = new ArrayList<String>();
			while (cellIterator.hasNext()) {
				Cell currentCell = cellIterator.next();// Get each cell
														// value of Row
				rowData.add(currentCell.toString());
				}
			transactions = Utility.setTransactionList(rowData);
			if (transactions != null)
				listTransactions.add(transactions);
			// logger.info("Transactions:"+transactions.toString());
		}
	} catch (FileNotFoundException fnex) {
		throw new SapientException(ErrorCodes.FILE_NOT_FOUND, fnex.getMessage());
	}

	catch (Exception ex) {
		throw new SapientException(ErrorCodes.FILE_READING_ERROR, ex.getMessage());
	}
	return listTransactions;
}

	/**
	 * XML Reader
	 * @param file
	 * @return
	 */
	public static List<Transactions> readXmlFile(File file){
		try {

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(file);
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("transaction");
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;

				
					System.out.println("externalTransactionId : " + eElement.getElementsByTagName("externalTransactionId").item(0).getTextContent());
					System.out.println("clientId : " + eElement.getElementsByTagName("clientId").item(0).getTextContent());
					System.out.println("securityId : " + eElement.getElementsByTagName("securityId").item(0).getTextContent());
					System.out.println("transactionType: " + eElement.getElementsByTagName("transactionType").item(0).getTextContent());
					System.out.println("transactionDate : " + eElement.getElementsByTagName("transactionDate").item(0).getTextContent());
					System.out.println("marketValue : " + eElement.getElementsByTagName("marketValue").item(0).getTextContent());
					System.out.println("priorityFlag: " + eElement.getElementsByTagName("priorityFlag").item(0).getTextContent());

				}
			}
		    } catch (Exception e) {
			e.printStackTrace();
		    }
		  
		return null;
		
	}

}
