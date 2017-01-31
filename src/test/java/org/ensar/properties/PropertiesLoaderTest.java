package org.ensar.properties;

import static org.junit.Assert.assertEquals;

import org.ensar.properties.resources.ConstatntsInterfaceNoSource;
import org.junit.Before;
import org.junit.Test;

public class PropertiesLoaderTest {

	ConstatntsInterfaceNoSource constants;

	@Before
	public void setup() {
		constants = PropertiesLoader.ofNoSource(ConstatntsInterfaceNoSource.class);
	}

	@Test
	public void testOfNoSourceGivenValueReturnConstant() {
		String actualName = "Ensar";
		String actualSecondname = "Bavrk";

		assertEquals(constants.name(), actualName);
		assertEquals(constants.secondname(), actualSecondname);
	}

	@Test(expected = PropertieLoaderException.class)
	public void testOnInterfaceOfNosourceKeyAnnotationShouldThrowException() {
		assertEquals("someExpectedValue", constants.expectedException());
	}

	@Test
	public void testOfNoSourceForDefaultValue() {
		String actualValue = "nesto";
		assertEquals(constants.nesto(), actualValue);
	}

}
