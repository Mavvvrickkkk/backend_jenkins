package com.klef.fsd.sdp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer; // <-- ADD THIS IMPORT

@SpringBootApplication
public class ManiProjectApplication extends SpringBootServletInitializer { // <-- EXTEND THIS CLASS

	public static void main(String[] args) {
		SpringApplication.run(ManiProjectApplication.class, args);
		System.out.println("Mani tej Project is Runnning Successfully !!...");
	}
	
	@Override // <-- ADD THIS ENTIRE METHOD
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ManiProjectApplication.class);
    }
}
