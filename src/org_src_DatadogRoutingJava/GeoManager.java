package org_src_DatadogRoutingJava;


/**
 * A class to keep all of the coordinate mathematics functions in
 * @author Vytenis Sliogeris
 *
 */
public class GeoManager {
	public static double toRadians(double deg) {
		return deg*Math.PI/180;
	}
	
	public static double calcDist(double lat1, double lon1, double lat2, double lon2) {
		double dist = 0;
		double lat1R = toRadians(lat1);
		double lon1R = toRadians(lon1);
		double lat2R = toRadians(lat2);
		double lon2R = toRadians(lon2);
		
		double a = Math.pow(Math.sin((lat2R-lat1R)/2),2) + Math.cos(lat1R) * Math.cos(lat2R)*Math.pow(Math.sin((lon2R-lon1R)/2),2);
		double c = 2 * Math.atan2(Math.sqrt(a),Math.sqrt(1-a));
		double R = 6371.0;
		dist = R*c;
		return dist;
	}
}
