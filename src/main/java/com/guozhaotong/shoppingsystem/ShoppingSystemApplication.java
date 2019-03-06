package com.guozhaotong.shoppingsystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
@MapperScan("com.guozhaotong.shoppingsystem.mapper")
public class ShoppingSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppingSystemApplication.class, args);
	}

}
