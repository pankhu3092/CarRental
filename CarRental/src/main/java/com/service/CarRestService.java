package com.service;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.Car;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;


@Service
public class CarRestService {
	

	    public List<Car> getCars() {
	    	
	    	//using object Mapper to read json and store in car list
	    	ObjectMapper objectMapper = new ObjectMapper();
	        List<Car> cars = null;
			try {
				cars = objectMapper.readValue(new File("src/main/resources/car.json"),new TypeReference<List<Car>>(){});
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return cars;
	    	
	    }

	
	    //Question 1: Print all the blue Teslas received in the web service response. Also print the notes
	    public List<Car> selectBlueTesla() throws IOException{
	    	
	    	List<Car> cars=getCars();
	    	List<Car> resultList=new ArrayList<Car>();
	    	if(cars.size()!=0) {
	    	for(Car c:cars) {
	    		
	    		if(c.getMake().equals("Tesla") && c.getColor().equals("Blue")) {
	    			resultList.add(c);
    		      }
	    		}
	    	}
	    	return resultList;
	    }
	    
//	  //   Question 2: Return all cars which have the lowest per day rental cost for both cases:
//		a. Price only

	    public List<Car> selectLowRentalCar() throws IOException{
	    	
	    	List<Car> cars=getCars();
	    	TreeMap<Float, List<Car>> map= new TreeMap<Float, List<Car>>();
	    	
	    	if(cars.size()!=0) {
	    	for(Car c:cars) {
	    		//without discount
	    		if(map.get(c.getPrice())!=null) {
	    			List<Car> samValueCar=map.get(c.getPrice());
	    			samValueCar.add(c);
	    			map.put(c.getPrice(),samValueCar);
	    			
	    		}
	    		else {
	    			List<Car> samValueCar=new ArrayList<Car>();
	    			samValueCar.add(c);
	    		map.put(c.getPrice(), samValueCar);
	    		}
	          }
             }
	    	//Cars with the lowest rent Price
	    	return map.firstEntry().getValue();
	    }
	    
	    
	    //b. Price after discounts
	    
	    public List<Car> selectLowRentalCarDiscount() throws IOException{
	    	
	    	List<Car> cars=getCars();
	    	TreeMap<Float, List<Car>> mapD= new TreeMap<Float, List<Car>>();
	    	
	    	if(cars.size()!=0) {
	    	for(Car c:cars) {
	    		
	    		//with discount
	    		float discountPrice=(100-c.getDiscount())*c.getPrice()/100;
	    		
	    		if(mapD.get(discountPrice)!=null) {
	    			List<Car> samValueCar=mapD.get(discountPrice);
	    			samValueCar.add(c);
	    			mapD.put(discountPrice,samValueCar);
	    			
	    		}
	    		else {
	    			List<Car> samValueCar=new ArrayList<Car>();
	    			samValueCar.add(c);
	    		   mapD.put(discountPrice, samValueCar);
	    		}
	    	}
	    }

	    	//Cars with the lowest Price
	    	return mapD.firstEntry().getValue();
	    	
	    }
	
	    // Question 3: Find the highest revenue generating car. year over year maintenance cost + depreciation is the total expense per car for the full year for the rental car company.
		
        public List<Car> selectHighestRevCar() throws IOException{	
	    	List<Car> cars=getCars();
	    	TreeMap<Float, List<Car>> map= new TreeMap<Float, List<Car>>();
	  
	    	if(cars.size()!=0) {
	    	for(Car c:cars) {
	    		
	    		float discountPrice=(100-c.getDiscount())*c.getPrice()/100;
	    		float profit=(c.getMetrics().getYeartodateRentalCount()*discountPrice)-(c.getMetrics().getYoymaintenancecost()+c.getMetrics().getDepreciation());
	    		
	    		if(map.get(profit)!=null) {
	    			List<Car> samValueCar=map.get(profit);
	    			samValueCar.add(c);
	    			map.put(profit,samValueCar);
	    		}
	    		else {
	    			List<Car> samValueCar=new ArrayList<Car>();
	    			samValueCar.add(c);
	    		   map.put(profit, samValueCar);
	    		}
	    	}	
	     }
	    	return map.lastEntry().getValue();
      }
	
}
