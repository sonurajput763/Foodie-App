package com.stackroute.vendorService.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.stackroute.vendorService.exception.CusineNotFoundException;
import com.stackroute.vendorService.exception.RestaurantNotFoundException;
import com.stackroute.vendorService.model.Cusine;
import com.stackroute.vendorService.model.Restaurant;
import com.stackroute.vendorService.repositroy.CusineRepository;
import com.stackroute.vendorService.repositroy.RestaurantRepository;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {VendorServiceImpl.class})
@ExtendWith(SpringExtension.class)
class VendorServiceImplTest {
    @MockBean
    private CusineRepository cusineRepository;

    @MockBean
    private RestaurantRepository restaurantRepository;

    @Autowired
    private VendorServiceImpl vendorServiceImpl;

//    @Test
//    void testAddCusine() {
//        Cusine cusine = new Cusine();
//        cusine.setCategory1("Category1");
//        cusine.setCategory2("Category2");
//        cusine.setCity("Oxford");
//        cusine.setCusineCost(10.0);
//        cusine.setCusineId(123);
//        cusine.setCusineImage("Cusine Image");
//        cusine.setCusineName("Cusine Name");
//        cusine.setCusineSize("Cusine Size");
//        cusine.setEmail("jane.doe@example.org");
//        cusine.setResturantName("Resturant Name");
//        when(this.cusineRepository.save((Cusine) any())).thenReturn(cusine);
//
//        Cusine cusine1 = new Cusine();
//        cusine1.setCategory1("Category1");
//        cusine1.setCategory2("Category2");
//        cusine1.setCity("Oxford");
//        cusine1.setCusineCost(10.0);
//        cusine1.setCusineId(123);
//        cusine1.setCusineImage("Cusine Image");
//        cusine1.setCusineName("Cusine Name");
//        cusine1.setCusineSize("Cusine Size");
//        cusine1.setEmail("jane.doe@example.org");
//        cusine1.setResturantName("Resturant Name");
//        assertSame(cusine, this.vendorServiceImpl.addCusine(cusine1));
//        verify(this.cusineRepository).save((Cusine) any());
//    }

    @Test
    void testAddRestaurant() {
        Restaurant restaurant = new Restaurant();
        restaurant.setCity("Oxford");
        restaurant.setEmail("jane.doe@example.org");
        restaurant.setLogo("Logo");
        restaurant.setRestaurantId(123);
        restaurant.setRestaurantName("Restaurant Name");
        when(this.restaurantRepository.save((Restaurant) any())).thenReturn(restaurant);

        Restaurant restaurant1 = new Restaurant();
        restaurant1.setCity("Oxford");
        restaurant1.setEmail("jane.doe@example.org");
        restaurant1.setLogo("Logo");
        restaurant1.setRestaurantId(123);
        restaurant1.setRestaurantName("Restaurant Name");
        assertSame(restaurant, this.vendorServiceImpl.addRestaurant(restaurant1));
        verify(this.restaurantRepository).save((Restaurant) any());
    }

    @Test
    void testDeleteCusine() throws CusineNotFoundException {
        doNothing().when(this.cusineRepository).deleteById((Integer) any());
        assertTrue(this.vendorServiceImpl.deleteCusine(1));
        verify(this.cusineRepository).deleteById((Integer) any());
    }

    @Test
    void testDeleteResturant() throws RestaurantNotFoundException {
        doNothing().when(this.restaurantRepository).deleteById((Integer) any());
        assertTrue(this.vendorServiceImpl.deleteResturant(1));
        verify(this.restaurantRepository).deleteById((Integer) any());
    }

    @Test
    void testGetCusineFromEmail() {
        ArrayList<Cusine> cusineList = new ArrayList<>();
        when(this.cusineRepository.findByEmail((String) any())).thenReturn(cusineList);
        List<Cusine> actualCusineFromEmail = this.vendorServiceImpl.getCusineFromEmail("jane.doe@example.org");
        assertSame(cusineList, actualCusineFromEmail);
        assertTrue(actualCusineFromEmail.isEmpty());
        verify(this.cusineRepository).findByEmail((String) any());
    }

    @Test
    void testGetRestaurantFromEmail() {
        ArrayList<Restaurant> restaurantList = new ArrayList<>();
        when(this.restaurantRepository.findByEmail((String) any())).thenReturn(restaurantList);
        List<Restaurant> actualRestaurantFromEmail = this.vendorServiceImpl.getRestaurantFromEmail("jane.doe@example.org");
        assertSame(restaurantList, actualRestaurantFromEmail);
        assertTrue(actualRestaurantFromEmail.isEmpty());
        verify(this.restaurantRepository).findByEmail((String) any());
    }

    @Test
    void testSaveProfileImage() throws IOException, IllegalStateException {
        MockMultipartFile mockMultipartFile = mock(MockMultipartFile.class);
        doNothing().when(mockMultipartFile).transferTo((java.io.File) any());
        when(mockMultipartFile.getOriginalFilename()).thenReturn("foo.txt");
        assertEquals("D:\\uploadedfiles\\foo.txt", this.vendorServiceImpl.saveProfileImage(mockMultipartFile));
        verify(mockMultipartFile, atLeast(1)).getOriginalFilename();
        verify(mockMultipartFile).transferTo((java.io.File) any());
    }
}

