package com.stackroute.FoodieService.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.FoodieService.DTODomain.CartDTO;
import com.stackroute.FoodieService.DTODomain.FavouriteDTO;
import com.stackroute.FoodieService.config.CartProducer;
import com.stackroute.FoodieService.config.FavProducer;
import com.stackroute.FoodieService.domain.User;
import com.stackroute.FoodieService.exception.UserAlreadyExistsException;
import com.stackroute.FoodieService.exception.UserNotFoundException;
import com.stackroute.FoodieService.proxy.UserAuthProxy;
import com.stackroute.FoodieService.repository.CusineRepository;
import com.stackroute.FoodieService.repository.RestaurantRepository;
import com.stackroute.FoodieService.repository.UserRepository;
import com.stackroute.FoodieService.service.FileSaveService;
import com.stackroute.FoodieService.service.FileSaveServiceImpl;
import com.stackroute.FoodieService.service.FoodieService;
import com.stackroute.FoodieService.service.FoodieServiceImpl;

import java.io.UnsupportedEncodingException;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {FoodieController.class})
@ExtendWith(SpringExtension.class)
class FoodieControllerTest {
    @MockBean
    private FileSaveService fileSaveService;

    @Autowired
    private FoodieController foodieController;

    @MockBean
    private FoodieService foodieService;

    @Test
    void testSaveUserProfileImage() throws UnsupportedEncodingException {
        UserRepository userRepository = mock(UserRepository.class);
        RestaurantRepository restaurantRepository = mock(RestaurantRepository.class);
        CusineRepository cusineRepository = mock(CusineRepository.class);
        UserAuthProxy userAuthProxy = mock(UserAuthProxy.class);
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        CartProducer cartProducer = new CartProducer(rabbitTemplate, new DirectExchange("Name"));

        RabbitTemplate rabbitTemplate1 = new RabbitTemplate();
        FoodieServiceImpl foodieService = new FoodieServiceImpl(userRepository, restaurantRepository, cusineRepository,
                userAuthProxy, cartProducer, new FavProducer(rabbitTemplate1, new DirectExchange("Name")));

        FoodieController foodieController = new FoodieController(foodieService, new FileSaveServiceImpl());
        assertNull(foodieController.saveUserProfileImage(new MockMultipartFile("Name", "AAAAAAAA".getBytes("UTF-8"))));
    }

    @Test
    void testSaveUserProfileImage2() throws UnsupportedEncodingException {
        UserRepository userRepository = mock(UserRepository.class);
        RestaurantRepository restaurantRepository = mock(RestaurantRepository.class);
        CusineRepository cusineRepository = mock(CusineRepository.class);
        UserAuthProxy userAuthProxy = mock(UserAuthProxy.class);
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        CartProducer cartProducer = new CartProducer(rabbitTemplate, new DirectExchange("Name"));

        RabbitTemplate rabbitTemplate1 = new RabbitTemplate();
        FoodieController foodieController = new FoodieController(new FoodieServiceImpl(userRepository, restaurantRepository,
                cusineRepository, userAuthProxy, cartProducer, new FavProducer(rabbitTemplate1, new DirectExchange("Name"))),
                null);
        assertNull(foodieController.saveUserProfileImage(new MockMultipartFile("Name", "AAAAAAAA".getBytes("UTF-8"))));
    }

    @Test
    void testSaveUserProfileImage3() throws UnsupportedEncodingException {
        FileSaveServiceImpl fileSaveServiceImpl = mock(FileSaveServiceImpl.class);
        when(fileSaveServiceImpl.save((org.springframework.web.multipart.MultipartFile) any())).thenReturn("Save");
        UserRepository userRepository = mock(UserRepository.class);
        RestaurantRepository restaurantRepository = mock(RestaurantRepository.class);
        CusineRepository cusineRepository = mock(CusineRepository.class);
        UserAuthProxy userAuthProxy = mock(UserAuthProxy.class);
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        CartProducer cartProducer = new CartProducer(rabbitTemplate, new DirectExchange("Name"));

        RabbitTemplate rabbitTemplate1 = new RabbitTemplate();
        FoodieController foodieController = new FoodieController(new FoodieServiceImpl(userRepository, restaurantRepository,
                cusineRepository, userAuthProxy, cartProducer, new FavProducer(rabbitTemplate1, new DirectExchange("Name"))),
                fileSaveServiceImpl);
        ResponseEntity actualSaveUserProfileImageResult = foodieController
                .saveUserProfileImage(new MockMultipartFile("Name", "AAAAAAAA".getBytes("UTF-8")));
        Object body = actualSaveUserProfileImageResult.getBody();
        assertEquals("Save", body);
        assertEquals("<201 CREATED Created,Save,[]>", actualSaveUserProfileImageResult.toString());
        assertEquals(HttpStatus.CREATED, actualSaveUserProfileImageResult.getStatusCode());
        assertTrue(actualSaveUserProfileImageResult.getHeaders().isEmpty());
        verify(fileSaveServiceImpl).save((org.springframework.web.multipart.MultipartFile) any());
        assertSame(body, foodieController.filename);
        ResponseEntity expectedAllRestaurant = foodieController.getAllCusine();
        assertEquals(expectedAllRestaurant, foodieController.getAllRestaurant());
    }

