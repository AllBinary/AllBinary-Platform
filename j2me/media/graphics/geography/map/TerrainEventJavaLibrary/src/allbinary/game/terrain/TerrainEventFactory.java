/*
 * AllBinary Open License Version 1
 * Copyright (c) 2011 AllBinary
 *
 * Created By: Travis Berthelot
 * Date: July 22, 2006, 2:57 AM
 *
 *
 * Modified By         When       ?
 *
 */ 
package allbinary.game.terrain;

import allbinary.logic.basic.util.event.AllBinaryEventObject;
import allbinary.logic.basic.util.event.AllBinaryEventObjectFactoryInterface;

public class TerrainEventFactory implements AllBinaryEventObjectFactoryInterface
{
    public TerrainEventFactory()
    {
    }
    
    public AllBinaryEventObject getInstance()
    {
        return new TerrainEvent();
    }
    
}
