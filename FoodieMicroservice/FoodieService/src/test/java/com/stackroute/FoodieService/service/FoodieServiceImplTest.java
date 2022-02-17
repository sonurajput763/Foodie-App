package com.stackroute.FoodieService.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.stackroute.FoodieService.DTODomain.CartDTO;
import com.stackroute.FoodieService.DTODomain.FavouriteDTO;
import com.stackroute.FoodieService.config.CartProducer;
import com.stackroute.FoodieService.config.FavProducer;
import com.stackroute.FoodieService.domain.Cusine;
import com.stackroute.FoodieService.domain.Restaurant;
import com.stackroute.FoodieService.domain.User;
import com.stackroute.FoodieService.exception.CusineNotFoundException;
import com.stackroute.FoodieService.exception.RestaurantNotFoundException;
import com.stackroute.FoodieService.exception.UserAlreadyExistsException;
import com.stackroute.FoodieService.exception.UserNotFoundException;
import com.stackroute.FoodieService.proxy.UserAuthProxy;
import com.stackroute.FoodieService.repository.CusineRepository;
import com.stackroute.FoodieService.repository.RestaurantRepository;
import com.stackroute.FoodieService.repository.UserRepository;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@ContextConfiguration(classes = {FoodieServiceImpl.class})
@ExtendWith(SpringExtension.class)
class FoodieServiceImplTest {
    @MockBean
    private CartProducer cartProducer;

    @MockBean
    private CusineRepository cusineRepository;

    @MockBean
    private FavProducer favProducer;

    @Autowired
    private FoodieServiceImpl foodieServiceImpl;

    @MockBean
    private RestaurantRepository restaurantRepository;

    @MockBean
    private UserAuthProxy userAuthProxy;

    @MockBean
    private UserRepository userRepository;

    @Test
    void testRegisterUser() throws UserAlreadyExistsException {
        User user = new User();
        user.setAccountType("3");
        user.setEmail("jane.doe@example.org");
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setPhoneNo("4105551212");
        user.setProfileImage("Profile Image");
        Optional<User> ofResult = Optional.of(user);
        when(this.userRepository.findById((String) any())).thenReturn(ofResult);

        User user1 = new User();
        user1.setAccountType("3");
        user1.setEmail("jane.doe@example.org");
        user1.setName("Name");
        user1.setPassword("iloveyou");
        user1.setPhoneNo("4105551212");
        user1.setProfileImage("Profile Image");
        assertThrows(UserAlreadyExistsException.class, () -> this.foodieServiceImpl.registerUser(user1));
        verify(this.userRepository).findById((String) any());
    }

    @Test
    void testRegisterUser2() throws UserAlreadyExistsException {
        User user = new User();
        user.setAccountType("3");
        user.setEmail("jane.doe@example.org");
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setPhoneNo("4105551212");
        user.setProfileImage("Profile Image");
        when(this.userRepository.save((User) any())).thenReturn(user);
        when(this.userRepository.findById((String) any())).thenReturn(Optional.empty());
        when(this.userAuthProxy.saveUser((User) any())).thenReturn(new ResponseEntity(HttpStatus.CONTINUE));

        User user1 = new User();
        user1.setAccountType("3");
        user1.setEmail("jane.doe@example.org");
        user1.setName("Name");
        user1.setPassword("iloveyou");
        user1.setPhoneNo("4105551212");
        user1.setProfileImage("Profile Image");
        assertSame(user, this.foodieServiceImpl.registerUser(user1));
        verify(this.userRepository).findById((String) any());
        verify(this.userRepository).save((User) any());
        verify(this.userAuthProxy).saveUser((User) any());
        assertTrue(this.foodieServiceImpl.getAllCusine().isEmpty());
    }

    @Test
    void testGetAllCusine() {
        ArrayList<Cusine> cusineList = new ArrayList<>();
        when(this.cusineRepository.findAll()).thenReturn(cusineList);
        List<Cusine> actualAllCusine = this.foodieServiceImpl.getAllCusine();
        assertSame(cusineList, actualAllCusine);
        assertTrue(actualAllCusine.isEmpty());
        verify(this.cusineRepository).findAll();
        assertTrue(this.foodieServiceImpl.getAllRestaurant().isEmpty());
    }

    @Test
    void testGetAllRestaurant() {
        ArrayList<Restaurant> restaurantList = new ArrayList<>();
        when(this.restaurantRepository.findAll()).thenReturn(restaurantList);
        List<Restaurant> actualAllRestaurant = this.foodieServiceImpl.getAllRestaurant();
        assertSame(restaurantList, actualAllRestaurant);
        assertTrue(actualAllRestaurant.isEmpty());
        verify(this.restaurantRepository).findAll();
        assertTrue(this.foodieServiceImpl.getAllCusine().isEmpty());
    }

    @Test
    void testGetAllCusineByCity() {
        ArrayList<Cusine> cusineList = new ArrayList<>();
        when(this.cusineRepository.findByCityIgnoreCase((String) any())).thenReturn(cusineList);
        List<Cusine> actualAllCusineByCity = this.foodieServiceImpl.getAllCusineByCity("Oxford");
        assertSame(cusineList, actualAllCusineByCity);
        assertTrue(actualAllCusineByCity.isEmpty());
        verify(this.cusineRepository).findByCityIgnoreCase((String) any());
        assertTrue(this.foodieServiceImpl.getAllCusine().isEmpty());
        assertTrue(this.foodieServiceImpl.getAllRestaurant().isEmpty());
    }

