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
package org.allbinary.game.identification;

import org.allbinary.util.BasicArrayList;

import org.allbinary.string.CommonStrings;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringUtil;

public class CompositeGroup
{
    private final Group groupInterface;
    private final BasicArrayList list = new BasicArrayList();

    public CompositeGroup(Group groupInterface)
    {
        this.groupInterface = groupInterface;
    }
    
    public void clear()
    {
        this.list.clear();
    }
    
    private static final String ADDING_LABEL = "Adding: ";
    private static final String TO_LABEL = " to: ";
    
    public void add(Group groupInterface)
    {
        StringMaker stringBuffer = new StringMaker();
        
        stringBuffer.append(ADDING_LABEL);
        stringBuffer.append(groupInterface.toString());
        stringBuffer.append(TO_LABEL);
        stringBuffer.append(StringUtil.getInstance().toString(this.groupInterface));
        
        LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), this, CommonStrings.getInstance().ADD));
        
        this.list.add(groupInterface);
    }
    
    public boolean isInGroup(Group groupInterface)
    {
        if(list.contains(groupInterface))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public Group getGroup()
    {
        return groupInterface;
    }
}
