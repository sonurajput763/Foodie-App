package com.stackroute.FavouriteService.service;

import com.stackroute.FavouriteService.domain.Favourite;
import com.stackroute.FavouriteService.exception.*;
import com.stackroute.FavouriteService.repository.FavouriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FavouriteServiceImpl implements FavouriteService {

    FavouriteRepository favouriteRepository;

    @Autowired
    public FavouriteServiceImpl(FavouriteRepository favouriteRepository) {
        this.favouriteRepository = favouriteRepository;
    }


    @Override
    public boolean deleteCusine(Integer id, String email) throws CusineNotFoundException {
        Favourite fav=favouriteRepository.findById(email).get();
        List<Integer> cusines=fav.getCusines();
        if(cusines==null)
        {
            throw new CusineNotFoundException();
        }
        cusines.remove(id);
        fav.setCusines(cusines);
        favouriteRepository.save(fav);
        return true;
    }

    @Override
    public boolean deleteResturant(Integer id, String email) throws ResturantNotFoundException {
        Favourite fav=favouriteRepository.findById(email).get();
        List<Integer> restaurants=fav.getRestaurants();
        if(restaurants==null)
        {
            throw new ResturantNotFoundException();
        }
        restaurants.remove(id);
        fav.setCusines(restaurants);
        favouriteRepository.save(fav);
        return true;
    }

    @Override
    public Favourite getFavouritesByEmail(String email) throws UserNotFoundException {
        return favouriteRepository.findById(email).get();
    }

    @Override
    public Favourite addFavourite(String email, int cusineID, int restaurantID) throws CusineAlreadyExistException, ResturantAlreadyExistException {
        if(favouriteRepository.findById(email).isEmpty())
        {
            Favourite fav1=new Favourite();
            fav1.setEmail(email);
            favouriteRepository.save(fav1);
        }
        Favourite fav=favouriteRepository.findById(email).get();
        if(restaurantID==0)
        {
            List<Integer> cusines=fav.getCusines();
            if(cusines==null)
            {
                cusines=new ArrayList<>();
            }
            if(cusines.contains(cusineID))
            {
                throw new CusineAlreadyExistException();
            }
            cusines.add(cusineID);
            fav.setCusines(cusines);
            favouriteRepository.save(fav);
        }
        if(cusineID==0)
        {
            List<Integer> restaurants=fav.getRestaurants();
            if(restaurants==null)
            {
                restaurants=new ArrayList<>();
            }
            if(restaurants.contains(restaurantID))
            {
                throw new ResturantAlreadyExistException();
            }
            restaurants.add(restaurantID);
            fav.setRestaurants(restaurants);
            favouriteRepository.save(fav);
        }
        return fav;

    }

}
