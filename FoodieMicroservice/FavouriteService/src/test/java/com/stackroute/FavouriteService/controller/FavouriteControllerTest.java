package com.stackroute.FavouriteService.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.when;

import com.stackroute.FavouriteService.domain.Favourite;
import com.stackroute.FavouriteService.service.FavouriteService;

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

@ContextConfiguration(classes = {FavouriteController.class})
@ExtendWith(SpringExtension.class)
class FavouriteControllerTest {
    @Autowired
    private FavouriteController favouriteController;

    @MockBean
    private FavouriteService favouriteService;

    @Test
    void testAddCusineToFavourite() throws Exception {
        Favourite favourite = new Favourite();
        favourite.setCusines(new ArrayList<>());
        favourite.setEmail("jane.doe@example.org");
        favourite.setRestaurants(new ArrayList<>());
        when(this.favouriteService.addFavourite((String) any(), anyInt(), anyInt())).thenReturn(favourite);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v5/cusine/{email}/{cusineID}",
                "jane.doe@example.org", 1);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.favouriteController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("<Favourite><email>jane.doe@example.org</email><restaurants/><cusines/></Favourite>"));
    }

    @Test
    void testAddRestaurantToFavourite() throws Exception {
        Favourite favourite = new Favourite();
        favourite.setCusines(new ArrayList<>());
        favourite.setEmail("jane.doe@example.org");
        favourite.setRestaurants(new ArrayList<>());
        when(this.favouriteService.addFavourite((String) any(), anyInt(), anyInt())).thenReturn(favourite);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/v5/restaurant/{email}/{restaurantID}", "jane.doe@example.org", 1);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.favouriteController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("<Favourite><email>jane.doe@example.org</email><restaurants/><cusines/></Favourite>"));
    }

    @Test
    void testDeleteCusineFromFavourite() throws Exception {
        when(this.favouriteService.deleteCusine((Integer) any(), (String) any())).thenReturn(true);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/api/v5/favourite/cusine/{email}/{id}", "jane.doe@example.org", 1);
        MockMvcBuilders.standaloneSetup(this.favouriteController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<Boolean>true</Boolean>"));
    }

    @Test
    void testDeleteCusineFromFavourite2() throws Exception {
        when(this.favouriteService.deleteCusine((Integer) any(), (String) any())).thenReturn(true);
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/api/v5/favourite/cusine/{email}/{id}",
                "jane.doe@example.org", 1);
        deleteResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(this.favouriteController)
                .build()
                .perform(deleteResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<Boolean>true</Boolean>"));
    }

    @Test
    void testDeleteRestaurantFromFavourite() throws Exception {
        when(this.favouriteService.deleteResturant((Integer) any(), (String) any())).thenReturn(true);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/api/v5/favourite/restaurant/{email}/{id}", "jane.doe@example.org", 1);
        MockMvcBuilders.standaloneSetup(this.favouriteController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<Boolean>true</Boolean>"));
    }

    @Test
    void testDeleteRestaurantFromFavourite2() throws Exception {
        when(this.favouriteService.deleteResturant((Integer) any(), (String) any())).thenReturn(true);
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders
                .delete("/api/v5/favourite/restaurant/{email}/{id}", "jane.doe@example.org", 1);
        deleteResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(this.favouriteController)
                .build()
                .perform(deleteResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<Boolean>true</Boolean>"));
    }

    @Test
    void testGetUserFavourites() throws Exception {
        Favourite favourite = new Favourite();
        favourite.setCusines(new ArrayList<>());
        favourite.setEmail("jane.doe@example.org");
        favourite.setRestaurants(new ArrayList<>());
        when(this.favouriteService.getFavouritesByEmail((String) any())).thenReturn(favourite);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v5/favourite/{email}",
                "jane.doe@example.org");
        MockMvcBuilders.standaloneSetup(this.favouriteController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("<Favourite><email>jane.doe@example.org</email><restaurants/><cusines/></Favourite>"));
    }
}

