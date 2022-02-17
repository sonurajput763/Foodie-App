package com.stackroute.FoodieService.repositorty;

import com.stackroute.FoodieService.domain.Cusine;
import com.stackroute.FoodieService.repository.CusineRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class CuisineRepositoryTest {

    @Autowired
    CusineRepository cusineRepository;

    private Cusine cusine1,cusine2;

    @BeforeEach
    public void setUp()
    {
        cusineRepository.deleteAll();
        cusine1=new Cusine("Meal-combo","veg","indian","D://images//pic1.jpg",150,"full-plate","Hydrabad","VK Family Restaurant","email1@gmail.com");
        cusine2=new Cusine("Snack-combo","veg","indian","D://images//pic1.jpg",100,"half-plate","Hydrabad","VK Family Restaurant","email1@gmail.com");
    }
    @AfterEach
    public void tearDown()
    {
        cusine1=null;
        cusine2=null;
        cusineRepository.deleteAll();
    }

    @Test
    public void givenCusineSaveAndReturnCusine()
    {
        cusineRepository.save(cusine1);
        Cusine saveCusine=cusineRepository.findByCusineNameIgnoreCase(cusine1.getCusineName()).get(0);
        assertNotNull(saveCusine);
        assertEquals(saveCusine.getCusineName(),cusine1.getCusineName());
    }

    @Test
    public void returnAllCuisines()
    {
        cusineRepository.save(cusine1);
        cusineRepository.save(cusine2);
        List<Cusine> savedCusine=cusineRepository.findAll();
        assertEquals(2,savedCusine.size());
        assertEquals(savedCusine.get(0).getCusineName(),cusine1.getCusineName());
    }

    @Test
    public void givenIdShouldDeleteCusine()
    {
        cusineRepository.save(cusine1);
        int id=cusineRepository.findByCusineNameIgnoreCase(cusine1.getCusineName()).get(0).getCusineId();
        List<Cusine> savedCusine=cusineRepository.findAll();
        assertNotNull(savedCusine);
        cusineRepository.deleteById(id);
        assertEquals(Optional.empty(),cusineRepository.findById(id));
    }

    @Test
    public void givenLocationShouldReturnList()
    {
        cusineRepository.save(cusine1);
        cusineRepository.save(cusine2);
        List<Cusine> savedCusine=cusineRepository.findByCityIgnoreCase(cusine1.getCity());
        assertEquals(2,savedCusine.size());
        assertEquals(savedCusine.get(0).getCusineName(),cusine1.getCusineName());
    }
}
