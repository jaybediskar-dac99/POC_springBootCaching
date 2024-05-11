package com.example.cachingdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com.example.cachingdemo")
@EnableJpaRepositories("com.example.cachingdemo")
@EntityScan("com.example.cachingdemo")
@EnableCaching
@EnableAspectJAutoProxy
public class CachingdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CachingdemoApplication.class, args);
	}

}
