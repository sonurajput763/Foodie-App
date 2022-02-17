package com.stackroute.FavouriteService.service;

import com.stackroute.FavouriteService.domain.Favourite;
import com.stackroute.FavouriteService.exception.*;

import java.util.List;

public interface FavouriteService {

    public boolean deleteCusine(Integer id,String email) throws CusineNotFoundException;
    public boolean deleteResturant(Integer id,String email) throws ResturantNotFoundException;
    public Favourite getFavouritesByEmail(String email) throws UserNotFoundException;
    public Favourite addFavourite(String email,int cusineID,int restaurantID) throws CusineAlreadyExistException, ResturantAlreadyExistException;

}
