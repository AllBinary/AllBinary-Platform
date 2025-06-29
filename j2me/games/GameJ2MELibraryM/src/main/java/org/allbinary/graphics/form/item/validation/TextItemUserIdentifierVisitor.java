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

import org.allbinary.game.input.TextNotificationUtil;

import org.allbinary.string.CommonStrings;
import org.allbinary.logic.string.StringValidationUtil;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.java.bool.BooleanFactory;
import org.allbinary.logic.util.visitor.Visitor;

public class TextItemUserIdentifierVisitor extends Visitor
{
    public TextItemUserIdentifierVisitor()
    {
        
    }

    public Object visit(Object object)
    {
        String value = (String) object;
        
        if (value.length() < 2)
        {
            char charValue = value.charAt(0);
            
            final StringValidationUtil stringValidationUtil = StringValidationUtil.getInstance();
            
            if(stringValidationUtil.isNumber(value))
            {
                return BooleanFactory.getInstance().TRUE;
            }
            else
                //if(Character.isLetter(charValue))
                if(this.isLetter(charValue))
                {
                    return BooleanFactory.getInstance().TRUE;
                }
                else
                
                /*
                if(charValue > 'A' && charValue < 'Z')
                {
                    return BooleanFactory.getInstance().TRUE;
                }
                else
                    if(charValue > 'a' && charValue < 'z')
                    {
                        return BooleanFactory.getInstance().TRUE;
                    }
                    else
                    */
            {
                try
                {
                    TextNotificationUtil.getInstance().fireError(
                            //"Only [0-9][A-Z][a-z]"
                            "Only Letters & Numbers Allowed"
                            );
                } catch (Exception e)
                {
                    final CommonStrings commonStrings = CommonStrings.getInstance();
                    LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, commonStrings.VISIT, e));
                }
                
                return BooleanFactory.getInstance().FALSE;
            }
        }
        else
        {
            return BooleanFactory.getInstance().FALSE;
        }
    }
    
    public boolean isLetter(char charValue) {
        return (charValue > 64 && charValue < 91) || (charValue > 96 && charValue < 123);
    }
}
