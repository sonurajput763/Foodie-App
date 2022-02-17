package com.stackroute.CartService.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.stackroute.CartService.domain.Cart;
import com.stackroute.CartService.repository.CartRepository;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CartServiceImpl.class})
@ExtendWith(SpringExtension.class)
class CartServiceImplTest {
    @MockBean
    private CartRepository cartRepository;

    @Autowired
    private CartServiceImpl cartServiceImpl;

    @Test
    void testGetAllCartItemsFromEmail() {
        ArrayList<Cart> cartList = new ArrayList<>();
        when(this.cartRepository.findByEmail((String) any())).thenReturn(cartList);
        List<Cart> actualAllCartItemsFromEmail = this.cartServiceImpl.getAllCartItemsFromEmail("jane.doe@example.org");
        assertSame(cartList, actualAllCartItemsFromEmail);
        assertTrue(actualAllCartItemsFromEmail.isEmpty());
        verify(this.cartRepository).findByEmail((String) any());
    }

    @Test
    void testDeleteCartItem() {
        doNothing().when(this.cartRepository).deleteById((Integer) any());
        assertTrue(this.cartServiceImpl.deleteCartItem(1));
        verify(this.cartRepository).deleteById((Integer) any());
    }

    @Test
    void testSaveCusineToCart() {
        Cart cart = new Cart();
        cart.setCartID(1);
        cart.setCusineID(1);
        cart.setEmail("jane.doe@example.org");
        when(this.cartRepository.save((Cart) any())).thenReturn(cart);

        Cart cart1 = new Cart();
        cart1.setCartID(1);
        cart1.setCusineID(1);
        cart1.setEmail("jane.doe@example.org");
        assertSame(cart1, this.cartServiceImpl.saveCusineToCart(cart1));
        verify(this.cartRepository).save((Cart) any());
    }

    @Test
    void testSaveCusineToCartAlt() {
        Cart cart = new Cart();
        cart.setCartID(1);
        cart.setCusineID(1);
        cart.setEmail("jane.doe@example.org");
        when(this.cartRepository.save((Cart) any())).thenReturn(cart);
        Cart actualSaveCusineToCartAltResult = this.cartServiceImpl.saveCusineToCartAlt("jane.doe@example.org", 1);
        assertEquals("jane.doe@example.org", actualSaveCusineToCartAltResult.getEmail());
        assertEquals(1, actualSaveCusineToCartAltResult.getCusineID());
        verify(this.cartRepository).save((Cart) any());
    }
}

