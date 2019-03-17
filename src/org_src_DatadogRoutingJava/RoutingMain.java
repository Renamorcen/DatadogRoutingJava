/**
 * RoutingMain.java
 */

package org_src_DatadogRoutingJava;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
		
		
		
		
		System.out.println("Terminating!");
	}

}
