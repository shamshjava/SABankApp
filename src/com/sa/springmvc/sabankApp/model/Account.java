package com.sa.springmvc.sabankApp.model;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.sa.springmvc.sabankApp.validations.PSCode;

public class Account {
	
	@NotNull(message="Account # can't be blank")
	private Integer accountNo;
	@NotNull(message="Account Holder Name can't be blank")
	@Size(min=2,max=50,message="Invalid Length for Account Holder Name")
	@Pattern(regexp="[A-Za-z(\\s)]+",message="Invalid Account Holder Name")
	private String accountHolderName;
	@NotNull(message="Balance amount is required")
	@Min(value=5000,message="Minimum Balance must not be less than 5000")
	private Integer balance;
	@NotNull(message="Select Account Type")
	private String accountType;
	@DateTimeFormat(pattern="MM/dd/yyyy")
	@NotNull(message="Date of Birth can't be null")
	@Past(message="Account cant be created for a person not born")
	private Date dateOfBirth;
	@NotNull(message="PS Code is required")
	@PSCode(value="PSUSA",message="Security Code should start with PSUSA")
	private String psCode;
	@NotNull(message="Withdrawal amount is required")
	private Integer withdawalAmount;
	@NotNull(message="Depositable amount is required")
	private Integer depositableAmount;
	public Integer getWithdawalAmount() {
		return withdawalAmount;
	}
	public void setWithdawalAmount(Integer withdawalAmount) {
		this.withdawalAmount = withdawalAmount;
	}
	public Integer getDepositableAmount() {
		return depositableAmount;
	}
	public void setDepositableAmount(Integer depositableAmount) {
		this.depositableAmount = depositableAmount;
	}
	public Account() {
		this.accountNo = 0;
		this.accountHolderName = "";
		this.balance = 0;
		this.accountType = "";
		this.dateOfBirth = new Date();
		this.psCode = "";
		this.withdawalAmount = 0;
		this.depositableAmount = 0;
	}
	public Account(Integer accountNo, String accountHolderName, Integer balance, String accountType, Date dateOfBirth, String psCode, Integer withdawalAmount, Integer depositableAmount) {
		this.accountNo = accountNo;
		this.accountHolderName = accountHolderName;
		this.balance = balance;
		this.accountType = accountType;
		this.dateOfBirth = dateOfBirth;
		this.psCode = psCode;
		this.withdawalAmount = withdawalAmount;
		this.depositableAmount = depositableAmount;
	}
	public Integer getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(Integer accountNo) {
		this.accountNo = accountNo;
	}
	public String getAccountHolderName() {
		return accountHolderName;
	}
	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}
	public Integer getBalance() {
		return balance;
	}
	public void setBalance(Integer balance) {
		this.balance = balance;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getPsCode() {
		return psCode;
	}
	public void setPsCode(String psCode) {
		this.psCode = psCode;
	}
	
	

}
