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

import org.allbinary.game.input.form.AssignWaypointsUtil;
import org.allbinary.game.layer.AdvancedRTSGameLayer;
import org.allbinary.game.layer.RTSLayer;
import org.allbinary.game.layer.building.BuildingLayer;
import org.allbinary.game.layer.unit.UnitLayer;
import org.allbinary.util.BasicArrayList;

import org.allbinary.logic.util.visitor.Visitor;

public class SelectAdvancedRTSLayerVisitor
extends Visitor
{
    private final SelectedRTSLayersPlayerGameInput selectedRTSLayersPlayerGameInput;
    
    public SelectAdvancedRTSLayerVisitor(
            SelectedRTSLayersPlayerGameInput selectedRTSLayersPlayerGameInput)
    {
        this.selectedRTSLayersPlayerGameInput = selectedRTSLayersPlayerGameInput;
    }
    
    public Object visit(Object object)
    {
        AdvancedRTSGameLayer selectedLayer = (AdvancedRTSGameLayer) object;
        
        if(selectedLayer != null && selectedLayer.getType() == BuildingLayer.getStaticType())
        {
            BasicArrayList list = selectedRTSLayersPlayerGameInput.getSelectedBasicArrayList();
            
            for(int index = list.size() - 1; index >= 0; index--)
            {
                RTSLayer currentRTSLayer = (RTSLayer) list.get(index);
                
                if(currentRTSLayer.getType() == UnitLayer.getStaticType())
                {
                    UnitLayer unitLayer = (UnitLayer) currentRTSLayer;

                    unitLayer.setParentLayer(selectedLayer);

                    AssignWaypointsUtil.getInstance().set(
                            unitLayer, selectedLayer);
                }
            }
        }
        return null;
    }
}
