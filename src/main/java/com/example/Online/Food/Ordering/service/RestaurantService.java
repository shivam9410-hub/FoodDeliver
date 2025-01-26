package com.example.Online.Food.Ordering.service;

import com.example.Online.Food.Ordering.dto.RestaurantDto;
import com.example.Online.Food.Ordering.model.Restaurant;
import com.example.Online.Food.Ordering.model.User;
import com.example.Online.Food.Ordering.request.CreateRestaurantRequest;
import jdk.jshell.spi.ExecutionControlProvider;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface RestaurantService {
    public Restaurant createRestaurant(CreateRestaurantRequest req, User user);
  public Restaurant updateRestaurant (long restaurantId, CreateRestaurantRequest updateRestaurant) throws Exception;
  public void deleteRestaurant(long restaurantId) throws Exception;
  public List<Restaurant>getAllRestaurant();
  public List<Restaurant> searchRestaurant(String keyword) ;
  public Restaurant findRestaurantById(Long id) throws Exception;
  public Restaurant getRestaurantByUserId(long userId) throws Exception;
public RestaurantDto addToFavorites(Long restaurantId, User user) throws Exception;
  public Restaurant updateRestaurantStatus(long id) throws Exception;

}
