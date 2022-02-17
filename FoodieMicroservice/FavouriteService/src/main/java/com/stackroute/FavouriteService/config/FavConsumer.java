package com.stackroute.FavouriteService.config;


import com.stackroute.FavouriteService.DTODomain.FavouriteDTO;
import com.stackroute.FavouriteService.exception.CusineAlreadyExistException;
import com.stackroute.FavouriteService.exception.ResturantAlreadyExistException;
import com.stackroute.FavouriteService.service.FavouriteServiceImpl;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FavConsumer {
    @Autowired
    private FavouriteServiceImpl favouriteService;

    @RabbitListener(queues = "fav_queue")
    public void getCartFromRabbitMQ(FavouriteDTO favouriteDTO) throws ResturantAlreadyExistException, CusineAlreadyExistException {
        String email=favouriteDTO.getEmail();
        int cusineID=favouriteDTO.getCusineID();
        int restaurantID=favouriteDTO.getResturantID();

        favouriteService.addFavourite(email,cusineID,restaurantID);
    }
}
