package com.stackroute.CartService.service;

import com.stackroute.CartService.domain.Cart;
import com.stackroute.CartService.exception.CustomerNotFoundException;
import com.stackroute.CartService.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService{

    CartRepository cartRepository;
    @Autowired
    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }


    @Override
    public List<Cart> getAllCartItemsFromEmail(String email) {
        return cartRepository.findByEmail(email);
    }

    @Override
    public boolean deleteCartItem(int id) {
        cartRepository.deleteById(id);
        return true;
    }

    @Override
    public Cart saveCusineToCart(Cart cart) {
        cartRepository.save(cart);
        return cart;
    }

    @Override
    public Cart saveCusineToCartAlt(String email, int cusineID) {
        Cart cart=new Cart(email,cusineID);
        cartRepository.save(cart);
        return cart;
    }

    @Override
    public boolean deleteAllOrdersForUser(List<Cart> user) {
        for(Cart i:user)
        {
            cartRepository.deleteById(i.getCartID());
        }
        return true;
    }


}
