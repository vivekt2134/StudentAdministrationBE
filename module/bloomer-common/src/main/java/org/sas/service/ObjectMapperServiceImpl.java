package org.sas.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Vivek Tiwari
 */
@RequiredArgsConstructor
@Service
public class ObjectMapperServiceImpl implements ObjectMapperService {

	private final ObjectMapper mapper;

	public <T> T readValueAs(String value, Class<T> targetClass) {
		try {
			return mapper.readValue(value, targetClass);
		} catch (IOException exception) {
			throw new RuntimeException("Error while parsing class.", exception);
		}
	}

	public <T> String write(T object) {
		try {
			return mapper.writeValueAsString(object);
		} catch (IOException exception) {
			throw new RuntimeException("Error while parsing class.", exception);
		}
	}

	public <T> String prettyWrite(T object) {
		try {
			return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
		} catch (IOException exception) {
			throw new RuntimeException("Error while parsing class.", exception);
		}
	}

}
