package com.stackroute.vendorService.service;

import com.stackroute.vendorService.exception.CusineNotFoundException;
import com.stackroute.vendorService.exception.ImageNotUpdatedException;
import com.stackroute.vendorService.exception.RestaurantNotFoundException;
import com.stackroute.vendorService.model.Cusine;
import com.stackroute.vendorService.model.Restaurant;
import com.stackroute.vendorService.repositroy.CusineRepository;
import com.stackroute.vendorService.repositroy.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VendorServiceImpl implements VendorService{
    private CusineRepository cusineRepository;
    private RestaurantRepository restaurantRepository;

    @Autowired
    public VendorServiceImpl(CusineRepository cusineRepository, RestaurantRepository restaurantRepository) {
        this.cusineRepository = cusineRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public Cusine addCusine(Cusine cusine) throws ImageNotUpdatedException {
//        if(cusine.getCusineImage().equalsIgnoreCase("default.jpg"))
//        {
//            throw new ImageNotUpdatedException();
//        }
        return cusineRepository.save(cusine);
    }

    @Override
    public Restaurant addRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @Override
    public boolean deleteCusine(int id) throws CusineNotFoundException {
        cusineRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean deleteResturant(int id) throws RestaurantNotFoundException {
        restaurantRepository.deleteById(id);
        return true;
    }

    @Override
    public List<Cusine> getCusineFromEmail(String email) {
        return cusineRepository.findByEmail(email);
    }

    @Override
    public List<Restaurant> getRestaurantFromEmail(String email) {
        return restaurantRepository.findByEmail(email);
    }

    @Override
    public String saveProfileImage(MultipartFile file) throws IOException {
        file.transferTo(new File("D:\\uploadedfiles\\"+file.getOriginalFilename()));
        return "D:\\uploadedfiles\\"+file.getOriginalFilename();
    }
}
