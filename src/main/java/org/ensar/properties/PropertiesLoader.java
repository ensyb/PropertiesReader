package org.ensar.properties;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Optional;

import org.ensar.properties.meta.IConstants;
import org.ensar.properties.meta.DefaultValue;
import org.ensar.properties.meta.Key;
import org.ensar.properties.meta.Source;
import org.ensar.properties.meta.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertiesLoader {
	private static final Logger LOG = LoggerFactory.getLogger(PropertiesLoader.class);

	private static final String DEFAULT_LOADED_VALUE = "";

	@SuppressWarnings("unchecked")
	public static <Type extends IConstants> Type of(Class<Type> classOf) {
		return (Type) Proxy.newProxyInstance(classOf.getClassLoader(), new Class[] { classOf },
				(Object proxy, Method method, Object[] args) -> {
					try {
						return loadOrDefault(method);
					} catch (Exception e) {
						throw new PropertieLoaderException(e.getMessage());
					}
				});
	}

	@SuppressWarnings("unchecked")
	public static <Type extends IConstants> Type ofNoSource(Class<Type> classOf) {
		return (Type) Proxy.newProxyInstance(classOf.getClassLoader(), new Class[] { classOf },
				(Object proxy, Method method, Object[] args) -> {
					try {
						return loadOfNoSource(method);
					} catch (Exception e) {
						throw new PropertieLoaderException(e.getMessage());
					}
				});
	}

	private static String loadOrDefault(Method method) {
		String sourceValue = getSourceValue(method).orElse(DEFAULT_LOADED_VALUE);

		String keyValue = getKeyValueIfPresent(method).orElse(DEFAULT_LOADED_VALUE);
		if (!sourceValue.isEmpty()) {
			String loadedValue = loadValueFromResourceLoader(keyValue, sourceValue);
			if (loadedValue.isEmpty()) {
				String ifOccuredException = "Can't load propertie with key " + keyValue
						+ " and there is no default value specified @DefaultValue for this field";
				return getDefault(method, ifOccuredException);
			}
			return loadedValue;
		} else {
			String ifOccuredException = "Can't use key @Key if there is no source @Source specified, instead"
					+ " of that use defaultValue @DefaultValue ";
			return getDefault(method, ifOccuredException);
		}
	}

	private static Optional<String> getSourceValue(Method method) {
		String source = null;
		Annotation sourceAnotation = method.getDeclaringClass().getAnnotation(Source.class);
		if (sourceAnotation == null)
			LOG.warn("WARNING : There is no source @Source annotation to load properties file");

		if (sourceAnotation instanceof Source)
			source = ((Source) sourceAnotation).value();
		return Optional.ofNullable(source);
	}

	private static Optional<String> getKeyValueIfPresent(Method method) {
		String annotationValue = null;
		if (checkIfAnnotationIsPresentOnMethod(method, Key.class)) {
			annotationValue = (String) method.getAnnotation(Key.class).value();
		}
		return Optional.ofNullable(annotationValue);
	}

	private static String getDefault(Method method, String exceptionMessage) {
		String defaultValue = getDefaultValueIfPresent(method).orElse(DEFAULT_LOADED_VALUE);
		if (defaultValue.isEmpty())
			throw new PropertieLoaderException(exceptionMessage);
		return defaultValue;
	}

	private static Optional<String> getDefaultValueIfPresent(Method method) {
		String annotationValue = null;
		if (checkIfAnnotationIsPresentOnMethod(method, DefaultValue.class)) {
			annotationValue = (String) method.getAnnotation(DefaultValue.class).value();
		} else if (checkIfAnnotationIsPresentOnMethod(method, Value.class)) {
			annotationValue = (String) method.getAnnotation(Value.class).value();
		}
		return Optional.ofNullable(annotationValue);
	}

	private static String loadValueFromResourceLoader(String key, String source) {
		return PropertieResourceLoader.getPropertie(key, source).orElse(DEFAULT_LOADED_VALUE);
	}

	private static <T extends Annotation> boolean checkIfAnnotationIsPresentOnMethod(Method method,
			Class<? extends Annotation> annotationClass) {
		Annotation annoatation = method.getAnnotation(annotationClass);
		return (annoatation == null) ? false : true;
	}

	private static String loadOfNoSource(Method method) {
		loadOfNoSourceConatinsAnnotations(method);
		String defaultValue = getDefaultValueIfPresent(method).orElse("");
		if (defaultValue.isEmpty()) {
			return getValueFromValueAnnotation(method);
		}
		return defaultValue;
	}

	private static void loadOfNoSourceConatinsAnnotations(Method method) {
		String message = "if you loading properties form file use PropertiesLoader.of method";
		if (checkIfAnnotationIsPresentOnMethod(method, Source.class))
			throw new PropertieLoaderException(message);

		if (checkIfAnnotationIsPresentOnMethod(method, Key.class)) {
			throw new PropertieLoaderException(message);
		}

	}

	private static String getValueFromValueAnnotation(Method method) {
		if (!checkIfAnnotationIsPresentOnMethod(method, Value.class))
			throw new PropertieLoaderException("There is no @Value on this field cannot load anything ");

		String annotationValue = (String) method.getAnnotation(Value.class).value();
		if (null == annotationValue || annotationValue.isEmpty()) {
			throw new PropertieLoaderException("@Value cannot be empty");
		}

		return annotationValue;
	}
}
