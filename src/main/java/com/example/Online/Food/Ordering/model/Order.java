package com.example.Online.Food.Ordering.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    private User customer ;
    @JsonIgnore
    @ManyToOne
    private Restaurant restaurant;
 private Long totalAmount ;
 private String orderStatus ;
 private Date createdAt;
 private Address deliveryAddress;
}
