package com.example.payment.Controller;

import com.example.payment.Entity.Bank;
import com.example.payment.Service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "/api/bank")
public class BankController {
    @Autowired
    BankService bankService;
    @GetMapping("")
    public List<Bank> getBanks() {
        return bankService.getIdAndNameBank();
    }
}
