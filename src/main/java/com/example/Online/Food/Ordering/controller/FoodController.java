package com.example.Online.Food.Ordering.controller;

import com.example.Online.Food.Ordering.model.Food;
import com.example.Online.Food.Ordering.model.Restaurant;
import com.example.Online.Food.Ordering.model.User;
import com.example.Online.Food.Ordering.request.CreateFoodRequest;
import com.example.Online.Food.Ordering.response.MessageResponse;
import com.example.Online.Food.Ordering.service.FoodService;
import com.example.Online.Food.Ordering.service.RestaurantService;
import com.example.Online.Food.Ordering.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/food")
public class FoodController  {
    @Autowired
    private FoodService foodService;
    @Autowired
    private UserService userService;
    @Autowired
    private RestaurantService restaurantService;
    @GetMapping("/search")
    public ResponseEntity< List<Food>> SearchFood( @RequestParam String name, @RequestHeader("Authorization") String jwt) throws Exception {
        User user =userService.findUserByJwtToken(jwt);
        List<Food>food =foodService.searchFood(name) ;
        return new ResponseEntity<>(food, HttpStatus.CREATED) ;
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity< List<Food>> getRestaurantFood( @RequestParam boolean vegetarian, @RequestParam boolean seasonal,@RequestParam boolean nonveg, @RequestParam(required = false)String food_category, @PathVariable Long restaurantId, @RequestHeader("Authorization") String jwt) throws Exception {
        User user =userService.findUserByJwtToken(jwt);
        List<Food>food =foodService.getRestaurantFood(restaurantId,vegetarian,nonveg,seasonal,food_category);
        return new ResponseEntity<>(food, HttpStatus.OK) ;
    }



}
