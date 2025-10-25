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
package org.allbinary.game.input;

import org.allbinary.logic.util.visitor.Visitor;

public class SelectAdvancedRTSLayerVisitorFactory 
implements SelectRTSLayerVisitorFactoryInterface 
{
    private static final SelectAdvancedRTSLayerVisitorFactory instance =
        new SelectAdvancedRTSLayerVisitorFactory();
    
    public static SelectAdvancedRTSLayerVisitorFactory getInstance()
    {
        return instance;
    }
    
    private SelectAdvancedRTSLayerVisitorFactory()
    {
        
    }
    
    @Override
    public Visitor getInstance(final SelectedRTSLayersPlayerGameInput selectedRTSLayersPlayerGameInput)
    {
        return new SelectAdvancedRTSLayerVisitor(selectedRTSLayersPlayerGameInput);
    }
}
