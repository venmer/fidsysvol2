package ru.mremne;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import ru.mremne.service.FidServicce;
import ru.mremne.service.FidServiceImpl;

/**
 * autor:maksim
 * date: 06.03.15
 * time: 22:28.
 */
public class ApplicationBinder extends AbstractBinder {
    @Override
    protected void configure() {
        bind(FidServiceImpl.class).to(FidServicce.class);
    }
}
