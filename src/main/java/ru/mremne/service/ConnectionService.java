package ru.mremne.service;

import ru.mremne.util.Util;

/**
 * autor:maksim
 * date: 10.02.15
 * time: 17:16.
 */
public class ConnectionService {
    public static FidService getService() {
        return SERVICE;
    }

    private static final FidService SERVICE =new FidService(Util.getNeo4jUrl());
}
