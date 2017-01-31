package org.ensar.properties.resources;

import org.ensar.properties.meta.DefaultValue;
import org.ensar.properties.meta.IConstants;
import org.ensar.properties.meta.Key;
import org.ensar.properties.meta.Value;

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
