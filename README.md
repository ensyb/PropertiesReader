# PropertiesReader

load values from properties file 

```java
@Source("package/testProperties.properties")
public interface ConstantsWithSource extends IConstants {

	@Key("first")
	@DefaultValue("defaultFirstValue")
	public String firstProperty();
  
  }
  
//usage
//if there is no key "first" in file default value will be used 
ConstantsWithSource constants = PropertiesLoader.of(ConstantsWithSource.class);
String value = constants.firstProperty();

//loader can be used without source

	@DefaultValue("defaultValue")
  //or
  @Value("defaultValue")
	public String firstProperty();
  
  ConstantsWithSource constants = PropertiesLoader.ofNoSource(ConstantsWithSource.class);


```
