package com.stackroute.CartService.controller;

import com.stackroute.CartService.domain.Cart;
import com.stackroute.CartService.exception.CustomerNotFoundException;
import com.stackroute.CartService.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/v4/")
public class CartController {

    private CartService cartService;
    @Autowired
    public CartController(CartService cartService)
    {
        this.cartService=cartService;
    }

    private ResponseEntity responseEntity;


   @GetMapping("cart/{email}")
    public ResponseEntity getAllItemsFromCart(@PathVariable String email)
   {
       try
       {
           responseEntity=new ResponseEntity(cartService.getAllCartItemsFromEmail(email),HttpStatus.OK);
       }
       catch(Exception e)
       {
           e.printStackTrace();
       }
       return responseEntity;
   }

   @DeleteMapping("cart/{id}")
    public ResponseEntity deleteCartItem(@PathVariable int id)
   {
       try
       {
           responseEntity=new ResponseEntity(cartService.deleteCartItem(id),HttpStatus.OK);
       }
       catch(Exception e)
       {
           e.printStackTrace();
       }
       return responseEntity;
   }

    @PostMapping("cart/{email}/{id}")
    public ResponseEntity addCusineToCart(@PathVariable String email,@PathVariable int id)
    {
        try{
            responseEntity=new ResponseEntity<>(cartService.saveCusineToCartAlt(email,id), HttpStatus.CREATED);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return  responseEntity;
    }

    @DeleteMapping("cart/delete")
    public ResponseEntity deleteCusineForUser(@RequestBody List<Cart> user)
    {
        try{
            responseEntity=new ResponseEntity<>(cartService.deleteAllOrdersForUser(user), HttpStatus.OK);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return  responseEntity;
    }
}