    @Test
    void testGetAllRestaurantByCity() {
        ArrayList<Restaurant> restaurantList = new ArrayList<>();
        when(this.restaurantRepository.findByCityIgnoreCase((String) any())).thenReturn(restaurantList);
        List<Restaurant> actualAllRestaurantByCity = this.foodieServiceImpl.getAllRestaurantByCity("Oxford");
        assertSame(restaurantList, actualAllRestaurantByCity);
        assertTrue(actualAllRestaurantByCity.isEmpty());
        verify(this.restaurantRepository).findByCityIgnoreCase((String) any());
        assertTrue(this.foodieServiceImpl.getAllCusine().isEmpty());
        assertTrue(this.foodieServiceImpl.getAllRestaurant().isEmpty());
    }

    @Test
    void testGetCusineByID() throws CusineNotFoundException {
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
        when(this.cusineRepository.getById((Integer) any())).thenReturn(cusine);
        assertSame(cusine, this.foodieServiceImpl.getCusineByID(1));
        verify(this.cusineRepository).getById((Integer) any());
        assertTrue(this.foodieServiceImpl.getAllCusine().isEmpty());
        assertTrue(this.foodieServiceImpl.getAllRestaurant().isEmpty());
    }

    @Test
    void testGetRestaurantByID() throws RestaurantNotFoundException {
        Restaurant restaurant = new Restaurant();
        restaurant.setCity("Oxford");
        restaurant.setEmail("jane.doe@example.org");
        restaurant.setLogo("Logo");
        restaurant.setRestaurantId(123);
        restaurant.setRestaurantName("Restaurant Name");
        when(this.restaurantRepository.getById((Integer) any())).thenReturn(restaurant);
        assertSame(restaurant, this.foodieServiceImpl.getRestaurantByID(1));
        verify(this.restaurantRepository).getById((Integer) any());
        assertTrue(this.foodieServiceImpl.getAllCusine().isEmpty());
        assertTrue(this.foodieServiceImpl.getAllRestaurant().isEmpty());
    }

    @Test
    void testGetCusineByName() {
        ArrayList<Cusine> cusineList = new ArrayList<>();
        when(this.cusineRepository.findByCusineNameIgnoreCase((String) any())).thenReturn(cusineList);
        List<Cusine> actualCusineByName = this.foodieServiceImpl.getCusineByName("Name");
        assertSame(cusineList, actualCusineByName);
        assertTrue(actualCusineByName.isEmpty());
        verify(this.cusineRepository).findByCusineNameIgnoreCase((String) any());
        assertTrue(this.foodieServiceImpl.getAllCusine().isEmpty());
        assertTrue(this.foodieServiceImpl.getAllRestaurant().isEmpty());
    }

    @Test
    void testGetUserByID() throws UserNotFoundException {
        User user = new User();
        user.setAccountType("3");
        user.setEmail("jane.doe@example.org");
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setPhoneNo("4105551212");
        user.setProfileImage("Profile Image");
        Optional<User> ofResult = Optional.of(user);
        when(this.userRepository.findById((String) any())).thenReturn(ofResult);
        assertSame(user, this.foodieServiceImpl.getUserByID("jane.doe@example.org"));
        verify(this.userRepository).findById((String) any());
        assertTrue(this.foodieServiceImpl.getAllCusine().isEmpty());
    }

    @Test
    void testAddCusineToCart() {
        doNothing().when(this.cartProducer).sendMessageTORabbitMQ((CartDTO) any());
        CartDTO actualAddCusineToCartResult = this.foodieServiceImpl.addCusineToCart("jane.doe@example.org", 1);
        assertEquals(1, actualAddCusineToCartResult.getCusineID());
        assertEquals("jane.doe@example.org", actualAddCusineToCartResult.getEmail());
        verify(this.cartProducer).sendMessageTORabbitMQ((CartDTO) any());
        assertTrue(this.foodieServiceImpl.getAllCusine().isEmpty());
    }

    @Test
    void testAddCusineToFavourite() {
        doNothing().when(this.favProducer).sendMessageTORabbitMQ((FavouriteDTO) any());
        FavouriteDTO actualAddCusineToFavouriteResult = this.foodieServiceImpl.addCusineToFavourite("jane.doe@example.org",
                1);
        assertEquals(1, actualAddCusineToFavouriteResult.getCusineID());
        assertEquals("jane.doe@example.org", actualAddCusineToFavouriteResult.getEmail());
        verify(this.favProducer).sendMessageTORabbitMQ((FavouriteDTO) any());
        assertTrue(this.foodieServiceImpl.getAllCusine().isEmpty());
    }

    @Test
    void testAddRestaurantToFavourite() {
        doNothing().when(this.favProducer).sendMessageTORabbitMQ((FavouriteDTO) any());
        FavouriteDTO actualAddRestaurantToFavouriteResult = this.foodieServiceImpl
                .addRestaurantToFavourite("jane.doe@example.org", 1);
        assertEquals(1, actualAddRestaurantToFavouriteResult.getResturantID());
        assertEquals("jane.doe@example.org", actualAddRestaurantToFavouriteResult.getEmail());
        verify(this.favProducer).sendMessageTORabbitMQ((FavouriteDTO) any());
        assertTrue(this.foodieServiceImpl.getAllCusine().isEmpty());
    }

    @Test
    void testSaveProfileImage() throws IOException, IllegalStateException {
        CommonsMultipartFile commonsMultipartFile = mock(CommonsMultipartFile.class);
        doNothing().when(commonsMultipartFile).transferTo((java.io.File) any());
        when(commonsMultipartFile.getOriginalFilename()).thenReturn("foo.txt");
        assertEquals("D:\\uploadedfiles\\foo.txt", this.foodieServiceImpl.saveProfileImage(commonsMultipartFile));
        verify(commonsMultipartFile, atLeast(1)).getOriginalFilename();
        verify(commonsMultipartFile).transferTo((java.io.File) any());
        assertTrue(this.foodieServiceImpl.getAllCusine().isEmpty());
    }
}

