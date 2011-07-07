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
package allbinary.game.ai;

import abcs.logic.basic.string.CommonStrings;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import abcs.logic.java.bool.BooleanFactory;
import allbinary.logic.basic.util.visitor.Visitor;

public class LastKeyAIVisitor extends Visitor
{    
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
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, CommonStrings.getInstance().VISIT, e));
        }
        return TRUE;
    }
}
