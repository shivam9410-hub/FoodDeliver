package com.example.Online.Food.Ordering.controller;


import com.example.Online.Food.Ordering.model.Restaurant;
import com.example.Online.Food.Ordering.model.User;
import com.example.Online.Food.Ordering.request.CreateRestaurantRequest;
import com.example.Online.Food.Ordering.response.MessageResponse;
import com.example.Online.Food.Ordering.service.RestaurantService;
import com.example.Online.Food.Ordering.service.RestaurantServiceImpl;
import com.example.Online.Food.Ordering.service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;

@RestController
@RequestMapping("/api/admin/restaurant")
public class AdminRestaurantController {

    @Autowired
    private UserServiceImp userservice;
    @Autowired
    private RestaurantServiceImpl restaurantService;
      @PostMapping()
    public ResponseEntity<Restaurant> createRestaurant(
            @RequestBody CreateRestaurantRequest req, @RequestHeader("Authorization") String jwt
            )   throws  Exception{

         User user = userservice.findUserByJwtToken(jwt);
         Restaurant restaurant = restaurantService.createRestaurant(req,user) ;
         return new ResponseEntity<>(restaurant, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Restaurant> updateRestaurant(
            @RequestBody CreateRestaurantRequest req, @RequestHeader("Authorization") String jwt
   , @PathVariable Long id )   throws  Exception{

        User user = userservice.findUserByJwtToken(jwt);
        Restaurant restaurant = restaurantService.updateRestaurant(id,req);
        return new ResponseEntity<>(restaurant, HttpStatus.CREATED);
    }
   @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteRestaurant(
            @RequestHeader("Authorization") String jwt , @PathVariable Long id
    )   throws  Exception{

        User user = userservice.findUserByJwtToken(jwt);
       restaurantService.deleteRestaurant(id); ;
       MessageResponse res=new MessageResponse();
       res.setMessage("restaurant deleted successfully") ;
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Restaurant> updateRestaurantStatus(
           @RequestHeader("Authorization") String jwt , @PathVariable Long id
    )   throws  Exception{

        User user = userservice.findUserByJwtToken(jwt);
       Restaurant restaurant = restaurantService.updateRestaurantStatus(id);
       return new ResponseEntity<>(restaurant,HttpStatus.OK);
    }


    @GetMapping("/user")
    public ResponseEntity<Restaurant> findRestaurantByUserId(
             @RequestHeader("Authorization") String jwt , @PathVariable Long id
    )   throws  Exception{

        User user = userservice.findUserByJwtToken(jwt);
        Restaurant restaurant = restaurantService.findRestaurantById(user.getId());
        return new ResponseEntity<>(restaurant,HttpStatus.OK);
    }


}

