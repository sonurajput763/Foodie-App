package com.stackroute.CartService.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.when;

import com.stackroute.CartService.domain.Cart;
import com.stackroute.CartService.service.CartService;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {CartController.class})
@ExtendWith(SpringExtension.class)
class CartControllerTest {
    @Autowired
    private CartController cartController;

    @MockBean
    private CartService cartService;

    @Test
    void testAddCusineToCart() throws Exception {
        Cart cart = new Cart();
        cart.setCartID(1);
        cart.setCusineID(1);
        cart.setEmail("jane.doe@example.org");
        when(this.cartService.saveCusineToCartAlt((String) any(), anyInt())).thenReturn(cart);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v4/cart/{email}/{id}",
                "jane.doe@example.org", 1);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.cartController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("<Cart><cartID>1</cartID><email>jane.doe@example.org</email><cusineID>1</cusineID></Cart>"));
    }

    @Test
    void testDeleteCartItem() throws Exception {
        when(this.cartService.deleteCartItem(anyInt())).thenReturn(true);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/v4/cart/{id}", 1);
        MockMvcBuilders.standaloneSetup(this.cartController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<Boolean>true</Boolean>"));
    }

    @Test
    void testDeleteCartItem2() throws Exception {
        when(this.cartService.deleteCartItem(anyInt())).thenReturn(true);
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/api/v4/cart/{id}", 1);
        deleteResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(this.cartController)
                .build()
                .perform(deleteResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<Boolean>true</Boolean>"));
    }

    @Test
    void testGetAllItemsFromCart() throws Exception {
        when(this.cartService.getAllCartItemsFromEmail((String) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v4/cart/{email}",
                "jane.doe@example.org");
        MockMvcBuilders.standaloneSetup(this.cartController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<ArrayList/>"));
    }

    @Test
    void testGetAllItemsFromCart2() throws Exception {
        when(this.cartService.getAllCartItemsFromEmail((String) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/v4/cart/{email}",
                "jane.doe@example.org");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(this.cartController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<ArrayList/>"));
    }
}

