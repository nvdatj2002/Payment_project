package com.example.payment.Controller;

import com.example.payment.Entity.Account;
import com.example.payment.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping("")
    public List<Account> findAll() {
        List<Account> listAccount = accountService.findAll();
        if(listAccount.size() == 0){
            System.out.println("o");
            return null;
        }else{
            listAccount.stream().forEach(System.out::println);
            Account account = new Account(2,"long","123","123");
            listAccount.add(account);
            return listAccount;
        }


    }

    @GetMapping("/getAll")
    public Account getAll() {
        Account accounts = new Account(1,"dat","uss","123");

        return accounts;
    }

}
