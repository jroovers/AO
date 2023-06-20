package com.github.jroovers.ao.parsers.tinker.util;

import lombok.experimental.UtilityClass;

import java.util.Map;
import java.util.Optional;

import static com.github.jroovers.ao.Constants.OTHER_ATTACK_RATING_CAP;

@UtilityClass
public class TinkerUtils {

    public static Integer clipSize(Integer clipsize) {
        return clipsize == -1 ? null : clipsize;
    }

    public static Double timing(Integer timing) {
        return Double.valueOf(timing) / 10.00;
    }

    public static Integer mbs(Map<String, Integer> other) {
        Optional<Integer> mbs = Optional.ofNullable(other.get(OTHER_ATTACK_RATING_CAP));
        return mbs.orElse(null);
    }

}
