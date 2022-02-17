package com.stackroute.vendorService.controller;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.vendorService.exception.ImageNotUpdatedException;
import com.stackroute.vendorService.model.Cusine;
import com.stackroute.vendorService.model.Restaurant;
import com.stackroute.vendorService.service.FileSaveService;
import com.stackroute.vendorService.service.VendorService;

import java.io.UnsupportedEncodingException;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {VendorController.class})
@ExtendWith(SpringExtension.class)
class VendorControllerTest {
    @MockBean
    private FileSaveService fileSaveService;

    @Autowired
    private VendorController vendorController;

    @MockBean
    private VendorService vendorService;


    @Test
    void testDeleteCusine() throws Exception {
        when(this.vendorService.deleteCusine(anyInt())).thenReturn(true);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/v2/cusine/{id}", 1);
        MockMvcBuilders.standaloneSetup(this.vendorController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<Boolean>true</Boolean>"));
    }

    @Test
    void testDeleteCusine2() throws Exception {
        when(this.vendorService.deleteCusine(anyInt())).thenReturn(true);
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/api/v2/cusine/{id}", 1);
        deleteResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(this.vendorController)
                .build()
                .perform(deleteResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<Boolean>true</Boolean>"));
    }

    @Test
    void testDeleteRestaurant() throws Exception {
        when(this.vendorService.deleteResturant(anyInt())).thenReturn(true);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/v2/restaurant/{id}", 1);
        MockMvcBuilders.standaloneSetup(this.vendorController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<Boolean>true</Boolean>"));
    }

    @Test
    void testDeleteRestaurant2() throws Exception {
        when(this.vendorService.deleteResturant(anyInt())).thenReturn(true);
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/api/v2/restaurant/{id}", 1);
        deleteResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(this.vendorController)
                .build()
                .perform(deleteResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<Boolean>true</Boolean>"));
    }

    @Test
    void testGetAllCusinesByEmail() throws Exception {
        when(this.vendorService.getCusineFromEmail((String) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v2/cusine/{email}",
                "jane.doe@example.org");
        MockMvcBuilders.standaloneSetup(this.vendorController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<ArrayList/>"));
    }

    @Test
    void testGetAllCusinesByEmail2() throws Exception {
        when(this.vendorService.getCusineFromEmail((String) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/v2/cusine/{email}",
                "jane.doe@example.org");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(this.vendorController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<ArrayList/>"));
    }

    @Test
    void testGetAllRestaurantByEmail() throws Exception {
        when(this.vendorService.getRestaurantFromEmail((String) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v2/restaurant/{email}",
                "jane.doe@example.org");
        MockMvcBuilders.standaloneSetup(this.vendorController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<ArrayList/>"));
    }

    @Test
    void testGetAllRestaurantByEmail2() throws Exception {
        when(this.vendorService.getRestaurantFromEmail((String) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/v2/restaurant/{email}",
                "jane.doe@example.org");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(this.vendorController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<ArrayList/>"));
    }

    @Test
    void testSaveCusine() throws Exception, ImageNotUpdatedException {
        Cusine cusine = new Cusine();
        cusine.setCategory1("Category1");
        cusine.setCategory2("Category2");
        cusine.setCity("Oxford");
        cusine.setCusineCost(10.0);
        cusine.setCusineId(123);
        cusine.setCusineImage("Cusine Image");
        cusine.setCusineName("Cusine Name");
        cusine.setCusineSize("Cusine Size");
        cusine.setEmail("jane.doe@example.org");
        cusine.setResturantName("Resturant Name");
        when(this.vendorService.addCusine((Cusine) any())).thenReturn(cusine);

        Cusine cusine1 = new Cusine();
        cusine1.setCategory1("Category1");
        cusine1.setCategory2("Category2");
        cusine1.setCity("Oxford");
        cusine1.setCusineCost(10.0);
        cusine1.setCusineId(123);
        cusine1.setCusineImage("Cusine Image");
        cusine1.setCusineName("Cusine Name");
        cusine1.setCusineSize("Cusine Size");
        cusine1.setEmail("jane.doe@example.org");
        cusine1.setResturantName("Resturant Name");
        String content = (new ObjectMapper()).writeValueAsString(cusine1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v2/cusine")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.vendorController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "<Cusine><cusineId>123</cusineId><cusineName>Cusine Name</cusineName><category1>Category1</category1>"
                                        + "<category2>Category2</category2><cusineImage>Cusine Image</cusineImage><cusineCost>10.0</cusineCost>"
                                        + "<cusineSize>Cusine Size</cusineSize><city>Oxford</city><resturantName>Resturant Name</resturantName>"
                                        + "<email>jane.doe@example.org</email></Cusine>"));
    }

    @Test
    void testSaveRestaurant() throws Exception {
        Restaurant restaurant = new Restaurant();
        restaurant.setCity("Oxford");
        restaurant.setEmail("jane.doe@example.org");
        restaurant.setLogo("Logo");
        restaurant.setRestaurantId(123);
        restaurant.setRestaurantName("Restaurant Name");
        when(this.vendorService.addRestaurant((Restaurant) any())).thenReturn(restaurant);

        Restaurant restaurant1 = new Restaurant();
        restaurant1.setCity("Oxford");
        restaurant1.setEmail("jane.doe@example.org");
        restaurant1.setLogo("Logo");
        restaurant1.setRestaurantId(123);
        restaurant1.setRestaurantName("Restaurant Name");
        String content = (new ObjectMapper()).writeValueAsString(restaurant1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v2/restaurant")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.vendorController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("<Restaurant><restaurantId>123</restaurantId><restaurantName>Restaurant Name</restaurantName><city"
                                + ">Oxford</city><logo>Logo</logo><email>jane.doe@example.org</email></Restaurant>"));
    }
}

