package com.stackroute.FoodieService;

import com.stackroute.FoodieService.exception.DirectoryAlreadyExistException;
import com.stackroute.FoodieService.service.FileSaveService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import javax.annotation.Resource;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class FoodieServiceApplication implements CommandLineRunner {
	@Resource
	FileSaveService fileSaveService;

	public static void main(String[] args) {
		SpringApplication.run(FoodieServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//fileSaveService.deleteAll();
		try{
			fileSaveService.init();
		}
		catch(DirectoryAlreadyExistException e)
		{
			e.printStackTrace();
		}
	}
}
