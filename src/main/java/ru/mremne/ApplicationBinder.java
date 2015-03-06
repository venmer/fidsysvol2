package ru.mremne;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import ru.mremne.service.FidService;

/**
 * autor:maksim
 * date: 06.03.15
 * time: 22:28.
 */
public class ApplicationBinder extends AbstractBinder {
    @Override
    protected void configure() {
        bind(FidService.class).to(FidService.class);
    }
}
