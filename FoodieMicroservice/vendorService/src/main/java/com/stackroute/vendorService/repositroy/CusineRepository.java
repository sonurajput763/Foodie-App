package com.stackroute.vendorService.repositroy;


import com.stackroute.vendorService.model.Cusine;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CusineRepository extends JpaRepository<Cusine,Integer> {
    public List<Cusine> findByEmail(String email);
}
