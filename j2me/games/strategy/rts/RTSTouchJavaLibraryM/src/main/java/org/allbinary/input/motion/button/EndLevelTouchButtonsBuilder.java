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
import org.allbinary.util.BasicArrayListD;
import org.allbinary.util.BasicArrayListUtil;
import org.allbinary.logic.communication.log.LogUtil;

public class EndLevelTouchButtonsBuilder
extends TouchButtonsListBuilder
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    @Override
    public BasicArrayList getList()
    {
        try
        {
            this.logUtil.putF(this.commonStrings.START, this, this.commonStrings.CONSTRUCTOR);

            final BasicArrayList list = new BasicArrayListD();

            list.addAllList(new BasicTouchButtonsBuilder().getList());
            
            list.addAllList(new BuildingScrollTouchButtonsBuilder().getList());
            
            list.addAllList(new UpgradeDowngradeTouchButtonsBuilder().getList());
            
            this.addList(list);
            
            return list;
        }
        catch (Exception e)
        {
            this.logUtil.put(this.commonStrings.EXCEPTION, this, this.commonStrings.CONSTRUCTOR, e);
            
            return BasicArrayListUtil.getInstance().getImmutableInstance();
        }
    }
}
