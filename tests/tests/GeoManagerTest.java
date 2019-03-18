package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import org_src_DatadogRoutingJava.GeoManager;

class GeoManagerTest {

	@Test
	void mathTest() {
		assertEquals(GeoManager.calcDist(13.045,23.110,150.022,132.582), 7426.853307659911);
	}

}
