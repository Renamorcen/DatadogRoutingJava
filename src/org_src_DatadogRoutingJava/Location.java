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
	private int ID;
	

	/**
	 * Constructor, as all breweries must have a location (for our purposes)
	 * @param lat
	 * @param lon
	 */
	public Location(int ID, double lat, double lon) {
		this.lat = lat;
		this.lon = lon;
		this.ID = ID;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public double getLat() {
		return this.lat;
	}
	public double getLon() {
		return this.lon;
	}
	public int getID() {
		return this.ID;
	}
	public int getBeerCount() {
		return this.beers.length;
	}
	public String[] getBeers() {
		return this.beers;
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
	 * Helper method to get distance between locations
	 * @param dest
	 * @return
	 */
	public double calcDist(Location dest) {
		double destLat = dest.getLat();
		double destLon = dest.getLon();
		return GeoManager.calcDist(lat, lon, destLat, destLon);
	}
}
