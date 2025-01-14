package com.example.Online.Food.Ordering.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.util.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="orders")
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
 @ManyToOne
 private Address deliveryAddress;
 @OneToMany
 private List<OrderItem>items;
 //private Payment payment;
 private int totalItem;
 private int totalPrice;

}
