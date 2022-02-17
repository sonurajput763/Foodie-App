package com.stackroute.vendorService.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.vendorService.exception.ImageNotUpdatedException;
import com.stackroute.vendorService.model.Cusine;
import com.stackroute.vendorService.model.Restaurant;
import com.stackroute.vendorService.service.FileSaveService;
import com.stackroute.vendorService.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v2")
public class VendorController {
    ResponseEntity responseEntity;
    @Autowired
    VendorService vendorService;

    @Autowired
    FileSaveService fileSaveService;

    String location="default.jpg";

    @PostMapping("image/upload")
    public ResponseEntity saveImage(@RequestParam("file")MultipartFile file)
    {
        try
        {
            location=fileSaveService.save(file);
            responseEntity=new ResponseEntity(location,HttpStatus.CREATED);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return responseEntity;
    }

    @GetMapping("image/{image}")
    public ResponseEntity getImage(@PathVariable String image)
    {
        try{
            responseEntity=new ResponseEntity<>(fileSaveService.load(image), HttpStatus.OK);
        }

        catch(Exception e)
        {
            e.printStackTrace();
        }
        return  responseEntity;
    }

    @PostMapping("cusine")
    public ResponseEntity saveCusine(@RequestBody Cusine cusine)
    {
        try
        {

//            String location=vendorService.saveProfileImage(file);
//            ObjectMapper mapper=new ObjectMapper();
//            Cusine cusine=mapper.readValue(model,Cusine.class);
            cusine.setCusineImage(location);
            responseEntity = new ResponseEntity(vendorService.addCusine(cusine), HttpStatus.CREATED);
        }
        catch(ImageNotUpdatedException e)
        {
            e.printStackTrace();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return responseEntity;
    }

    @PostMapping("restaurant")
    public ResponseEntity saveRestaurant(@RequestBody Restaurant restaurant)
    {
        try
        {
//            String location=vendorService.saveProfileImage(file);
//            ObjectMapper mapper=new ObjectMapper();
//            Restaurant restaurant=mapper.readValue(model,Restaurant.class);
            restaurant.setLogo(location);
            responseEntity = new ResponseEntity(vendorService.addRestaurant(restaurant), HttpStatus.CREATED);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return responseEntity;
    }

    @DeleteMapping("cusine/{id}")
    public ResponseEntity deleteCusine(@PathVariable int id)
    {
        try
        {
            responseEntity=new ResponseEntity(vendorService.deleteCusine(id),HttpStatus.OK);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return responseEntity;
    }

    @DeleteMapping("restaurant/{id}")
    public ResponseEntity deleteRestaurant(@PathVariable int id)
    {
        try
        {
            responseEntity=new ResponseEntity(vendorService.deleteResturant(id),HttpStatus.OK);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return responseEntity;
    }

    @GetMapping("cusine/{email}")
    public ResponseEntity getAllCusinesByEmail(@PathVariable String email)
    {
        try
        {
            responseEntity=new ResponseEntity(vendorService.getCusineFromEmail(email),HttpStatus.OK);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return responseEntity;
    }

    @GetMapping("restaurant/{email}")
    public ResponseEntity getAllRestaurantByEmail(@PathVariable String email)
    {
        try
        {
            responseEntity=new ResponseEntity(vendorService.getRestaurantFromEmail(email),HttpStatus.OK);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return responseEntity;
    }

}
