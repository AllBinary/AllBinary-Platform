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

import java.util.Vector;

import org.allbinary.graphics.form.item.TextFieldItem;
import org.allbinary.util.BasicArrayList;

import org.allbinary.logic.java.bool.BooleanFactory;
import org.allbinary.logic.control.validate.ValidatorBase;
import org.allbinary.logic.math.SmallIntegerSingletonFactory;

public class NumberTextFieldItemValidator extends ValidatorBase
{
    private final TextFieldItem textFieldItem;
    private final int min;
    private final int max;
    private final int maxChars;
    
    public NumberTextFieldItemValidator(TextFieldItem textFieldItem, int maxChars, int min, int max)
    {
        this.textFieldItem = textFieldItem;
        
        this.min = min;
        this.max = max;
        this.maxChars = maxChars;
    }
    
    protected Boolean isNumberValid(Integer value)
    {
        return BooleanFactory.getInstance().TRUE;
    }

    protected Vector toNumberVector(Integer value)
    {
        return new Vector();
    }
        
    //@Override
    public Boolean isValid()
    {
        Boolean result = BooleanFactory.getInstance().TRUE;

        final String string = this.textFieldItem.getString();

        int textLength = string.length();

        if (textLength > 0 && textLength < maxChars)
        {
            // PreLogUtil.put(, this, "toVector");
            try
            {
                //result = this.isNumberValid(
                Integer.parseInt(this.textFieldItem.getString());
                //);
            } catch (NumberFormatException e)
            {
                result = BooleanFactory.getInstance().FALSE;
            }

        } else
        {
            if(textLength < 1)
            {
                result = BooleanFactory.getInstance().FALSE;
            }
            else
                if(textLength > maxChars)
                {
                    result = BooleanFactory.getInstance().FALSE;
                }
        }
        return result;
    }
    
    //@Override
    public BasicArrayList toList()
    {
        final BasicArrayList list = new BasicArrayList();        
        final String string = this.textFieldItem.getString();
        
        int textLength = string.length();
        
        final String name = this.textFieldItem.getLabel().substring(0, this.textFieldItem.getLabel().length() - 2);

        final SmallIntegerSingletonFactory smallIntegerSingletonFactory = SmallIntegerSingletonFactory.getInstance();
        
        if (textLength > 0 && textLength < maxChars)
        {
            try
            {
                Integer number = smallIntegerSingletonFactory.createInstance(Integer.parseInt(this.textFieldItem.getString()));
    
                //vector.addAll(this.toNumberVector(number));
                
                if(number.intValue() > min)
                {
                    list.add(name + " is to small");
                }
                else
                    if(number.intValue() > max)
                {
                    list.add(name + " is to large");
                }

            } catch (NumberFormatException e)
            {
                list.add(name + " is not a number");
            }

        } else
        {
            if(textLength < 1)
            {
                list.add(name + " is to short");
            }
            else
                if(textLength > maxChars)
                {
                    list.add(name + " is to long");
                }
        }
        return list;
    }
}
