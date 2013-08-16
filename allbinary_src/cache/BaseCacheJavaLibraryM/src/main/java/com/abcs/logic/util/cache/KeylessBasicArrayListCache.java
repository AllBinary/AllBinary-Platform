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
package com.abcs.logic.util.cache;

import org.allbinary.util.BasicArrayList;

import abcs.logic.basic.string.CommonStrings;
import abcs.logic.basic.string.StringMaker;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;

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
            if (this.index >= this.getList().size())
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
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, CommonStrings.getInstance().GET, e));
            return null;
        }
    }

    public void clear()
    {
        super.clear();

        for (int index = this.getList().size() - 1; index >= 0; index--)
        {
            ((BasicArrayList) this.getList().objectArray[index]).clear();
        }
    }

    public String log()
    {
        StringMaker stringBuffer = new StringMaker();

        stringBuffer.append("S: ");
        stringBuffer.append(this.getList().size());

        for (int index = this.getList().size() - 1; index >= 0; index--)
        {
            stringBuffer.append(" s: ");
            stringBuffer.append(
            ((BasicArrayList) this.getList().objectArray[index]).size()
                );
        }

        return stringBuffer.toString();
    }

}
