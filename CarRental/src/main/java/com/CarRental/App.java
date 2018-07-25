package com.CarRental;




import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.model.Car;
import com.service.CarRestService;

@SpringBootApplication
@ComponentScan("com.service")
public class App implements CommandLineRunner{
	
	private static final Logger log=LoggerFactory.getLogger(App.class);
	
	
	@Autowired
	private CarRestService crs;

    public static void main(String[] args) {
       SpringApplication.run(App.class, args);
    }

    
	@Override
	public void run(String... arg0) throws Exception {
		
		//Question 1
    	List<Car> cars1=crs.selectBlueTesla();
    	for(Car r:cars1) {
    		System.out.println("Blue color Tesla  "+r.toString());
    	}
		
        
        //Question 2
    	List<Car> cars2=crs.selectLowRentalCar();
    	for(Car r:cars2) {
    		System.out.println("Cars with the lowest Price before discount  "+r.toString());
    	}
    	
    	List<Car> cars3=crs.selectLowRentalCarDiscount();
    	for(Car r:cars3) {
    		System.out.println("Cars with the lowest Price after discount  "+r.toString());
    	}
		// Qustion 3
    	List<Car> cars4=crs.selectHighestRevCar();
    	for(Car r:cars4) {
    		System.out.println("Cars with the highest revenue  "+r.toString());
    	}
	}

}