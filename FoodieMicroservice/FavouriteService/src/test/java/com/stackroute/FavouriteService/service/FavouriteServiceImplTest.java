package com.stackroute.FavouriteService.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.stackroute.FavouriteService.domain.Favourite;
import com.stackroute.FavouriteService.exception.*;
import com.stackroute.FavouriteService.repository.FavouriteRepository;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {FavouriteServiceImpl.class})
@ExtendWith(SpringExtension.class)
class FavouriteServiceImplTest {
    @MockBean
    private FavouriteRepository favouriteRepository;

    @Autowired
    private FavouriteServiceImpl favouriteServiceImpl;

    @Test
    void testDeleteCusine() throws CusineNotFoundException {
        Favourite favourite = new Favourite();
        favourite.setCusines(new ArrayList<>());
        favourite.setEmail("jane.doe@example.org");
        favourite.setRestaurants(new ArrayList<>());
        Optional<Favourite> ofResult = Optional.of(favourite);

        Favourite favourite1 = new Favourite();
        favourite1.setCusines(new ArrayList<>());
        favourite1.setEmail("jane.doe@example.org");
        favourite1.setRestaurants(new ArrayList<>());
        when(this.favouriteRepository.save((Favourite) any())).thenReturn(favourite1);
        when(this.favouriteRepository.findById((String) any())).thenReturn(ofResult);
        assertTrue(this.favouriteServiceImpl.deleteCusine(1, "jane.doe@example.org"));
        verify(this.favouriteRepository).findById((String) any());
        verify(this.favouriteRepository).save((Favourite) any());
    }

    @Test
    void testDeleteResturant() throws ResturantNotFoundException {
        Favourite favourite = new Favourite();
        favourite.setCusines(new ArrayList<>());
        favourite.setEmail("jane.doe@example.org");
        favourite.setRestaurants(new ArrayList<>());
        Optional<Favourite> ofResult = Optional.of(favourite);

        Favourite favourite1 = new Favourite();
        favourite1.setCusines(new ArrayList<>());
        favourite1.setEmail("jane.doe@example.org");
        favourite1.setRestaurants(new ArrayList<>());
        when(this.favouriteRepository.save((Favourite) any())).thenReturn(favourite1);
        when(this.favouriteRepository.findById((String) any())).thenReturn(ofResult);
        assertTrue(this.favouriteServiceImpl.deleteResturant(1, "jane.doe@example.org"));
        verify(this.favouriteRepository).findById((String) any());
        verify(this.favouriteRepository).save((Favourite) any());
    }

    @Test
    void testGetFavouritesByEmail() throws UserNotFoundException {
        Favourite favourite = new Favourite();
        favourite.setCusines(new ArrayList<>());
        favourite.setEmail("jane.doe@example.org");
        favourite.setRestaurants(new ArrayList<>());
        Optional<Favourite> ofResult = Optional.of(favourite);
        when(this.favouriteRepository.findById((String) any())).thenReturn(ofResult);
        assertSame(favourite, this.favouriteServiceImpl.getFavouritesByEmail("jane.doe@example.org"));
        verify(this.favouriteRepository).findById((String) any());
    }

    @Test
    void testAddFavourite() throws ResturantAlreadyExistException, CusineAlreadyExistException {
        Favourite favourite = new Favourite();
        favourite.setCusines(new ArrayList<>());
        favourite.setEmail("jane.doe@example.org");
        favourite.setRestaurants(new ArrayList<>());
        Optional<Favourite> ofResult = Optional.of(favourite);
        when(this.favouriteRepository.findById((String) any())).thenReturn(ofResult);
        assertSame(favourite, this.favouriteServiceImpl.addFavourite("jane.doe@example.org", 1, 1));
        verify(this.favouriteRepository, atLeast(1)).findById((String) any());
    }
}

