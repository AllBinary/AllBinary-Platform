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
package org.allbinary.graphics.form.item.validation;

import org.allbinary.logic.java.bool.BooleanFactory;
import org.allbinary.logic.basic.util.visitor.Visitor;

public class TextItemVisitor extends Visitor
{
    public TextItemVisitor()
    {
        
    }

    public Object visit(Object object)
    {
        String value = (String) object;
        
        if (value.length() < 2)
        {
            return BooleanFactory.getInstance().TRUE;
        }
        else
        {
            return BooleanFactory.getInstance().FALSE;
        }
        
    }
}
