/*
 * AllBinary Open License Version 1
 * Copyright (c) 2003 AllBinary
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

package org.allbinary.game.input;

import org.allbinary.logic.util.visitor.Visitor;

public class SelectRTSLayerVisitorFactory 
implements SelectRTSLayerVisitorFactoryInterface 
{
    private static final SelectRTSLayerVisitorFactory instance =
        new SelectRTSLayerVisitorFactory();
    
    public static SelectRTSLayerVisitorFactory getInstance()
    {
        return instance;
    }
    
    private SelectRTSLayerVisitorFactory()
    {
        
    }
    
    public Visitor getInstance(
            SelectedRTSLayersPlayerGameInput selectedRTSLayersPlayerGameInput)
    {
        return new SelectRTSLayerVisitor();
    }
}
