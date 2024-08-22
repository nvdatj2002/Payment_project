package com.example.payment.Service;

import com.example.payment.Entity.Bank;
import com.example.payment.Reponsitories.BankReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BankService {

    @Autowired
    BankReponsitory bankReponsitory;
    public List<Bank> getAll(){
        return bankReponsitory.findAll();
    };

    public Bank findById(int id){
        return bankReponsitory.findById(id).get();
    }

    public List<Bank> getIdAndNameBank() {
        List<Bank> banks = new ArrayList<>();
        List<Bank> bankFound = bankReponsitory.findAll();

        for(Bank bank: bankFound) {
            Bank b = new Bank();
            b.setId(bank.getId());
            b.setNameBank(bank.getNameBank());
            banks.add(b);
        }
         return banks;
    }

}
