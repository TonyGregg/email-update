package com.genil.apps.contacts.emailupdate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients("com.genil.apps.contacts.emailupdate.proxies")
public class EmailUpdateApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmailUpdateApplication.class, args);
	}

}
