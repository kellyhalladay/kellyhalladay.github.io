package com.techelevator.npgeek.model;

import java.math.BigDecimal;

public class Park {
	
	private String parkCode;
	private String parkName;
	private String state;
	private Integer acreage;
	private Integer elevationInFeet;
	private Double milesOfTrail;
	private Integer numberOfCampsites;
	private String climate;
	private Integer yearFounded;
	private Integer annualVisitorCount;
	private String inspirationalQuote;
	private String inspirationalQuoteSource;
	private String parkDescription;
	private BigDecimal entryFee;
	private Integer numberOfAnimalSpecies;
	
	public void setParkCode(String parkCode) {
		this.parkCode = parkCode;
	}
	public void setParkName(String parkName) {
		this.parkName = parkName;
	}
	public void setState(String state) {
		this.state = state;
	}
	public void setAcreage(Integer acreage) {
		this.acreage = acreage;
	}
	public void setElevationInFeet(Integer elevationInFeet) {
		this.elevationInFeet = elevationInFeet;
	}
	public void setMilesOfTrail(Double milesOfTrail) {
		this.milesOfTrail = milesOfTrail;
	}
	public void setNumberOfCampsites(Integer numberOfCampsites) {
		this.numberOfCampsites = numberOfCampsites;
	}
	public void setClimate(String climate) {
		this.climate = climate;
	}
	public void setYearFounded(Integer yearFounded) {
		this.yearFounded = yearFounded;
	}
	public void setAnnualVisitorCount(Integer annualVisitorCount) {
		this.annualVisitorCount = annualVisitorCount;
	}
	public void setInspirationalQuote(String inspirationalQuote) {
		this.inspirationalQuote = inspirationalQuote;
	}
	public void setInspirationalQuoteSource(String inspirationalQuoteSource) {
		this.inspirationalQuoteSource = inspirationalQuoteSource;
	}
	public void setParkDescription(String parkDescription) {
		this.parkDescription = parkDescription;
	}
	public void setEntryFee(BigDecimal entryFee) {
		this.entryFee = entryFee;
	}
	public void setNumberOfAnimalSpecies(Integer numberOfAnimalSpecies) {
		this.numberOfAnimalSpecies = numberOfAnimalSpecies;
	}	
	public String getParkCode() {
		return parkCode;
	}
	public String getParkName() {
		return parkName;
	}
	public String getState() {
		return state;
	}
	public Integer getAcreage() {
		return acreage;
	}
	public Integer getElevationInFeet() {
		return elevationInFeet;
	}
	public Double getMilesOfTrail() {
		return milesOfTrail;
	}
	public Integer getNumberOfCampsites() {
		return numberOfCampsites;
	}
	public String getClimate() {
		return climate;
	}
	public Integer getYearFounded() {
		return yearFounded;
	}
	public Integer getAnnualVisitorCount() {
		return annualVisitorCount;
	}
	public String getInspirationalQuote() {
		return inspirationalQuote;
	}
	public String getInspirationalQuoteSource() {
		return inspirationalQuoteSource;
	}
	public String getParkDescription() {
		return parkDescription;
	}
	public BigDecimal getEntryFee() {
		return entryFee;
	}
	public Integer getNumberOfAnimalSpecies() {
		return numberOfAnimalSpecies;
	}

}
