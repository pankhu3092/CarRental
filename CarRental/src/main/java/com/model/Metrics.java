package com.model;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Metrics {

	
	private float yoymaintenancecost;
	private float depreciation;
	private int lastweekRentalCount;
	private int yeartodateRentalCount;
	
	public Metrics() {
	
	}
	
	

	public Metrics(float yoymaintenancecost, float depreciation, int lastweekRentalCount, int yeartodateRentalCount) {
		this.yoymaintenancecost = yoymaintenancecost;
		this.depreciation = depreciation;
		this.lastweekRentalCount = lastweekRentalCount;
		this.yeartodateRentalCount = yeartodateRentalCount;
	}



	public float getYoymaintenancecost() {
		return yoymaintenancecost;
	}

	public void setYoymaintenancecost(float yoymaintenancecost) {
		this.yoymaintenancecost = yoymaintenancecost;
	}

	public float getDepreciation() {
		return depreciation;
	}

	public void setDepreciation(float depreciation) {
		this.depreciation = depreciation;
	}


	@JsonProperty("lastweek")
	public int getLastweekRentalCount() {
		return lastweekRentalCount;
	}



	public void setLastweekRentalCount(int lastweekRentalCount) {
		this.lastweekRentalCount = lastweekRentalCount;
	}


	@JsonProperty("yeartodate")
	public int getYeartodateRentalCount() {
		return yeartodateRentalCount;
	}



	public void setYeartodateRentalCount(int yeartodateRentalCount) {
		this.yeartodateRentalCount = yeartodateRentalCount;
	}

	@JsonProperty("rentalcount")
    private void unpackNestedPerDayRent(Map<String,Integer> rentalcount) {
        this.lastweekRentalCount = rentalcount.get("lastweek");
       this.yeartodateRentalCount=rentalcount.get("yeartodate");
    }
	
	
	
	
}
