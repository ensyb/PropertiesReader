package org.ensar.properties;

class PropertieLoaderException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PropertieLoaderException() {
		super();
	}
	
	public PropertieLoaderException(String message) {
		super(message);
	}
	
	@Override
	public String getMessage() {
		return super.getMessage();
	}
}
