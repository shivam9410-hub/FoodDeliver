package com.example.Online.Food.Ordering.controller;

import com.example.Online.Food.Ordering.model.Category;
import com.example.Online.Food.Ordering.model.User;
import com.example.Online.Food.Ordering.service.CategoryService;
import com.example.Online.Food.Ordering.service.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UserService userService;

    public ResponseEntity<Category>createCategory(@RequestBody Category category, @RequestHeader("Authorization")String jwt) throws Exception {
       User user =userService.findUserByJwtToken(jwt) ;
       Category createdCategory= categoryService.createCategory(category.getName(),user.getId());
       return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }
}
