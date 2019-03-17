/**
 * RoutingMain.java
 */

package org_src_DatadogRoutingJava;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
/**
 * 
 * Main class for the program
 * 
 * @author Vytenis Šliogeirs
 *
 */

public class RoutingMain {
	
	public static void main(String[] args) {
		System.out.println("Hello World");
		
		CSVReader reader = new CSVReader();
		String[][] beers = null;
		String[][] geocodes = null;
		String[][] breweries = null;
		try {
			beers = reader.parseCSV("dumps/beers.csv");
			geocodes = reader.parseCSV("dumps/geocodes.csv");
			breweries = reader.parseCSV("dumps/breweries.csv");
		} catch (IOException e) {
			// TODO Auto-generated catch block <3
			e.printStackTrace();
		}
		/**
		 * Used a hashmap to avoid quadratic times for assigning latitudes and longitudes to the breweries
		 * and to also avoid empty entries.
		 */
		HashMap<Integer, Location> locations = new HashMap<Integer, Location>();
		for(int i = 0; i < breweries.length; i++) {
			int id = Integer.parseInt(breweries[i][0]);
			String name = breweries[i][1];
			locations.put(id, new Location(name));
		}
		
		/**
		 * This function would perform in quadratic time if I used arbitrary ID's
		 */
		for(int i = 0; i < geocodes.length; i++) {
			int id = Integer.parseInt(geocodes[i][1]);
			double lat = Double.parseDouble(geocodes[i][2]);
			double lon = Double.parseDouble(geocodes[i][3]);
			locations.get(id).setLatLong(lat, lon);
		}
		
		for(int i = 0; i < beers.length; i++) {
			String beerName = beers[i][2];
			int id = Integer.parseInt(beers[i][1]);
			locations.get(id).addBeer(beerName);
		}
		
		System.out.println("Terminating!");
	}
}