package com.sa.springmvc.sabankApp.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sa.springmvc.sabankApp.model.Account;
import com.sa.springmvc.sabankApp.services.AccountService;
import com.sa.springmvc.sabankApp.validate.AccountValidator;

//@RequestMapping("/account")
@Controller
public class AccountController {
	
	@Autowired
	AccountService accountService;
	@Autowired
	AccountValidator validator;
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor ste = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, ste);
	}
	@RequestMapping(value={"/","/index"})
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
	@RequestMapping("/withdraw")
	public String withdraw(Model model) {
		model.addAttribute("account", new Account());
		return "withdraw";
	}
	@RequestMapping(value="/withdrawSave", method=RequestMethod.POST)
	public String withdrawSave(@ModelAttribute("account") Account account,
			BindingResult result, Model model, final RedirectAttributes redirectAttributes) {
		validator.validate(account, result);
		if(result.hasErrors()) {
			return "withdraw";
		}else {
			String message = "Transaction successfully completed!";
			boolean flag = true;
			try {
				//flag = accountService.saveAccount(account);
				Account account1 = accountService.getAccount(new Integer(account.getAccountNo()));
				System.out.println(account.getAccountNo());
				System.out.println(account.getWithdawalAmount());
				System.out.println(account1.getBalance());
				if(account.getWithdawalAmount() <= account1.getBalance()) {
					int balance = account1.getBalance() - account.getWithdawalAmount();
					account1.setBalance(balance);
					flag = accountService.saveAccount(account1);
				}else {
					message = "Not Sufficient balance is available in your Account";
				}
			}catch(Exception ex) {
				message = ex.getMessage();
				flag = false;
			}
			if(!flag) {
				model.addAttribute("message", message);
				return "withdraw";
			}
			redirectAttributes.addFlashAttribute("message", message);
			//model.addAttribute("message", message);
			model.addAttribute("account",account);
			return "redirect:/list";
			//return "success";
		}
	}
	
	@RequestMapping("/deposit")
	public String deposit(Model model) {
		model.addAttribute("account", new Account());
		return "deposit";
	}
	@RequestMapping(value="/depositSave", method=RequestMethod.POST)
	public String depositSave(@ModelAttribute("account") Account account,
			BindingResult result, Model model, final RedirectAttributes redirectAttributes) {
		//model.addAttribute("account", new Account());
		System.out.println("account="+account.getAccountNo());
		System.out.println("resut="+result);
		validator.validate(account, result);
		if(result.hasErrors()) {
			return "deposit";
		}else {
			String message = "Transaction successfully completed!";
			boolean flag = true;
			try {
				Account account1 = accountService.getAccount(new Integer(account.getAccountNo()));
					int balance = account1.getBalance() + account.getDepositableAmount();
					account1.setBalance(balance);
					flag = accountService.saveAccount(account1);
			}catch(Exception ex) {
				message = ex.getMessage();
				flag = false;
			}
			if(!flag) {
				model.addAttribute("message", message);
				return "deposit";
			}
			model.addAttribute("account",account);
			redirectAttributes.addFlashAttribute("message", message);
			return "redirect:/list";
		}
	}
	/*@RequestMapping("/showAccount")
	public String showAccount() {
		return "showAccount";
	}*/
	
	@RequestMapping(value="/saveAccount", method=RequestMethod.POST )
	public String saveAccount(@Valid @ModelAttribute("account") Account account,
			BindingResult result, Model model, final RedirectAttributes redirectAttributes
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
			String message = "Account successfully created/updated!";
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
			redirectAttributes.addFlashAttribute("message", message);
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
	public String deleteAccount(@RequestParam("accountNo") int accountNo, Model model, final RedirectAttributes redirectAttributes) {
		accountService.deleteAccount(accountNo);
		String message = "Account Successfully deleted!";
		redirectAttributes.addFlashAttribute("message", message);
		return "redirect:/list";
	}
}
