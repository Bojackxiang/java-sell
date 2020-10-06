package com.alex.java.Utils;

import java.util.UUID;

public class  KeyUtil {
    public static synchronized UUID generateUUID(){
        final long mostSignificant64Bits = 112L;
        final long leastSignificant64Bits = 1231L;
        return new UUID(mostSignificant64Bits, leastSignificant64Bits);

    }
}
