package ru.mremne.service;

import ru.mremne.util.Util;

/**
 * autor:maksim
 * date: 10.02.15
 * time: 17:16.
 */
public class ServiceConnection {
    public static FidService getService() {
        return service;
    }

    private static final FidService service=new FidService(Util.getNeo4jUrl());
}
