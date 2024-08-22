package com.example.payment.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestPayment {
    private int orderCode;
    private Bank bank;
    private String token;
}
