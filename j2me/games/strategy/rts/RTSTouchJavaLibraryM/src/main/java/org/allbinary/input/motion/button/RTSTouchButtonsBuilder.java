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

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;

public class RTSTouchButtonsBuilder
extends BaseTouchInput
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

            list.addAll(new UpgradeDowngradeTouchButtonsBuilder().getList());
            
            return list;
        }
        catch (Exception e)
        {
            logUtil.put(commonStrings.EXCEPTION, this, commonStrings.CONSTRUCTOR, e);

            return BasicArrayListUtil.getInstance().getImmutableInstance();
        }
    }
}
