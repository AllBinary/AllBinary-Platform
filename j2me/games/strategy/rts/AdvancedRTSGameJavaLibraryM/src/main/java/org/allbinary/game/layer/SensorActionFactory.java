/*
 * AllBinary Open License Version 1
 * Copyright (c) 2006 AllBinary
 * 
 * By agreeing to this license you and any business entity you represent are
 * legally bound to the AllBinary Open License Version 1 legal agreement.
 * 
 * You may obtain the AllBinary Open License Version 1 legal agreement from
 * AllBinary or the root directory of AllBinary's AllBinary Platform repository.
 * 
 * Created By: Travis Berthelot
 * 
 */
package org.allbinary.game.layer;

/**
 *
 * @author user
 */
public class SensorActionFactory {

    private static final SensorActionFactory instance = new SensorActionFactory();

    /**
     * @return the instance
     */
    public static SensorActionFactory getInstance()
    {
        return instance;
    }

    private SensorActionFactory()
    {

    }

    public final SensorAction ATTACK = new SensorAction("Attack");
    public final SensorAction EVADE = new SensorAction("Evade");
}
