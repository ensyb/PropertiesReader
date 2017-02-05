package io.github.ensyb.properties.constants;

import io.github.ensyb.properties.meta.DefaultValue;
import io.github.ensyb.properties.meta.IConstants;
import io.github.ensyb.properties.meta.Key;
import io.github.ensyb.properties.meta.Source;

@Source("TestProps.properties")
public interface ConstantInterfaceWithSource extends IConstants {

	@Key("first")
	@DefaultValue("defaultFirstValue")
    String firstProperty();
	
	@Key("second")
	@DefaultValue("defaultSecondValue")
    String secondProperty();
	
	@Key("third")
	@DefaultValue("defaultThirdValue")
    String thirdProperty();
	
	@DefaultValue("oneConstant")
    String someOtherConstant();
	
	@Key("noValueForKey")
    String noDefaultValue();
	
}
