package com.example.Online.Food.Ordering.service;

import com.example.Online.Food.Ordering.config.JwProvider;
import com.example.Online.Food.Ordering.model.User;
import com.example.Online.Food.Ordering.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements  UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
   private JwProvider jwtProvider ;
    @Override
    public User findUserByJwtToken(String jwt) throws Exception {
     String email=  jwtProvider.getEmailFromJwtToken(jwt);
     User user= findUserByEmail(email);
     return user ;
    }

    @Override
    public User findUserByEmail(String email) throws Exception {

      User user = userRepository.findByEmail(email) ;
       if(user==null){
           throw new Exception("user not found");
       }
        return  user;
    }
}
