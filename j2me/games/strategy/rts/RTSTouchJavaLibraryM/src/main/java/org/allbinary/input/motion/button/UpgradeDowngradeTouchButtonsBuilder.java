/*
 * AllBinary Open License Version 1
 * Copyright (c) 2007 AllBinary
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

package org.allbinary.input.motion.button;

import org.allbinary.game.input.resource.TouchButtonDowngradeResource;
import org.allbinary.game.input.resource.TouchButtonUpgradeResource;
import org.allbinary.util.BasicArrayList;
import org.allbinary.util.BasicArrayListUtil;

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.graphics.CellPositionFactory;

public class UpgradeDowngradeTouchButtonsBuilder
extends BaseTouchInput
{
    public BasicArrayList getList()
    {
        try
        {
            LogUtil.put(LogFactory.getInstance(commonStrings.START, this, commonStrings.CONSTRUCTOR));

            final BasicArrayList list = new BasicArrayList();

            final TouchButtonLocationHelper touchButtonLocationHelper = new TouchButtonLocationHelper();

            final CellPositionFactory cellPositionFactory = CellPositionFactory.getInstance();
            
            final BasicTouchButtonCellPositionFactory basicTouchButtonCellPositionFactory = 
                new BasicTouchButtonCellPositionFactory();
            
            if (basicTouchButtonCellPositionFactory.SECOND_FROM_TOP_LEFT != cellPositionFactory.NONE
                    && basicTouchButtonCellPositionFactory.SECOND_FROM_TOP_RIGHT != cellPositionFactory.NONE)
            {
                CommonButtons commonButtons = CommonButtons.getInstance();
                
                // Upgrade Button
                TouchButton UP = new TouchButton(
                        BasicTouchInputFactory.getInstance().SPECIAL_BUTTON_TWO,
                        TouchButtonUpgradeResource.getInstance(),
                        commonButtons.NORMAL_BUTTON, 
                        basicTouchButtonCellPositionFactory.SECOND_FROM_TOP_LEFT, 
                        touchButtonLocationHelper.getColumnsRemainderHalf(), 
                        touchButtonLocationHelper.getRowsRemainderHalf());

                // Downgrade Button
                TouchButton DOWN = new TouchButton(
                        BasicTouchInputFactory.getInstance().SPECIAL_BUTTON_ONE,
                        TouchButtonDowngradeResource.getInstance(),
                        commonButtons.NORMAL_BUTTON, 
                        basicTouchButtonCellPositionFactory.SECOND_FROM_TOP_RIGHT, 
                        touchButtonLocationHelper.getColumnsRemainderHalf(), 
                        touchButtonLocationHelper.getRowsRemainderHalf());

                list.add(UP);
                list.add(DOWN);

            }

            return list;
            
        } catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, commonStrings.CONSTRUCTOR, e));
            
            return BasicArrayListUtil.getInstance().getImmutableInstance();
        }
    }
}
