package com.stackroute.FoodieService.service;

import com.stackroute.FoodieService.DTODomain.CartDTO;
import com.stackroute.FoodieService.DTODomain.FavouriteDTO;
import com.stackroute.FoodieService.config.CartProducer;
import com.stackroute.FoodieService.config.FavProducer;
import com.stackroute.FoodieService.domain.Cusine;

import com.stackroute.FoodieService.domain.Restaurant;
import com.stackroute.FoodieService.domain.User;
import com.stackroute.FoodieService.exception.*;
import com.stackroute.FoodieService.proxy.UserAuthProxy;
import com.stackroute.FoodieService.repository.CusineRepository;
import com.stackroute.FoodieService.repository.RestaurantRepository;
import com.stackroute.FoodieService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class FoodieServiceImpl implements FoodieService{
    private UserRepository userRepository;
    private RestaurantRepository restaurantRepository;
    private CusineRepository cusineRepository;
    private UserAuthProxy userAuthProxy;
    private CartProducer cartProducer;
    private FavProducer favProducer;

    @Autowired
    public FoodieServiceImpl(UserRepository userRepository, RestaurantRepository restaurantRepository, CusineRepository cusineRepository, UserAuthProxy userAuthProxy, CartProducer cartProducer, FavProducer favProducer) {
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
        this.cusineRepository = cusineRepository;
        this.userAuthProxy = userAuthProxy;
        this.cartProducer=cartProducer;
        this.favProducer=favProducer;
    }

    @Override
    public User registerUser(User user) throws UserAlreadyExistsException {
        if(userRepository.findById(user.getEmail()).isPresent())
        {
            throw new UserAlreadyExistsException();
        }
        User user1=userRepository.save(user);
         ResponseEntity<?> responseEntity= userAuthProxy.saveUser(user);
        System.out.println(responseEntity.getBody());
        return user1;
    }

    @Override
    public List<Cusine> getAllCusine() {
        return cusineRepository.findAll();
    }

    @Override
    public List<Restaurant> getAllRestaurant() {
        return restaurantRepository.findAll();
    }

    @Override
    public List<Cusine> getAllCusineByCity(String city) {
        return cusineRepository.findByCityIgnoreCase(city);
    }

    @Override
    public List<Restaurant> getAllRestaurantByCity(String city) {
        return restaurantRepository.findByCityIgnoreCase(city);
    }

    @Override
    public Cusine getCusineByID(int id) throws CusineNotFoundException {
        Cusine cuisine= cusineRepository.findById(id).get();
        return cuisine;
    }

    @Override
    public Restaurant getRestaurantByID(int id) throws RestaurantNotFoundException {
        return restaurantRepository.findById(id).get();
    }

    @Override
    public List<Cusine> getCusineByName(String name) {
        return cusineRepository.findByCusineNameIgnoreCase(name);
    }

    @Override
    public User getUserByID(String email) throws UserNotFoundException {
        return userRepository.findById(email).get();
    }

    @Override
    public CartDTO addCusineToCart(String email, int cusineID) {
        CartDTO cart=new CartDTO();
        cart.setEmail(email);
        cart.setCusineID(cusineID);
        cartProducer.sendMessageTORabbitMQ(cart);
        return cart;
    }

    @Override
    public FavouriteDTO addCusineToFavourite(String email, int cusineID) {
        FavouriteDTO fav=new FavouriteDTO();
        fav.setEmail(email);
        fav.setCusineID(cusineID);
        favProducer.sendMessageTORabbitMQ(fav);
        return fav;
    }

    @Override
    public FavouriteDTO addRestaurantToFavourite(String email, int restaurantID) {
        FavouriteDTO fav=new FavouriteDTO();
        fav.setEmail(email);
        fav.setResturantID(restaurantID);
        favProducer.sendMessageTORabbitMQ(fav);
        return fav;
    }

    @Override
    public String saveProfileImage(MultipartFile file) throws IOException {
        file.transferTo(new File("D:\\uploadedfiles\\"+file.getOriginalFilename()));
        return "D:\\uploadedfiles\\"+file.getOriginalFilename();
    }
}
