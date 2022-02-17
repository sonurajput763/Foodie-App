package com.stackroute.vendorService;

import com.stackroute.vendorService.exception.DirectoryAlreadyExistException;
import com.stackroute.vendorService.service.FileSaveService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import javax.annotation.Resource;

@SpringBootApplication
@EnableEurekaClient
public class VendorServiceApplication implements CommandLineRunner {

	@Resource
	FileSaveService fileSaveService;

	public static void main(String[] args) {
		SpringApplication.run(VendorServiceApplication.class, args);
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
