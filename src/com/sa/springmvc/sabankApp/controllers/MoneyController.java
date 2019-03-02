package com.sa.springmvc.sabankApp.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sa.springmvc.sabankApp.model.Account;
import com.sa.springmvc.sabankApp.services.AccountService;

public class MoneyController {
	
	@Autowired
	AccountService accountService;
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor ste = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, ste);
	}
	@RequestMapping("/")
	public String showHomePage()
	{
		return "index";
	}
	
	/*@RequestMapping("/new")
	public String newAccount() {
		return "newAccount";
	}*/
	@RequestMapping("/new")
	public String showAccount(Model model) {
		model.addAttribute("account", new Account());
		return "newAccount";
	}
	/*@RequestMapping("/showAccount")
	public String showAccount() {
		return "showAccount";
	}*/
	
	@RequestMapping(value="/saveAccount", method=RequestMethod.POST )
	public String saveAccount(@Valid @ModelAttribute("account") Account account,
			BindingResult result, Model model
			/*@RequestParam("accountNo")String accountNo, 
			@RequestParam("accountHolderName")String accountHolderName,
			@RequestParam("balance")String balance*/
			) {
		/*String accountNo = req.getParameter("accountNo");
		String accountHolderName = req.getParameter("accountHolderName");
		String balance = req.getParameter("balance");*/
		/*model.addAttribute("accountNo", accountNo);
		model.addAttribute("accountHolderName", accountHolderName);
		model.addAttribute("balance", balance);*/
		/*Account account = new Account();
		account.setAccountNo(Integer.parseInt(accountNo));
		account.setAccountHolderName(accountHolderName);
		account.setBalance(Integer.parseInt(balance));*/
		/*model.addAttribute("account", account);
		return "showAccount";*/
		/*if(result.hasErrors()) {
			return "newAccount";
		}else {
			accountService.saveAccount(account);
			return "redirect:/list";
		}*/
		if(result.hasErrors()) {
			return "newAccount";
		}else {
			String message = "";
			boolean flag = true;
			try {
				flag = accountService.saveAccount(account);
			}catch(Exception ex) {
				message = ex.getMessage();
				flag = false;
			}
			if(!flag) {
				model.addAttribute("message", message);
				return "newAccount";
			}
			model.addAttribute("account",account);
			return "redirect:/list";
		}
	}
	@GetMapping("/list")
	public String getAccounts(Model model) {
		List<Account> accounts = accountService.getAccounts();
		model.addAttribute("accounts",accounts);
		return "listAccount";
	}
	@GetMapping("/edit")
	public String updateAccount(@RequestParam("accountNo") int accountNo, Model model) {
		Account account = accountService.getAccount(new Integer(accountNo));
		model.addAttribute("account",account);
		return "newAccount";
	}
	@GetMapping("/delete")
	public String deleteAccount(@RequestParam("accountNo") int accountNo, Model model) {
		accountService.deleteAccount(accountNo);
		return "redirect:/list";
	}
}
