# PropertiesReader

load values from properties file 

maven usage :

```xml 
<dependency>
	<groupId>io.github.ensyb.properties</groupId>
	<artifactId>PropertiesReader</artifactId>
	<version>0.1.3</version>
</dependency>
```

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
