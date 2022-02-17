package com.stackroute.FoodieService.repository;

import com.stackroute.FoodieService.domain.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant,Integer> {
    List<Restaurant> findByCityIgnoreCase(String city);
}
