package io.github.ensyb.properties;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import io.github.ensyb.properties.PropertieLoaderException;
import io.github.ensyb.properties.PropertiesLoader;
import io.github.ensyb.properties.constants.ConstantInterfaceWithSource;

public class PropertiesLoaderSourceTest {

	ConstantInterfaceWithSource constants;

	@Before
	public void setup() {
		constants = PropertiesLoader.of(ConstantInterfaceWithSource.class);
	}

	@Test
	public void testLoadValueFromProertiesFile() {
		String realFirstValue = "firstValue";
		String realSecondValue = "secondValue";

		assertEquals(realFirstValue, constants.firstProperty());
		assertEquals(realSecondValue, constants.secondProperty());
	}

	@Test
	public void testDefaultValueIfValueInFileIsNotPresent() {
		String realValue = "defaultThirdValue";

		assertEquals(realValue, constants.thirdProperty());
	}

	@Test(expected = PropertieLoaderException.class)
	public void testNoValueForKeySpecified() {
		String realvalue = "";

		assertEquals(realvalue, constants.noDefaultValue());
	}

	@Test
	public void testDefaultValueIfKeyIsNotPresent() {
		String realvalue = "oneConstant";

		assertEquals(realvalue, constants.someOtherConstant());
	}
}
