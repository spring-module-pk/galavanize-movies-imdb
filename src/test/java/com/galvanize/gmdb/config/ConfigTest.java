package com.galvanize.gmdb.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ConfigTest {

    @Test
    public void testObjectMapperBean() {
        AnnotationConfigApplicationContext annotationConfigContextLoader = new AnnotationConfigApplicationContext(Config.class);
        ObjectMapper objectMapper = annotationConfigContextLoader.getBean(ObjectMapper.class);
        assertNotNull(objectMapper, "ObjectMapper bean should not be null");
        assertFalse(objectMapper.getSerializationConfig().isEnabled(SerializationFeature.FAIL_ON_EMPTY_BEANS),
                "FAIL_ON_EMPTY_BEANS should be disabled");

        // Close the context
        annotationConfigContextLoader.close();
    }
}
