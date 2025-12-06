package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {

        byte variable1 = 1;
        short variable2 = 2;
        int variable3 = 3;
        long variable4 = 4L;
        float variable5 = 5F;
        double variable6 = 6.0;
        boolean variable7 = true;
        char variable8 = 'A';

        LOG.debug("variable1 : {}, "
                        + "variable2 : {}, "
                        + "variable3 : {}, "
                        + "variable4 : {}, "
                        + "variable5 : {}, "
                        + "variable6 : {}, "
                        + "variable7 : {}, "
                        + "variable8 : {}",
                variable1,
                variable2,
                variable3,
                variable4,
                variable5,
                variable6,
                variable7,
                variable8
        );
    }
}