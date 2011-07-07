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

import javax.microedition.lcdui.Canvas;

import abcs.logic.basic.string.CommonStrings;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import abcs.logic.java.bool.BooleanFactory;
import allbinary.logic.basic.util.visitor.Visitor;

public class ThrustAIVisitor extends Visitor
{
    private final Boolean TRUE = BooleanFactory.getInstance().TRUE;

    protected ThrustAIVisitor()
    {
    }

    public Object visit(Object object)
    {
        try
        {
            BasicAI basicAI = (BasicAI) object;

            basicAI.processAI(Canvas.UP);
        } 
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, CommonStrings.getInstance().VISIT, e));
        }
        return TRUE;
    }
}
