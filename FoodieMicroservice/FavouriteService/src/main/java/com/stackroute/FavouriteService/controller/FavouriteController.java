package com.stackroute.FavouriteService.controller;

import com.stackroute.FavouriteService.domain.Favourite;
import com.stackroute.FavouriteService.exception.*;
import com.stackroute.FavouriteService.service.FavouriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/v5/")
public class FavouriteController {

    private FavouriteService favouriteService;
    @Autowired
    public FavouriteController(FavouriteService favouriteService) {
        this.favouriteService = favouriteService;
    }

    private ResponseEntity responseEntity;

    @DeleteMapping("favourite/cusine/{email}/{id}")
    public ResponseEntity deleteCusineFromFavourite(@PathVariable String email,@PathVariable int id)
    {
        try
        {
            responseEntity=new ResponseEntity(favouriteService.deleteCusine(id,email),HttpStatus.OK);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return responseEntity;
    }

    @DeleteMapping("favourite/restaurant/{email}/{id}")
    public ResponseEntity deleteRestaurantFromFavourite(@PathVariable String email,@PathVariable int id)
    {
        try
        {
            responseEntity=new ResponseEntity(favouriteService.deleteResturant(id,email),HttpStatus.OK);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return responseEntity;
    }

    @GetMapping("favourite/{email}")
    public ResponseEntity getUserFavourites(@PathVariable String email)
    {
        try
        {
            responseEntity=new ResponseEntity(favouriteService.getFavouritesByEmail(email),HttpStatus.OK);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return responseEntity;
    }
    @PostMapping("cusine/{email}/{cusineID}")
    public ResponseEntity addCusineToFavourite(@PathVariable String email,@PathVariable int cusineID)
    {
        try{
            responseEntity=new ResponseEntity<>(favouriteService.addFavourite(email,cusineID,0), HttpStatus.CREATED);
        }
        catch(CusineAlreadyExistException e)
        {
            responseEntity=new ResponseEntity("error",HttpStatus.OK);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return  responseEntity;
    }

    @PostMapping("restaurant/{email}/{restaurantID}")
    public ResponseEntity addRestaurantToFavourite(@PathVariable String email,@PathVariable int restaurantID)
    {
        try{
            responseEntity=new ResponseEntity<>(favouriteService.addFavourite(email,0,restaurantID), HttpStatus.CREATED);
        }
        catch(ResturantAlreadyExistException e)
        {
            responseEntity=new ResponseEntity("error",HttpStatus.OK);

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return  responseEntity;
    }


}
