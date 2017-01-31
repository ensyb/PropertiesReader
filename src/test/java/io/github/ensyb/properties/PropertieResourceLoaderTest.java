package io.github.ensyb.properties;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PropertieResourceLoaderTest {

	private static final String SOURCE = "TestProps.properties";

	@Test
	public void testLoadStringFromFile() {
		String realValue = "firstValue";
		String key = "first";
		assertEquals(realValue, PropertieResourceLoader.getPropertie(key, SOURCE).get());
	}

}
