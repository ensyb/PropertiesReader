package io.github.ensyb.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;

public class PropertieResourceLoader {

	private static Properties properies;

	public static Optional<String> getPropertie(String key, String source) {
		if(properies == null){
			loadPropeties(source);
		}
		Properties properite = properies;
		String result = (String) properite.getProperty(key);
		return Optional.ofNullable(result);
	}
	
	private static void loadPropeties(String source) {
		properies = new Properties();
		
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		try (InputStream inputStream = loader.getResourceAsStream(source)){
			properies.load(inputStream);
		} catch (IOException e) {
			throw new PropertieLoaderException("there is no " + source + " on class path");
		}
	}



}
