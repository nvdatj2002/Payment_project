package com.example.payment.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity
@Table(name = "tblbank")
public class Bank {

    @Id
    private int id;

    private String name;

    @Column(name = "name_bank")
    private String nameBank;

    @Column(name = "number_bank")
    private String numberBank;

    @JsonIgnore
    @OneToMany(mappedBy = "bank")
    private List<Order> order;
}
