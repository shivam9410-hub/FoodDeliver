package com.example.Online.Food.Ordering.model;

import com.example.Online.Food.Ordering.dto.RestaurantDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
     private Long id;

    private String fullName;
    private String email;
    private String password;
    private USER_ROLE role;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
 private List<Order>orders = new ArrayList<>();
@ElementCollection
    private List<RestaurantDto>favourites= new ArrayList<>() ;
 @OneToMany(cascade = CascadeType.ALL)
    private List<Address>addresses= new ArrayList<>();

}
