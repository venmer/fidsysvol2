package ru.mremne;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import ru.mremne.service.FidService;
import ru.mremne.service.FidServiceImpl;
import ru.mremne.service.MongoService;
import ru.mremne.service.MongoServiceImpl;

/**
 * autor:maksim
 * date: 06.03.15
 * time: 22:28.
 */
public class ApplicationBinder extends AbstractBinder {
    @Override
    protected void configure() {
        bind(FidServiceImpl.class).to(FidService.class);
        bind(MongoServiceImpl.class).to(MongoService.class);
    }
}
