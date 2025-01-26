package com.example.Online.Food.Ordering.service;
import com.example.Online.Food.Ordering.model.User;
public interface UserService {

    public User findUserByJwtToken(String jwt) throws Exception;

    public User findUserByEmail(String email) throws  Exception;

}
