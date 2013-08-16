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
package allbinary.graphics.form.item.validation;

import org.allbinary.game.input.TextNotificationUtil;

import abcs.logic.basic.string.CommonStrings;
import abcs.logic.basic.string.StringValidationUtil;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import abcs.logic.java.bool.BooleanFactory;
import allbinary.logic.basic.util.visitor.Visitor;

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
                    LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, CommonStrings.getInstance().VISIT, e));
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
