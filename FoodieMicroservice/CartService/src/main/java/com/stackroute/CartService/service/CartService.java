package com.stackroute.CartService.service;

import com.stackroute.CartService.domain.Cart;
import com.stackroute.CartService.exception.CustomerNotFoundException;

import java.util.List;

public interface CartService {
    public List<Cart> getAllCartItemsFromEmail(String email);
    public boolean deleteCartItem(int id);
    public Cart saveCusineToCart(Cart cart);
    public Cart saveCusineToCartAlt(String email,int cusineID);
    public boolean deleteAllOrdersForUser(List<Cart> user);
}
