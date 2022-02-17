package com.stackroute.FoodieService.repositorty;

import com.stackroute.FoodieService.domain.Restaurant;
import com.stackroute.FoodieService.repository.RestaurantRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class RestaurantRepositoryTest {

    @Autowired
    RestaurantRepository restaurantRepository;

    Restaurant restaurant1,restaurant2;

    @BeforeEach
    public void setUp()
    {
        restaurantRepository.deleteAll();
        restaurant1=new Restaurant("AK restaurant","Bangalore","D://photos//logo1.jpg","email1@gmail.com");
        restaurant2=new Restaurant("VK restaurant","Bangalore","D://photos//logo2.jpg","email2@gmail.com");

    }

    @AfterEach
    public void tearDown()
    {
        restaurantRepository.deleteAll();
        restaurant1=null;
        restaurant2=null;
    }

    @Test
    public void shouldReturnRestaurantWhenCalled()
    {
        int id=restaurantRepository.save(restaurant1).getRestaurantId();
        Restaurant restaurant=restaurantRepository.findById(id).get();
        assertNotNull(restaurant);
        assertEquals(restaurant.getRestaurantName(),restaurant1.getRestaurantName());
    }

    @Test
    public void shouldReturnAllRestaurants()
    {
        restaurantRepository.save(restaurant1);
        restaurantRepository.save(restaurant2);
        List<Restaurant> savedRestaurants=restaurantRepository.findAll();
        assertEquals(2,savedRestaurants.size());
    }

    @Test
    public void shouldReturnAllRestaurantsWhenGivenCity()
    {
        restaurantRepository.save(restaurant1);
        restaurantRepository.save(restaurant2);
        List<Restaurant> savedRestaurants=restaurantRepository.findByCityIgnoreCase(restaurant1.getCity());
        assertEquals(2,savedRestaurants.size());
    }

    @Test
    public void shouldDeleteRestaurantWhenGivenId()
    {
        int id=restaurantRepository.save(restaurant1).getRestaurantId();
        Restaurant restaurant=restaurantRepository.findById(id).get();
        assertNotNull(restaurant);
        restaurantRepository.deleteById(id);
        assertEquals(Optional.empty(),restaurantRepository.findById(id));
    }



}
