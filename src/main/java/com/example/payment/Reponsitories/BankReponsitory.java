package com.example.payment.Reponsitories;

import com.example.payment.Entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankReponsitory extends JpaRepository<Bank, Integer> {

}
