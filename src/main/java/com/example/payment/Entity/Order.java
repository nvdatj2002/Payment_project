package com.example.payment.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tblorder")
public class Order {

    @Id
    private int id;

    @Column(name = "name_product")
    private String productName;

    @Column(name = "total_money")
    private double totalMoney;

    @JoinColumn(name = "id_bank")
    @ManyToOne
    private Bank bank;

    private int status;

    @Column(name = "content_transfer")
    private String contentTransfer;
}
