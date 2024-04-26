package org.apollo.template.Domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AutocamperTest {

    private Autocamper autocamper = null;

    @BeforeEach
    void setUp() {

        autocamper = new Autocamper();

    }

    @Test
    void validateIntegerNotNegativePositive() {

        boolean actual = autocamper.validateIntegerNotNegative(10);
        boolean expected = true;

        assertEquals(actual, expected);

    }

    @Test
    void validateIntegerNotNegativeNegative() {

        boolean actual = autocamper.validateIntegerNotNegative(-10);
        boolean expected = false;

        assertEquals(actual, expected);

    }

    @Test
    void validateFloatNotNegativePositive() {

        boolean actual = autocamper.validateFloatNotNegative(10.89f);
        boolean expected = true;

        assertEquals(actual, expected);
    }
    @Test
    void validateFloatNotNegativeNegative() {

        boolean actual = autocamper.validateFloatNotNegative(-10.89f);
        boolean expected = false;

        assertEquals(actual, expected);
    }



}