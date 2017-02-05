package io.github.ensyb.properties.constants;

import io.github.ensyb.properties.meta.DefaultValue;
import io.github.ensyb.properties.meta.IConstants;
import io.github.ensyb.properties.meta.Key;
import io.github.ensyb.properties.meta.Value;

public interface ConstatntsInterfaceNoSource extends IConstants{

	
	@Value("Ensar")
    String name();
	
	
	@Value("Bavrk")
    String secondname();
	
	@DefaultValue("nesto")
    String nesto();
	
	@Key("excp")
    String expectedException();
}
