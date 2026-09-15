package com.br.GerenciadorProjetos.utils;

import java.math.BigDecimal;

public final class BigDecimalUtils {

    private BigDecimalUtils() {
    }

    public static Boolean isGreaterThan(BigDecimal first, BigDecimal second) {
        return first.compareTo(second) > 0;
    }

    public static Boolean isLessThan(BigDecimal first, BigDecimal second) {
        return first.compareTo(second) < 0;
    }

    public static Boolean isGreaterThanOrEqualTo(BigDecimal first, BigDecimal second) {
        return first.compareTo(second) >= 0;
    }

    public static Boolean isLessThanOrEqualTo(BigDecimal first, BigDecimal second) {
        return first.compareTo(second) <= 0;
    }
}

