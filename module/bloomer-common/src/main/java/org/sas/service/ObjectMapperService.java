package org.sas.service;

/**
 * @author Vivek Tiwari
 */
public interface ObjectMapperService {

	<T> T readValueAs(String value, Class<T> targetClass);
	<T> String write(T object);
	<T> String prettyWrite(T object);

}
