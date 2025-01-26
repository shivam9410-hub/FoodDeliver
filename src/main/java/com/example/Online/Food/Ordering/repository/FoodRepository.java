package com.example.Online.Food.Ordering.repository;

import com.example.Online.Food.Ordering.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FoodRepository extends JpaRepository<Food,Long> {


List<Food> findByRestaurant(Long restaurantId) ;
@Query("select f From Food f where f.name like %:keyword% or f.foodcategory.name like %:keyword%")
List<Food> searchFood(@Param("keyword")String keyword);

}
