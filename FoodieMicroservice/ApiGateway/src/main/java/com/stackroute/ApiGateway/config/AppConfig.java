package com.stackroute.ApiGateway.config;

import com.stackroute.ApiGateway.filter.CustomerJwtFilter;
import com.stackroute.ApiGateway.filter.VendorJwtFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder)
    {
        return builder.routes()
                .route(p->p
                        .path("/api/v4/**")
                        .uri("http://localhost:9001"))//cartService
                .route(p->p
                        .path("/api/v5/**")
                        .uri("http://localhost:9002"))//favouriteService
                .route(p->p
                        .path("/api/v1/**")
                        .uri("http://localhost:9003"))//foodieService
                .route(p->p
                        .path("/api/v3/**")
                        .uri("http://localhost:9010"))//userAuthenticationService
                .route(p->p
                        .path("/api/v2/**")
                        .uri("http://localhost:9005"))//vendorService
                .build();
    }
    @Bean
    public FilterRegistrationBean customerJwtFilter()
    {
        FilterRegistrationBean filterRegistrationBean=new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new CustomerJwtFilter());
        filterRegistrationBean.addUrlPatterns("/api/v4/**","/api/v5/**");
        return filterRegistrationBean;
    }
    @Bean
    public FilterRegistrationBean VendorJwtFilter()
    {
        FilterRegistrationBean filterRegistrationBean=new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new VendorJwtFilter());
        filterRegistrationBean.addUrlPatterns("/api/v2/**");
        return filterRegistrationBean;
    }
}
