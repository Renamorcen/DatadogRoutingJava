package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import org_src_DatadogRoutingJava.Location;

class LocationTest {

	@Test
	void constructorTest() {
		Location location = new Location (1, 123.0, 132.0);
		assertNotNull(location);
		location.setName("asd");
		assertTrue("asd".equals(location.getName()));
	}

}
