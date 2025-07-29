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

import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;


public class Input
{
    //protected final LogUtil logUtil = LogUtil.getInstance();
    
    private int id;
    private String name = StringUtil.getInstance().EMPTY_STRING;

    protected final InputFactory inputFactory = InputFactory.getInstance();
    
    public Input(int id, String name)
    {
        this.setId(id);
        this.setName(name);

        //logUtil.put(
          //      "Name: " + this.getName() + " = " + this.getId(), this,
            //    commonStrings.CONSTRUCTOR);
    }
    
    public int getId()
    {
        return id;
    }

    private void setId(int key)
    {
        this.id = key;
    }

    public String getName()
    {
        return name;
    }

    private void setName(String keyName)
    {
        this.name = keyName;
    }
    
    public String toString()
    {
        StringMaker stringBuffer = new StringMaker();
        stringBuffer.append("Name: ");
        stringBuffer.append(this.getName());
        stringBuffer.append(" Id: ");
        stringBuffer.append(this.getId());
        
        return stringBuffer.toString();
    }
}
