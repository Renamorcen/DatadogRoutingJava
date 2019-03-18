package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import org_src_DatadogRoutingJava.Tuple;

class TupleTest {

	@Test
	void constructorTest() {
		Tuple tuple = new Tuple(0, new ArrayList<Integer>());
		assertNotNull(tuple);
	}
	@Test
	void variableTest() {
		ArrayList<Integer> al = new ArrayList<Integer>();
		al.add(1);
		al.add(2);
		Tuple tuple = new Tuple(0, al);
		assertEquals(0, tuple.Beers);
		int first = tuple.Path.get(0);
		int second = tuple.Path.get(1);
		assertEquals(1, first);
		assertEquals(2, second);
	}

}
