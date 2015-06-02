package ru.mremne.service;

import org.glassfish.hk2.api.Factory;
import org.mockito.Mockito;

/**
 * autor:maksim
 * date: 02.06.15
 * time: 19:39.
 */
public class MockMongoService implements Factory<MongoService> {
    @Override
    public MongoService provide() {
        final MongoService mockedMongoService = Mockito.mock(MongoService.class);
        Mockito.when(mockedMongoService.getUserById(Mockito.anyString()));
        return mockedMongoService;
    }

    @Override
    public void dispose(MongoService instance) {

    }
}
