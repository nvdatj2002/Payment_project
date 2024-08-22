package com.example.payment.Reponsitories;

import com.example.payment.Entity.Bank;
import com.example.payment.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderReponsitory extends JpaRepository<Order, Integer> {


}
