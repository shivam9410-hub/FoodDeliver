package com.example.Online.Food.Ordering.service;

import com.example.Online.Food.Ordering.dto.RestaurantDto;
import com.example.Online.Food.Ordering.model.Address;
import com.example.Online.Food.Ordering.model.Restaurant;
import com.example.Online.Food.Ordering.model.User;
import com.example.Online.Food.Ordering.repository.AddressRespository;
import com.example.Online.Food.Ordering.repository.RestaurantRepository;
import com.example.Online.Food.Ordering.repository.UserRepository;
import com.example.Online.Food.Ordering.request.CreateRestaurantRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantServiceImpl implements  RestaurantService {
     @Autowired
  private RestaurantRepository restaurantRepository;
  @Autowired
  private AddressRespository addressRespository;
@Autowired
private UserRepository userRepository;
   @Autowired
   private UserService userService;

    @Override
    public Restaurant createRestaurant(CreateRestaurantRequest req, User user) {
        Address address= addressRespository.save(req.getAddress());
        Restaurant restaurant= new Restaurant() ;
        restaurant.setAddress(address);
        restaurant.setContactInformation(req.getContactInformation());
        restaurant.setCuisineType(req.getCuisineType());
        restaurant.setDescription(req.getDescription());
        restaurant.setImages(req.getImages());
        restaurant.setName(req.getName());
        restaurant.setOpeningHours(req.getOpeningHours());
        restaurant.setRegistrationDate(LocalDateTime.now());
        restaurant.setOwner(user);
        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant updateRestaurant(long restaurantId, CreateRestaurantRequest updateRestaurant) throws Exception {
      Restaurant restaurant = findRestaurantById(restaurantId) ;

      if(restaurant.getCuisineType()!=null){
          restaurant.setCuisineType(updateRestaurant.getCuisineType());
      }
      if(restaurant.getDescription()!=null){
          restaurant.setDescription(updateRestaurant.getDescription());
      }
      if(restaurant.getName()!=null){
          restaurant.setName(updateRestaurant.getName());
      }
      return restaurantRepository.save(restaurant);
    }

    @Override
    public void deleteRestaurant(long restaurantId) throws Exception {

        Restaurant restaurant = findRestaurantById(restaurantId);

        restaurantRepository.delete(restaurant);

    }

    @Override
    public List<Restaurant> getAllRestaurant() {
        return restaurantRepository.findAll();
    }

    @Override
    public List<Restaurant> searchRestaurant(String keyword) {
        return restaurantRepository.findBySearchQuery(keyword);
    }

    @Override
    public Restaurant findRestaurantById(Long id) throws Exception {
          Optional<Restaurant> opt= restaurantRepository.findById(id) ;
          if(opt.isEmpty()){
              throw new Exception("restaurant not found with id"+id) ;
          }
        return opt.get();
    }

    @Override
    public Restaurant getRestaurantByUserId(long userId) throws Exception {
      Restaurant restaurant = restaurantRepository.findByOwnerId(userId);
      if(restaurant==null)
          throw new Exception("restaurant not found with owner id"+userId);

        return restaurant;
    }

    @Override
    public RestaurantDto addToFavorites(Long restaurantId, User user) throws Exception {
        Restaurant restaurant = findRestaurantById(restaurantId);
        RestaurantDto dto = new RestaurantDto();
        dto.setDescription(restaurant.getDescription());
        dto.setImages(restaurant.getImages());
        dto.setTitle(restaurant.getName());
        dto.setId(restaurantId);

        boolean isFavorited =false ;
        List<RestaurantDto> favorites = user.getFavourites();
        for(RestaurantDto favorite: favorites){
            if(favorite.getId().equals(restaurantId)){
                isFavorited=true;
                break;
            }
        }
        if(isFavorited){
            favorites.removeIf(favorite ->favorite.getId().equals(restaurantId));

        }

          userRepository.save(user);
       return dto;
    }

    @Override
    public Restaurant updateRestaurantStatus(long id) throws Exception {
        Restaurant restaurant=  findRestaurantById(id);
        restaurant.setOpen(!restaurant.isOpen());

        return restaurantRepository.save(restaurant);
    }
}
