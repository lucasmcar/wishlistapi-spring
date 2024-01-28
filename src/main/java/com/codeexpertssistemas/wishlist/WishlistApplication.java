package com.codeexpertssistemas.wishlist;

import com.codeexpertssistemas.wishlist.model.Wishlist;
import com.codeexpertssistemas.wishlist.repository.WishlistRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Collections;

@SpringBootApplication
public class WishlistApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(WishlistApplication.class);
		app.setDefaultProperties(Collections.singletonMap(
				"server.port", "8000"
		));
		app.run( args);
	}



}
