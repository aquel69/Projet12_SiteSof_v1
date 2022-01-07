package com.sof.interface_site;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.sof.interface_site")
public class InterfaceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InterfaceApplication.class, args);
	}

}
