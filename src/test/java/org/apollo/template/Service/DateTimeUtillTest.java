package org.apollo.template.Service;

import org.junit.jupiter.api.Test;

import java.sql.SQLData;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class DateTimeUtillTest {

    @Test
    void localTimeToDate() {

        LocalDate localDate = LocalDate.of(2020,2,10);

        // expected date
        Date expected = java.sql.Date.valueOf("2020-2-10");

        // actual date
        Date actual = DateTimeUtill.LocalTimeToDate(localDate);

        assertEquals(actual, expected);

    }
}