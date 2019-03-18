package org_src_DatadogRoutingJava;

import java.util.ArrayList;

/**
 * A tuple class to more-easier pass data around
 * @author Vytenis Sliogeris
 *
 */
public class Tuple {
	public int Beers;
	public ArrayList<Integer> Path;
	public Tuple(int beers, ArrayList<Integer> path) {
		this.Beers = beers;
		this.Path = path;
	}
}
