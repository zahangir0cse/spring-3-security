package com.crackcode.zahangir.utils;

import java.util.*;
import java.util.concurrent.TimeUnit;

public final class DateUtils {

    private DateUtils() {}
    public static Date getExpirationTime(Long expireHours) {
        Date now = new Date();
        Long expireInMilis = TimeUnit.HOURS.toMillis(expireHours);
        return new Date(expireInMilis + now.getTime());
    }
}
