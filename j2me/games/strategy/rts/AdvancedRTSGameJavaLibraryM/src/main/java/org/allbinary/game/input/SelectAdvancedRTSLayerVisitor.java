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
import org.allbinary.game.layer.special.CollidableDestroyableDamageableLayer;
import org.allbinary.game.layer.unit.UnitLayer;
import org.allbinary.logic.NullUtil;
import org.allbinary.util.BasicArrayList;
import org.allbinary.logic.util.visitor.Visitor;

public class SelectAdvancedRTSLayerVisitor
extends Visitor
{
    private final SelectedRTSLayersPlayerGameInput selectedRTSLayersPlayerGameInput;
    
    public SelectAdvancedRTSLayerVisitor(
            final SelectedRTSLayersPlayerGameInput selectedRTSLayersPlayerGameInput)
    {
        this.selectedRTSLayersPlayerGameInput = selectedRTSLayersPlayerGameInput;
    }
    
    @Override
    public Object visit(Object object)
    {
        final CollidableDestroyableDamageableLayer selectedLayer = (CollidableDestroyableDamageableLayer) object;

        if(selectedLayer != null && selectedLayer.getType() == BuildingLayer.getStaticType())
        {
            final AdvancedRTSGameLayer rtsGameLayer = (AdvancedRTSGameLayer) selectedLayer;
            final AssignWaypointsUtil assignWaypointsUtil = AssignWaypointsUtil.getInstance();
            final BasicArrayList list = selectedRTSLayersPlayerGameInput.getSelectedBasicArrayList();

            RTSLayer currentRTSLayer;
            UnitLayer unitLayer;
            for(int index = list.size() - 1; index >= 0; index--)
            {
                currentRTSLayer = (RTSLayer) list.get(index);

                if(currentRTSLayer.getType() == UnitLayer.getStaticType())
                {
                    unitLayer = (UnitLayer) currentRTSLayer;

                    unitLayer.setParentLayer(rtsGameLayer);

                    assignWaypointsUtil.set(unitLayer, rtsGameLayer);
                }
            }
        }
        return NullUtil.getInstance().NULL_OBJECT;
    }
}