    @Test
    void testRegisterUser() throws Exception {
        User user = new User();
        user.setAccountType("3");
        user.setEmail("jane.doe@example.org");
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setPhoneNo("4105551212");
        user.setProfileImage("Profile Image");
        when(this.foodieService.registerUser((User) any())).thenReturn(user);

        User user1 = new User();
        user1.setAccountType("3");
        user1.setEmail("jane.doe@example.org");
        user1.setName("Name");
        user1.setPassword("iloveyou");
        user1.setPhoneNo("4105551212");
        user1.setProfileImage("Profile Image");
        String content = (new ObjectMapper()).writeValueAsString(user1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.foodieController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("<User><email>jane.doe@example.org</email><password>iloveyou</password><name>Name</name><phoneNo"
                                + ">4105551212</phoneNo><accountType>3</accountType><profileImage>Profile Image</profileImage></User>"));
    }

//    @Test
//    void testRegisterUser2() throws Exception {
//        when(this.foodieService.registerUser((User) any())).thenThrow(new UserAlreadyExistsException());
//
//        User user = new User();
//        user.setAccountType("3");
//        user.setEmail("jane.doe@example.org");
//        user.setName("Name");
//        user.setPassword("iloveyou");
//        user.setPhoneNo("4105551212");
//        user.setProfileImage("Profile Image");
//        String content = (new ObjectMapper()).writeValueAsString(user);
//        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/users/register")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(content);
//        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.foodieController)
//                .build()
//                .perform(requestBuilder);
//        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
//                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
//                .andExpect(MockMvcResultMatchers.content()
//                        .string("<User><email>jane.doe@example.org</email><password>iloveyou</password><name>Name</name><phoneNo"
//                                + ">4105551212</phoneNo><accountType>3</accountType><profileImage>Profile Image</profileImage></User>"));
//    }

