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
package org.allbinary.game.input;

import jsinterop.annotations.JsType;

import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;



@JsType
public class Input
{
    //protected final LogUtil logUtil = LogUtil.getInstance();
    
    private int id;
    private String name = StringUtil.getInstance().EMPTY_STRING;
    
    @JsConstructor
    public Input(int id, String name)
    {
        this.setId(id);
        this.setName(name);

        //this.logUtil.putF(
          //      "Name: " + this.getName() + " = " + this.getId(), this, //    commonStrings.CONSTRUCTOR);
    }
    
    @JsMethod
    public int getId()
    {
        return this.id;
    }

    @JsMethod
    private void setId(int key)
    {
        this.id = key;
    }

    @JsMethod
    public String getName()
    {
        return this.name;
    }

    @JsMethod
    private void setName(String keyName)
    {
        this.name = keyName;
    }
    
    @JsMethod
    public String toString()
    {
        StringMaker stringBuffer = new StringMaker();
        stringBuffer.append("Name: ");
        stringBuffer.append(this.getName());
        stringBuffer.append(" Id: ");
        stringBuffer.appendint(this.getId());
        
        return stringBuffer.toString();
    }
}
