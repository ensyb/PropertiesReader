package org.ensar.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;

final class PropertieResourceLoader {

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
		if(!source.startsWith("/"))
			source = addCharacter(source, '/', 0);
		
		properies = new Properties();
		try (InputStream inputStream = Properties.class.getResourceAsStream(source)){
			properies.load(inputStream);
		} catch (IOException e) {
			throw new PropertieLoaderException("there is no " + source + " on class path");
		}
	}

	public static String addCharacter(String source, char c, int index) {
		return new StringBuilder(source.substring(0, index))
				.append(c)
				.append(source.substring(index, source.length()))
				.toString();
	}

}
