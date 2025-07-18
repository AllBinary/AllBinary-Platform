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
package org.allbinary.game.ai;

import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.java.bool.BooleanFactory;
import org.allbinary.logic.util.visitor.Visitor;
import org.allbinary.string.CommonStrings;

public class LastKeyAIVisitor extends Visitor
{
    protected final LogUtil logUtil = LogUtil.getInstance();
    
    private final Boolean TRUE = BooleanFactory.getInstance().TRUE;

    protected LastKeyAIVisitor()
    {
    }
    
    public Object visit(Object object)
    {
        try
        {
            BasicAI basicAI = (BasicAI) object;

            int key = basicAI.getLastKey();

            if(key != -1)
            {
                basicAI.processAI(key);
            }
        } 
        catch (Exception e)
        {
            final CommonStrings commonStrings = CommonStrings.getInstance();
            logUtil.put(commonStrings.EXCEPTION, this, commonStrings.VISIT, e);
        }
        return TRUE;
    }
}
