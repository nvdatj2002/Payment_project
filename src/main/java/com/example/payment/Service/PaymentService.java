package com.example.payment.Service;

import com.example.payment.Entity.Bank;
import com.example.payment.Entity.Order;
import com.example.payment.Entity.ResponsePayment;
import com.example.payment.Reponsitories.BankReponsitory;
import com.example.payment.Reponsitories.OrderReponsitory;
import com.example.payment.payment.JWSPayment;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PaymentService {

    @Autowired
    BankReponsitory bankReponsitory;
    @Autowired
    OrderReponsitory orderReponsitory;
    @Autowired
    OrderService orderService;
    @Autowired
    BankService bankService;
    JWSPayment jwsPayment = new JWSPayment();

    public String createJws(int orderCode, Bank bank, double totalMoney) {

        List<Bank> banks = bankReponsitory.findAll();
        String jws = jwsPayment.createJWS(orderCode, bank, totalMoney);
        return jws;
    }

    public ResponsePayment getPayment(int orderCode, int idBank) {
        List<Bank> banksFound = bankService.getAll();

        Order o = orderService.findById(orderCode);
        Bank bank = bankService.findById(idBank);

        String tokenJWS = jwsPayment.createJWS(orderCode, bank, o.getTotalMoney());

        Map<String, Object> infoPayment = new HashMap<>();
        infoPayment.put("orderCode", orderCode);
        infoPayment.put("totalMoney", o.getTotalMoney());
        infoPayment.put("bank", bank);
        infoPayment.put("content_transfer", "TTHD" + orderCode);
        infoPayment.put("token", tokenJWS);

        ResponsePayment responsePayment = new ResponsePayment("ok", "Lấy thông tin thanh toán thành công", infoPayment);
        return responsePayment;
    }


    public Order comfirmPayemnt(String tokenJWS, int idOrder, Bank bank) {

        if (jwsPayment.verifyJWS(tokenJWS)) {

            Order o = orderReponsitory.findById(idOrder).get();
            o.setStatus(2);
            o.setBank(bank);
            o.setContentTransfer("TTHD" + idOrder);
            orderReponsitory.save(o);
            return o;
        } else {
            return null;
        }
    }

}
