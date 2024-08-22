package com.example.payment.Service;

import com.example.payment.Entity.Account;
import com.example.payment.Reponsitories.AccountReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {
    @Autowired
    AccountReponsitory accountReponsitory;

//    public Account findByUsername(String username) {
//        return accountReponsitory.findByUsername();
//    }

    public List<Account> findAll() {
        List<Account> listAccount = accountReponsitory.findAll();
        if(listAccount.size() == 0){
            System.out.println("o");
            return null;
        }else{
            listAccount.stream().forEach(System.out::println);
            return listAccount;
        }

    }
}
