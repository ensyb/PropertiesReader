package io.github.ensyb.properties.constants;

import io.github.ensyb.properties.meta.DefaultValue;
import io.github.ensyb.properties.meta.IConstants;
import io.github.ensyb.properties.meta.Key;
import io.github.ensyb.properties.meta.Value;

public interface ConstatntsInterfaceNoSource extends IConstants{

	
	@Value("Ensar")
	public String name();
	
	
	@Value("Bavrk")
	public String secondname();
	
	@DefaultValue("nesto")
	public String nesto();
	
	@Key("excp")
	public String expectedException();
}
