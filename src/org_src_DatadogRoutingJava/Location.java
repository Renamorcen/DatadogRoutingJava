/**
 * Location.java
 */
package org_src_DatadogRoutingJava;

/**
 * 
 * Class representing each brewery with it's location and beers.
 * @author Vytenis Sliogeris
 *
 */
public class Location {
	
	private String name;
	private String[] beers = new String[0];
	private double lon;
	private double lat;
	
	/**
	 * Constructor for the breweries file.
	 * @param id
	 * @param name
	 */
	public Location(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	/**
	 * Method for adding a beer.
	 * @param beername
	 */
	public void addBeer(String beerName) {
		String[] beersNew = new String[1+beers.length];
		for(int i = 0; i < beers.length; i++) {
			beersNew[i] = beers[i];
		}
		beersNew[beersNew.length-1] = beerName;
		beers = beersNew;
	}
	/**
	 * Mutator for the geocodes file
	 * @param lat
	 * @param lon
	 */
	public void setLatLong(double lat, double lon) {
		this.lat = lat;
		this.lon = lon;
	}
	
}
