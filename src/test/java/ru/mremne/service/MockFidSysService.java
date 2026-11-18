package ru.mremne.service;

import org.glassfish.hk2.api.Factory;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

/**
 * autor:maksim
 * date: 02.06.15
 * time: 15:59.
 */
public class MockFidSysService implements Factory<FidService> {

    @Override
    public FidService provide() {
        final FidService mockedFidService = Mockito.mock(FidService.class);
        Mockito.when(mockedFidService.addAngles(Mockito.<Double[]>any()))
                .thenAnswer(new Answer<Boolean>() {
                    @Override
                    public Boolean answer(InvocationOnMock invocationOnMock) throws Throwable {
                        Double[] angles = (Double[]) invocationOnMock.getArguments()[0];
                        if (angles.length != 0)
                            return true;
                        else return false;
                    }
                });
        Mockito.when(mockedFidService.checkAngles(Mockito.<Double[]>any()))
                .thenAnswer(new Answer<ru.mremne.model.identification.Matching>() {
                    @Override
                    public ru.mremne.model.identification.Matching answer(InvocationOnMock invocationOnMock) throws Throwable {
                        Double[] angles = (Double[]) invocationOnMock.getArguments()[0];
                        if (angles.length != 0)
                            return new ru.mremne.model.identification.Matching(angles.length - 1, angles.length - 1);
                        else return new ru.mremne.model.identification.Matching(0, 0);
                    }
                });
        return mockedFidService;
    }

    @Override
    public void dispose(FidService instance) {

    }
}
