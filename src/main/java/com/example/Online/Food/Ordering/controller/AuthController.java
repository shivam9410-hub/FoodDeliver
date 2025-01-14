package com.example.Online.Food.Ordering.controller;


import com.example.Online.Food.Ordering.model.Cart;
import com.example.Online.Food.Ordering.model.User;
import com.example.Online.Food.Ordering.repository.CartRepository;
import com.example.Online.Food.Ordering.repository.UserRepository;
import com.example.Online.Food.Ordering.request.LoginRequest;
import com.example.Online.Food.Ordering.response.AuthResponse;
import com.example.Online.Food.Ordering.service.CustomerUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private CustomerUserDetailsService customerUserDetailsService;
     @Autowired
    private CartRepository cartRepository;
    @PostMapping("/signup")
     public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user) throws Exception {
      User isEmailExist= userRepository.findByEmail(user.getEmail());
       if(isEmailExist!=null){
           throw new Exception("Email is already used with another account");
       }

       User createdUser = new User() ;
       createdUser.setEmail(user.getEmail());
       createdUser.setPassword(passwordEncoder.encode(user.getPassword()));
       createdUser.setFullName(user.getFullName());
       createdUser.setRole((user.getRole()));
       User savedUser = userRepository.save(createdUser);
         Cart cart = new Cart() ;
         cart.setCustomer(savedUser);
         cartRepository.save(cart);
         Authentication authentication =new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword());
         SecurityContextHolder.getContext().setAuthentication(authentication);
         String jwt = jwtProvider.generateToken(authentication);
         AuthResponse authResponse= new AuthResponse();
         authResponse.setJwt(jwt);
         authResponse.setMessage("Register Success");
         authResponse.setRole(savedUser.getRole());
         return new ResponseEntity<>(authResponse, HttpStatus.CREATED);

     }


     public ResponseEntity<AuthResponse>signInHandler(@RequestBody LoginRequest req) throws Exception {

        String username = req.getEmail();
        String password = req.getPassword();
        Authentication authentication = authenticate(username,password);

       String jwt = jwtProvider.generateToken(authentication);
       AuthResponse authResponse= new AuthResponse();
       authResponse.setJwt(jwt);
       authResponse.setMessage("Login Success");
       Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

       String role= authorities.isEmpty()?null:authorities.iterator().next().getAuthority();

       return new ResponseEntity<>(authResponse, HttpStatus.OK);


    return null;
    }
    private Authentication authenticate(String username, String password) {
        UserDetails userDetails = customerUserDetailsService.loadUserByUsername(username);
        if(userDetails ==null)
        {
            throw new BadCredentialsException("Invalid username ");
        }
        if(!passwordEncoder.matches(password,userDetails.getPassword())){
             throw new BadCredentialsException("Invalid password");
        }
        return  new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());

    }


}
