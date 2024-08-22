package com.example.payment.Controller;

import com.example.payment.Entity.Order;
import com.example.payment.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@CrossOrigin
public class OrderController {
    @Autowired
    OrderService orderService;
    @GetMapping("")
    public List<Order> orders () {
        List<Order> orderList = orderService.findAll();
        return orderList;
    }


}
