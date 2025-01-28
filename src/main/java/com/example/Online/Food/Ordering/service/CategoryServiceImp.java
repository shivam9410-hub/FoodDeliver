package com.example.Online.Food.Ordering.service;

import com.example.Online.Food.Ordering.model.Category;
import com.example.Online.Food.Ordering.model.Restaurant;
import com.example.Online.Food.Ordering.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImp implements CategoryService {
    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public Category createCategory(String name, Long userId) throws Exception {
       Restaurant restaurant = restaurantService.findRestaurantById(userId) ;
          Category category = new Category();
          category.setName(name);
          category.setRestaurant(restaurant);
          return categoryRepository.save(category) ;

    }

    @Override
    public List<Category> findCategoryByRestaurantId(Long id) throws Exception {
        return categoryRepository.findByRestaurantId(id);
    }

    @Override
    public Category findCategoryById(Long id) throws Exception {
      Optional<Category> optionalCategory= categoryRepository.findById(id);
      if(optionalCategory.isEmpty()){
          throw new Exception("Category not found");
      }

        return optionalCategory.get();
    }
}
