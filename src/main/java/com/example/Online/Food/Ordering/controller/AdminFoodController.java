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
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/food")
public class AdminFoodController {
         @Autowired
    private FoodService foodService;
         @Autowired
    private UserService userService;
         @Autowired
    private RestaurantService restaurantService;
          @PostMapping
         public ResponseEntity<Food>createFood(@RequestBody CreateFoodRequest req, @RequestHeader("Authorization") String jwt) throws Exception {
             User user =userService.findUserByJwtToken(jwt);
             Restaurant restaurant =restaurantService.findRestaurantById(req.getRestaurantId());

             Food food = foodService.createFood(req,req.getCategory(),restaurant);

             return new ResponseEntity<>(food, HttpStatus.CREATED);

         }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse>deleteFood(@PathVariable Long id , @RequestHeader("Authorization") String jwt) throws Exception {
        User user =userService.findUserByJwtToken(jwt);
        Restaurant restaurant =restaurantService.findRestaurantById(req.getRestaurantId());
            foodService.deleteFood(id);
            MessageResponse res= new MessageResponse() ;
            res.setMessage("food deleted successfully");
        return new ResponseEntity<>(res, HttpStatus.CREATED);

    }
    @PutMapping("/{id}")
    public ResponseEntity<Food>updateFoodAvailabilityStatus(@PathVariable Long id , @RequestHeader("Authorization") String jwt) throws Exception {
        User user =userService.findUserByJwtToken(jwt);
        Food food = foodService.updateAvailabilityStatus(id) ;
        return new ResponseEntity<>(food, HttpStatus.CREATED) ;

          }


}
