/*
 * AllBinary Open License Version 1
 * Copyright (c) 2011 AllBinary
 *
 * Created By: Travis Berthelot
 * Date: 11/19/02
 *
 *
 * Modified By         When       ?
 *
 */
package allbinary.game.identification;

import org.allbinary.util.BasicArrayList;

import abcs.logic.basic.string.CommonStrings;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;

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
    
    public void add(Group groupInterface)
    {
        StringBuilder stringBuffer = new StringBuilder();
        
        stringBuffer.append("Adding: ");
        stringBuffer.append(groupInterface.toString());
        stringBuffer.append(" to ");
        stringBuffer.append(this.groupInterface);
        
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
