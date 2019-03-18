/**
 * RoutingMain.java
 */

package org_src_DatadogRoutingJava;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
/**
 * 
 * Main class for the program
 * 
 * @author Vytenis Šliogeirs
 *
 */

public class RoutingMain {
	/**
	 * A method to create a deep copy of a hash map, as there seems to not be the functionality built in.
	 * Also added in the functionality to remove some entry while at it.
	 * @param in
	 * @param toRemove
	 * @return
	 */
	public static HashMap<Integer, Location> deepCopyHashMap(HashMap<Integer, Location> in, Location toRemove){
		HashMap<Integer, Location> out = new HashMap<Integer, Location>();
		Set<Integer> IDs = in.keySet();
		Iterator<Integer> IDterator = IDs.iterator();
		while(IDterator.hasNext()) {
			int ID = IDterator.next();
			if(ID != toRemove.getID()) {
				out.put(ID, in.get(ID));
				}
		}
		return out;
	}
	/**
	 * A deep copy method for the array list for the same reasons as stated before
	 * @param in
	 * @return
	 */
	public static ArrayList<Integer> deepCopyArrayList(ArrayList<Integer> in){
		ArrayList<Integer> out = new ArrayList<Integer>();

		for(int i = 0; i < in.size(); i++) {
			out.add(in.get(i));
		}
		
		return out;
	}
	/**
	 * The recursive solution, same as in the Golang code.
	 * Seems to not want to terminate.
	 * @param currentPos
	 * @param home
	 * @param locations
	 * @param path
	 * @param beers
	 * @param fuel
	 * @return
	 */
	public static Tuple recursiveSoln(Location currentPos, Location home, HashMap<Integer, Location> locations, ArrayList<Integer> path, int beers, double fuel) {
		ArrayList<Integer> outputPath = deepCopyArrayList(path);
		ArrayList<Integer> bestPath = deepCopyArrayList(path);
		int bestBeers = beers;
		Tuple output = null;
		if(currentPos.calcDist(home) > fuel || fuel < 0) {
			return new Tuple(beers, path);
		}	
		outputPath.add(currentPos.getID());
		int currentBeers = beers + currentPos.getBeerCount();
		
		System.out.println(path.size() + " and pubs remaining " + locations.size() + " and beers " + beers + " and fuel " + fuel);
		Set<Integer> IDs = locations.keySet();
		Iterator<Integer> IDterator = IDs.iterator();
		while(IDterator.hasNext()) {
			int key = IDterator.next();
			HashMap<Integer, Location> locationsThere = deepCopyHashMap(locations, currentPos);
			Location considered = locations.get(key);
			double fuelthere = fuel-currentPos.calcDist(considered);
			Tuple alternativePath = recursiveSoln(considered, home, locationsThere, outputPath, currentBeers, fuelthere);
			if(bestBeers < alternativePath.Beers) {
				bestPath = alternativePath.Path;
				bestBeers = alternativePath.Beers;
			}
		}
		output = new Tuple(bestBeers, bestPath);
		
		return output;
	}
	
	/**
	 * A method to lower the number of breweries to just the 1000km radius, to lower the input.
	 * @param home
	 * @param globe
	 * @return
	 */
	public static HashMap<Integer, Location> getNeighbourhood(Location home, HashMap<Integer, Location> globe){
		HashMap<Integer, Location> output = new HashMap<Integer, Location>();
		Set<Integer> IDs = globe.keySet();
		Iterator<Integer> IDterator = IDs.iterator();
		while(IDterator.hasNext()) {
			Integer ID = IDterator.next();
			if(home.calcDist(globe.get(ID))<1000) {
				output.put(ID, globe.get(ID));
			}
		}
		return output;
	}
	
	/**
	 * Wrapper class for the recursive method
	 * @param lat
	 * @param lon
	 * @param locations
	 * @return
	 */
	public static int[] findPath(double lat, double lon, HashMap<Integer, Location> locations) {
		double fuel = 2000.0;
		Location[] optimalPath = new Location[0];
		String[] beers = new String[0];
		Location home = new Location(-1, lat, lon);
		home.setName("Home");
		HashMap<Integer, Location> neighbourhood = getNeighbourhood(home, locations);
		Tuple pathArrayListTuple = recursiveSoln(home, home, neighbourhood, new ArrayList<Integer>(), 0, fuel);
		ArrayList<Integer> pathArrayList = pathArrayListTuple.Path;
		int[] path = new int[pathArrayList.size()];
		for(int i = 0; i < pathArrayList.size(); i++) {
			path[i] = pathArrayList.get(i);
		}
		return path;
	}

	
	public static void main(String[] args) {
		System.out.println("Hello World");
		
		CSVReader reader = new CSVReader();
		String[][] beers = null;
		String[][] geocodes = null;
		String[][] breweries = null;
		try {
			breweries = reader.parseCSV("dumps/breweries.csv");
			beers = reader.parseCSV("dumps/beers.csv");
			geocodes = reader.parseCSV("dumps/geocodes.csv");
		} catch (IOException e) {
			System.out.println("Failed");
			// TODO Auto-generated catch block <3
			e.printStackTrace();
		}
		
		/**
		 * Used a hashmap to avoid quadratic times for assigning latitudes and longitudes to the breweries
		 * and to also avoid empty entries.
		 */
		HashMap<Integer, Location> locations = new HashMap<Integer, Location>();
		/**
		 * This function would perform in quadratic time if I used arbitrary ID's. Checks if the brewery even exists at that location.
		 */
		for(int i = 0; i < geocodes.length; i++) {
			int id = Integer.parseInt(geocodes[i][1]);
			double lat = Double.parseDouble(geocodes[i][2]);
			double lon = Double.parseDouble(geocodes[i][3]);
			locations.put(id, new Location(id, lat, lon));
		}
		
		for(int i = 0; i < breweries.length; i++) {
			int id = Integer.parseInt(breweries[i][0]);
			String name = breweries[i][1];
			if(locations.get(id)!=null) {
				locations.get(id).setName(name);
			}
		}
		
		for(int i = 0; i < beers.length; i++) {
			String beerName = beers[i][2];
			int id = Integer.parseInt(beers[i][1]);
			if(locations.get(id)!=null) {
				locations.get(id).addBeer(beerName);
			}
		}
		
		
		/**
		 * You can replace with any other method of inputting coordinates
		 */
		/*
		System.out.println("Please post your Latitude and Longitude in the for of Lat/Lon");
		Scanner scanner = new Scanner(System.in); 
		String input = scanner.nextLine();
		String[] coords = input.split("/");
		double lat = Double.parseDouble(coords[0]);
		double lon = Double.parseDouble(coords[1]);
		*/
		double lon = 19.43295600;
		double lat = 51.74250300;
		int[] path = findPath(lat, lon, locations);
		for(int i = 0; i < path.length; i++) {
			System.out.print(path[i] + " ");
		}
		System.out.println("Terminating!");
	}
}