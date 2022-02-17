package com.stackroute.vendorService.service;


import com.stackroute.vendorService.exception.CusineNotFoundException;
import com.stackroute.vendorService.exception.ImageNotUpdatedException;
import com.stackroute.vendorService.exception.RestaurantNotFoundException;
import com.stackroute.vendorService.model.Cusine;
import com.stackroute.vendorService.model.Restaurant;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface VendorService {

    public Cusine addCusine(Cusine cusine) throws ImageNotUpdatedException;
    public Restaurant addRestaurant(Restaurant restaurant);
    public boolean deleteCusine(int id) throws CusineNotFoundException;
    public boolean deleteResturant(int id) throws RestaurantNotFoundException;
    public List<Cusine> getCusineFromEmail(String email);
    public List<Restaurant> getRestaurantFromEmail(String email);
    public String saveProfileImage(MultipartFile file) throws IOException;
}
