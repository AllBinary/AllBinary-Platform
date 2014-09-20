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

import javax.microedition.lcdui.Canvas;

import org.allbinary.logic.basic.string.CommonStrings;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.game.ai.BasicAI;
import org.allbinary.logic.basic.util.visitor.Visitor;
import org.allbinary.logic.java.bool.BooleanFactory;

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
