//package com.stackroute.FoodieService.config;
//
//import org.springframework.beans.factory.ObjectFactory;
//import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
//import org.springframework.cloud.openfeign.support.SpringEncoder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.converter.HttpMessageConverter;
//import org.springframework.http.converter.cbor.MappingJackson2CborHttpMessageConverter;
//
//@Configuration
//public class FeignClientConfiguration {
//    @Bean
//    public SpringEncoder feignEncoder()
//    {
//        HttpMessageConverter jacksonConverter=new MappingJackson2CborHttpMessageConverter();
//        ObjectFactory<HttpMessageConverters> objectFactory=()-> new HttpMessageConverters(jacksonConverter);
//        return new SpringEncoder(objectFactory);
//    }
//}
