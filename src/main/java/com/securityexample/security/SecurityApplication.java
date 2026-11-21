package com.securityexample.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SecurityApplication {

	public static void main(String[] args) {
        System.out.println("user password:"+ new BCryptPasswordEncoder().encode("sifra123"));
        System.out.println("admin password:"+ new BCryptPasswordEncoder().encode("sifra"));
        SpringApplication.run(SecurityApplication.class, args);
	}

}
