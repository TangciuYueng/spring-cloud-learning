package com.aaabbb.orderservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
public class OrderServiceApplication {

	public static void main(String[] args) throws UnknownHostException {
		SpringApplication.run(OrderServiceApplication.class, args);
		InetAddress localhost = InetAddress.getLocalHost();
		System.out.println(localhost.getHostAddress());
	}
}
