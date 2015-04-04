package ru.mremne.service;

import ru.mremne.model.Result;

import javax.ws.rs.core.Response;
import java.util.Map;

/**
 * autor:maksim
 * date: 01.04.15
 * time: 17:02.
 */
public interface FidServicce {
    public Response addAngles(double[] angles);
    public Response checkAngles(double[] angles);
    public void saveStatus(Result result);
    public Map getStatus(String id);

}
