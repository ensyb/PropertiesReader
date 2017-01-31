package org.ensar.properties.resources;

import org.ensar.properties.meta.DefaultValue;
import org.ensar.properties.meta.IConstants;
import org.ensar.properties.meta.Key;
import org.ensar.properties.meta.Source;

@Source("org/ensar/properties/resources/testProperties.properties")
public interface ConstantInterfaceWithSource extends IConstants {

	@Key("first")
	@DefaultValue("defaultFirstValue")
	public String firstProperty();
	
	@Key("second")
	@DefaultValue("defaultSecondValue")
	public String secondProperty();
	
	@Key("third")
	@DefaultValue("defaultThirdValue")
	public String thirdProperty();
	
	@DefaultValue("oneConstant")
	public String someOtherConstant();
	
	@Key("noValueForKey")
	public String noDefaultValue();
	
}
