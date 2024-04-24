package org.apollo.template.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class DateTimeUtill {

    public static Date LocalTimeToDate(LocalDate localDate){

        ZoneId defaultZoneId = ZoneId.systemDefault();
        Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

        return date;

    }

}
