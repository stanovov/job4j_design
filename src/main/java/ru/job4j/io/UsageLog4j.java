package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        boolean varBoolean = true;
        byte varByte = 127;
        short varShort = Short.MAX_VALUE;
        char varChar = Character.MAX_VALUE;
        int varInt = Integer.MAX_VALUE;
        long varLong = Long.MAX_VALUE;
        float varFloat = Float.MAX_VALUE;
        double varDouble = Double.MAX_VALUE;
        LOG.debug("boolean: {}, byte: {}, short: {}, char: {}, int: {}, long: {}, float: {}, double: {}",
                varBoolean, varByte, varShort, varChar, varInt, varLong, varFloat, varDouble);
    }
}
