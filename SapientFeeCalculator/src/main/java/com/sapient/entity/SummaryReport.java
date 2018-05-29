package com.sapient.entity;

import java.io.Serializable;
import java.util.Date;
/**
 * 
 * @author DKP
 *
 */

public class SummaryReport implements Serializable{

	private static final long serialVersionUID = 1L;
	private String clientId;
	private String transactionType;
	private Date transactionDate;
	private String prority;
	private Double processingFee;
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public Date getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
	public String getPrority() {
		return prority;
	}
	public void setPrority(String prority) {
		this.prority = prority;
	}
	public Double getProcessingFee() {
		return processingFee;
	}
	public void setProcessingFee(Double processingFee) {
		this.processingFee = processingFee;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "SummaryReport [clientId=" + clientId + ", transactionType=" + transactionType + ", transactionDate="
				+ transactionDate + ", prority=" + prority + ", processingFee=" + processingFee + "]";
	}
	

}
