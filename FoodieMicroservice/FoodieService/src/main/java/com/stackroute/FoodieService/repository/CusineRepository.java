package com.stackroute.FoodieService.repository;

import com.stackroute.FoodieService.domain.Cusine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CusineRepository extends JpaRepository<Cusine,Integer> {
    List<Cusine> findByCusineNameIgnoreCase(String name);
    List<Cusine> findByCityIgnoreCase(String city);
}
