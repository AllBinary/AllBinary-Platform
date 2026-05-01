/*
* AllBinary Open License Version 1
* Copyright (c) 2011 AllBinary
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
package org.allbinary.logic.util.cache;

import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.util.BasicArrayList;
import org.allbinary.util.BasicArrayListUtil;

/**
 *
 * @author user
 */
public class KeylessBasicArrayListCache
    extends IndexedBasicArrayListCache
{
    protected final LogUtil logUtil = LogUtil.getInstance();
    protected final BasicArrayListUtil basicArrayListUtil = BasicArrayListUtil.getInstance();

    public KeylessBasicArrayListCache()
    {
    }
    
    public BasicArrayList get()
    //throws Exception
    {
        try
        {
            if (this.index >= this.list.size())
            {
                this.addDefault();
            }

            final BasicArrayList list = (BasicArrayList) this.getAt(this.index++);
            return list;

            //return new BasicArrayListS(this.maxPathSize);
        }
        catch (Exception e)
        {
            this.logUtil.put(this.commonStrings.EXCEPTION, this, this.commonStrings.GET, e);
            return this.basicArrayListUtil.getImmutableInstance();
        }
    }

    @Override
    public void clear()
    {
        super.clear();

        BasicArrayList basicArrayList;
        for (int index = this.list.size() - 1; index >= 0; index--)
        {
            basicArrayList = ((BasicArrayList) this.list.objectArray[index]);
            basicArrayList.clear();
        }
    }

    @Override
    public String log()
    {
        final StringMaker stringBuffer = new StringMaker();

        stringBuffer.append("S: ");
        stringBuffer.appendint(this.list.size());

        final String S_LABEL = " s: ";
        
        BasicArrayList basicArrayList;
        for (int index = this.list.size() - 1; index >= 0; index--)
        {
            stringBuffer.append(S_LABEL);
            basicArrayList = ((BasicArrayList) this.list.objectArray[index]);
            stringBuffer.appendint(basicArrayList.size());
        }

        return stringBuffer.toString();
    }

}
