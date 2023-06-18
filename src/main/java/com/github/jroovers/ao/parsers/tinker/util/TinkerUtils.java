package com.github.jroovers.ao.parsers.tinker.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class TinkerUtils {

    public static Integer clipSize(Integer clipsize) {
        return clipsize == -1 ? null : clipsize;
    }

    public static Double timing(Integer timing) {
        return Double.valueOf(timing) / 10.00;
    }

}
