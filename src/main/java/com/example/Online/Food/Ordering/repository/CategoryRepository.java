package com.example.Online.Food.Ordering.repository;

import com.example.Online.Food.Ordering.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public  interface CategoryRepository  extends JpaRepository<Category,Long> {
    public List<Category>findByRestaurantId(Long id);

}
