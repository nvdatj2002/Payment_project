package com.example.payment.Service;

import com.example.payment.Entity.Bank;
import com.example.payment.Entity.Order;
import com.example.payment.Reponsitories.OrderReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderReponsitory orderReponsitory;

    public List<Order> findAll() {

        return orderReponsitory.findAll();
    }

    public Order findById(int idOrder){
        return orderReponsitory.findById(idOrder).get();
    }



}
