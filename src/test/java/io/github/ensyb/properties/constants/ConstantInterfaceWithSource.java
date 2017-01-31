package io.github.ensyb.properties.constants;

import io.github.ensyb.properties.meta.DefaultValue;
import io.github.ensyb.properties.meta.IConstants;
import io.github.ensyb.properties.meta.Key;
import io.github.ensyb.properties.meta.Source;

@Source("TestProps.properties")
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
