package com.test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan({"com.test", "com.github.xjs.ezprofiler"})
public class MainApplication {
	
    public static void main(String[] args) throws Exception {
       	SpringApplication.run(MainApplication.class, args);
    }
   
}