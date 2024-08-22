package com.example.payment.Controller;


import com.example.payment.Entity.Bank;
import com.example.payment.Entity.Order;
import com.example.payment.Entity.RequestPayment;
import com.example.payment.Entity.ResponsePayment;
import com.example.payment.Service.BankService;
import com.example.payment.Service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
@CrossOrigin
@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    BankService bankService;
    @Autowired
    PaymentService paymentService;

    @GetMapping("")
    public ResponseEntity<ResponsePayment> payment(@RequestParam("idO") int idOrder, @RequestParam("idB")
    Optional<Integer> idBank) {

        idBank.orElse(1);

        ResponsePayment responsePayment = paymentService.getPayment(idOrder,idBank.get());
        return ResponseEntity.status(HttpStatus.OK).body(responsePayment);
    }

    @PostMapping("/confirm")
    public ResponseEntity<ResponsePayment> comfirmPayment(@RequestBody RequestPayment requestPayment) {
        Order order = paymentService.comfirmPayemnt(requestPayment.getToken(), requestPayment.getOrderCode(), requestPayment.getBank());
        if (order != null) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponsePayment("ok", "Thanh toán thành công", order));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponsePayment("ok", "Thanh toán thất bại", ""));

        }
    }
}
