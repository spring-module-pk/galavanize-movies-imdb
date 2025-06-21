package com.galvanize.starter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SampleTest {
    @Test
    void sampleTest() {
        assertTrue(true);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Hello", "World"})
    void sampleParameterizedTest(String str) {
        assertNotNull(str);
    }
}
