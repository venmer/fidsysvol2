package ru.mremne.service;

import ru.mremne.model.identification.Matching;

/**
 * autor:maksim
 * date: 01.04.15
 * time: 17:02.
 */
public interface FidService {
    public boolean addAngles(Double[] angles);

    public Matching checkAngles(Double[] angles);

}