    @Test
    void testGetAllRestaurant() throws Exception {
        when(this.foodieService.getAllRestaurant()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/restaurants");
        MockMvcBuilders.standaloneSetup(this.foodieController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<ArrayList/>"));
    }

    @Test
    void testGetAllRestaurant2() throws Exception {
        when(this.foodieService.getAllRestaurant()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/v1/restaurants");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(this.foodieController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<ArrayList/>"));
    }

    @Test
    void testGetAllCusine() throws Exception {
        when(this.foodieService.getAllCusine()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/cusine");
        MockMvcBuilders.standaloneSetup(this.foodieController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<ArrayList/>"));
    }

    @Test
    void testGetAllCusine2() throws Exception {
        when(this.foodieService.getAllCusine()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/v1/cusine");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(this.foodieController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<ArrayList/>"));
    }

    @Test
    void testGetAllCusinesFromACity2() throws Exception {
        when(this.foodieService.getAllCusine()).thenReturn(new ArrayList<>());
        when(this.foodieService.getAllCusineByCity((String) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/cusine/{city}", "", "Uri Vars");
        MockMvcBuilders.standaloneSetup(this.foodieController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<ArrayList/>"));
    }

    @Test
    void testAddCusineToCart() throws Exception {
        CartDTO cartDTO = new CartDTO();
        cartDTO.setCusineID(1);
        cartDTO.setEmail("jane.doe@example.org");
        when(this.foodieService.addCusineToCart((String) any(), anyInt())).thenReturn(cartDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/cart/{email}/{id}",
                "jane.doe@example.org", 1);
        MockMvcBuilders.standaloneSetup(this.foodieController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("<CartDTO><email>jane.doe@example.org</email><cusineID>1</cusineID></CartDTO>"));
    }

    @Test
    void testAddCusineToFavourite() throws Exception {
        FavouriteDTO favouriteDTO = new FavouriteDTO();
        favouriteDTO.setCusineID(1);
        favouriteDTO.setEmail("jane.doe@example.org");
        favouriteDTO.setResturantID(1);
        when(this.foodieService.addCusineToFavourite((String) any(), anyInt())).thenReturn(favouriteDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/cusine/{email}/{cusineID}",
                "jane.doe@example.org", 1);
        MockMvcBuilders.standaloneSetup(this.foodieController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "<FavouriteDTO><email>jane.doe@example.org</email><cusineID>1</cusineID><resturantID>1</resturantID><"
                                        + "/FavouriteDTO>"));
    }

    @Test
    void testAddRestaurantToFavourite() throws Exception {
        FavouriteDTO favouriteDTO = new FavouriteDTO();
        favouriteDTO.setCusineID(1);
        favouriteDTO.setEmail("jane.doe@example.org");
        favouriteDTO.setResturantID(1);
        when(this.foodieService.addRestaurantToFavourite((String) any(), anyInt())).thenReturn(favouriteDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/v1/restaurant/{email}/{restaurantID}", "jane.doe@example.org", 1);
        MockMvcBuilders.standaloneSetup(this.foodieController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "<FavouriteDTO><email>jane.doe@example.org</email><cusineID>1</cusineID><resturantID>1</resturantID><"
                                        + "/FavouriteDTO>"));
    }

    @Test
    void testGetAllCusinesFromACity() throws Exception {
        when(this.foodieService.getAllCusineByCity((String) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/cusine/{city}", "Oxford");
        MockMvcBuilders.standaloneSetup(this.foodieController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<ArrayList/>"));
    }

    @Test
    void testGetAllCusinesFromName() throws Exception {
        when(this.foodieService.getCusineByName((String) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/cusine/all/{name}", "Name");
        MockMvcBuilders.standaloneSetup(this.foodieController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<ArrayList/>"));
    }

    @Test
    void testGetAllCusinesFromName2() throws Exception {
        when(this.foodieService.getAllCusineByCity((String) any())).thenReturn(new ArrayList<>());
        when(this.foodieService.getCusineByName((String) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/cusine/all/{name}", "",
                "Uri Vars");
        MockMvcBuilders.standaloneSetup(this.foodieController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<ArrayList/>"));
    }

    @Test
    void testGetAllRestaurantFromACity() throws Exception {
        when(this.foodieService.getAllRestaurantByCity((String) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/restaurant/{city}", "Oxford");
        MockMvcBuilders.standaloneSetup(this.foodieController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<ArrayList/>"));
    }

    @Test
    void testGetAllRestaurantFromACity2() throws Exception {
        when(this.foodieService.getAllRestaurantByCity((String) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/v1/restaurant/{city}", "Oxford");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(this.foodieController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("<ArrayList/>"));
    }

    @Test
    void testGetUser() throws Exception {
        User user = new User();
        user.setAccountType("3");
        user.setEmail("jane.doe@example.org");
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setPhoneNo("4105551212");
        user.setProfileImage("Profile Image");
        when(this.foodieService.getUserByID((String) any())).thenReturn(user);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/users/{email}",
                "jane.doe@example.org");
        MockMvcBuilders.standaloneSetup(this.foodieController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("<User><email>jane.doe@example.org</email><password>iloveyou</password><name>Name</name><phoneNo"
                                + ">4105551212</phoneNo><accountType>3</accountType><profileImage>Profile Image</profileImage></User>"));
    }

//    @Test
//    void testGetUser2() throws Exception {
//        when(this.foodieService.getUserByID((String) any())).thenThrow(new UserNotFoundException());
//        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/users/{email}",
//                "jane.doe@example.org");
//        MockMvcBuilders.standaloneSetup(this.foodieController)
//                .build()
//                .perform(requestBuilder)
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
//                .andExpect(MockMvcResultMatchers.content()
//                        .string("<User><email>jane.doe@example.org</email><password>iloveyou</password><name>Name</name><phoneNo"
//                                + ">4105551212</phoneNo><accountType>3</accountType><profileImage>Profile Image</profileImage></User>"));
//    }

    @Test
    void testGetUserImage() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/users/image/{name}", "Name");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.foodieController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(500));
    }

    @Test
    void testGetUserImage2() throws Exception {
        User user = new User();
        user.setAccountType("3");
        user.setEmail("jane.doe@example.org");
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setPhoneNo("4105551212");
        user.setProfileImage("Profile Image");
        when(this.foodieService.getUserByID((String) any())).thenReturn(user);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/users/image/{name}", "",
                "Uri Vars");
        MockMvcBuilders.standaloneSetup(this.foodieController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("<User><email>jane.doe@example.org</email><password>iloveyou</password><name>Name</name><phoneNo"
                                + ">4105551212</phoneNo><accountType>3</accountType><profileImage>Profile Image</profileImage></User>"));
    }

    @Test
    void testGetUserImage3() throws Exception {
        when(this.foodieService.getUserByID((String) any())).thenThrow(new UserNotFoundException());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/users/image/{name}", "",
                "Uri Vars");
        MockMvcBuilders.standaloneSetup(this.foodieController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/xml;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("<User><email>jane.doe@example.org</email><password>iloveyou</password><name>Name</name><phoneNo"
                                + ">4105551212</phoneNo><accountType>3</accountType><profileImage>Profile Image</profileImage></User>"));
    }
}

