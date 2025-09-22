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

import org.allbinary.util.BasicArrayList;
import org.allbinary.util.BasicArrayListUtil;

import org.allbinary.string.CommonStrings;

import org.allbinary.logic.communication.log.LogUtil;

public class EndLevelNoBuildingSelectedTouchButtonsBuilder
extends TouchButtonsListBuilder
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    public BasicArrayList getList()
    {
        try
        {
            logUtil.put(commonStrings.START, this, commonStrings.CONSTRUCTOR);

            final BasicArrayList list = new BasicArrayList();

            list.addAll(new BasicTouchButtonsBuilder().getList());
            list.addAll(new BuildingScrollTouchButtonsBuilder().getList());

            this.add(list);
            
            return list;
        }
        catch (Exception e)
        {
            logUtil.put(commonStrings.EXCEPTION, this, commonStrings.CONSTRUCTOR, e);
            return BasicArrayListUtil.getInstance().getImmutableInstance();
        }
    }
}
