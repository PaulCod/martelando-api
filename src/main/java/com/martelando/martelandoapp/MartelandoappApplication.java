package com.martelando.martelandoapp;

import io.jsonwebtoken.Jwts;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.crypto.SecretKey;
import java.util.Base64;

@SpringBootApplication
public class MartelandoappApplication {

	public static void main(String[] args) {
		SpringApplication.run(MartelandoappApplication.class, args);
	}

}
