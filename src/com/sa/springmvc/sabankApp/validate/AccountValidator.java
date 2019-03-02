package com.sa.springmvc.sabankApp.validate;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.sa.springmvc.sabankApp.model.Account;

public class AccountValidator implements Validator{

	@Override
	public boolean supports(Class clazz) {
		return Account.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "accountNo", "error.accountNo", "Account Number is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "withdawalAmount", "error.withdawalAmount", "Withdrawal amount is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "accountType", "error.accountType", "Select Account Type.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "psCode", "error.accountNo", "PS Code is required");
		
	}

}
