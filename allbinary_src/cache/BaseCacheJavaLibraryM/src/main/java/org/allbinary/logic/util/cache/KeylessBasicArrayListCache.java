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

import org.allbinary.util.BasicArrayList;

import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;

/**
 *
 * @author user
 */
public class KeylessBasicArrayListCache
    extends IndexedBasicArrayListCache
{
    public KeylessBasicArrayListCache()
    {
        
    }
    
    public KeylessBasicArrayListCache(int size)
    throws Exception
    {
        super(size);
    }
    
    public BasicArrayList get()
    //throws Exception
    {
        try
        {
            if (this.index >= this.list.size())
            {
                this.add();
            }

            //BasicArrayList list = (BasicArrayList) this.get(this.index++);
            //return list;
            return (BasicArrayList) this.get(this.index++);

            //return new BasicArrayList(this.maxPathSize);
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, commonStrings.GET, e));
            return null;
        }
    }

    public void clear()
    {
        super.clear();

        for (int index = this.list.size() - 1; index >= 0; index--)
        {
            ((BasicArrayList) this.list.objectArray[index]).clear();
        }
    }

    public String log()
    {
        final StringMaker stringBuffer = new StringMaker();

        stringBuffer.append("S: ");
        stringBuffer.append(this.list.size());

        for (int index = this.list.size() - 1; index >= 0; index--)
        {
            stringBuffer.append(" s: ");
            stringBuffer.append(
            ((BasicArrayList) this.list.objectArray[index]).size()
                );
        }

        return stringBuffer.toString();
    }

}
