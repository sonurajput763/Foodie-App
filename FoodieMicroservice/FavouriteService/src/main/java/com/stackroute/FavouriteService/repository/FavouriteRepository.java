package com.stackroute.FavouriteService.repository;

import com.stackroute.FavouriteService.domain.Favourite;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavouriteRepository extends MongoRepository<Favourite,String> {

}
