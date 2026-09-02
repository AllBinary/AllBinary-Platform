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
package org.allbinary.game.input.mapping;

import java.util.Hashtable;

import jsinterop.annotations.JsType;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsProperty;

import org.allbinary.game.input.Input;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.string.CommonLabels;
import org.allbinary.string.CommonStrings;
import org.allbinary.util.BasicArrayList;
import org.allbinary.util.BasicArrayListD;
import org.allbinary.logic.StdUtil;

@JsType
public class InputMapping
{
    @JsProperty
    protected final LogUtil logUtil = LogUtil.getInstance();
    
    @JsProperty
    protected final CommonStrings commonStrings = CommonStrings.getInstance();

   //TWB - Use MapList someday
    private final Hashtable hashtable = StdUtil.getInstance().createHashtable();
    private final BasicArrayList mappedList = new BasicArrayListD();

    @JsConstructor
    protected InputMapping()
    {
    }

    @JsMethod
    public int getTotalMapped()
    {
        return this.hashtable.size();
    }
    
    @JsMethod
    protected boolean isDefaultNew()
    {
        return false;
    }
    
    @JsMethod
    protected InputToGameKeyMapping getDefault()
    {
        return InputToGameKeyMapping.getNullInstance();
    }
    
    @JsMethod
    protected void removeAll()
    {
        this.hashtable.clear();
        this.mappedList.clear();
    }

    @JsMethod
    public void remove(final Input input, final Input mappedToInput)
    {
        final StringMaker stringBuffer = new StringMaker();
        
        final StringUtil stringUtil = StringUtil.getInstance();
        
        stringBuffer.append(CommonLabels.getInstance().START_LABEL);
        stringBuffer.append(stringUtil.toString(input));
        stringBuffer.append(" == ");
        stringBuffer.append(stringUtil.toString(mappedToInput));
        
        this.logUtil.putF(stringBuffer.toString(), this, "InputMapping::remove");
        
        final BasicArrayList list = this.getMappedInput(input);
        if(list.contains(mappedToInput))
        {
            list.remove(mappedToInput);
            this.mappedList.remove(mappedToInput);
            
            stringBuffer.delete(0, stringBuffer.length());
            stringBuffer.append("Removed: ");
            stringBuffer.append(stringUtil.toString(input));
            stringBuffer.append(" == ");
            stringBuffer.append(stringUtil.toString(mappedToInput));
            
            this.logUtil.putF(stringBuffer.toString(), this, "InputMapping::remove");
        }
        else
        {
            stringBuffer.delete(0, stringBuffer.length());
            stringBuffer.append("Not Deleted: ");
            stringBuffer.append(stringUtil.toString(input));
            stringBuffer.append(" == ");
            stringBuffer.append(stringUtil.toString(mappedToInput));
            
            this.logUtil.putF(stringBuffer.toString(), this, "InputMapping::remove");
        }
    }
    
    @JsMethod
    public void addMapping(final InputToGameKeyMapping inputToGameKeyMapping)
    throws Exception
    {
        throw new Exception(this.commonStrings.NOT_IMPLEMENTED);
    }
    
    @JsMethod
    public void add(final Input input, final Input mappedToInput)
    {
        //this.logUtil.putF(this.commonStrings.START_LABEL + input + " == " + mappedToInput, this, "InputMapping::add");
        BasicArrayList list = this.getMappedInput(input);
        if(!list.contains(mappedToInput))
        {
            list.add(mappedToInput);
            this.mappedList.add(mappedToInput);
        }
    }

    @JsMethod
    public boolean isMapped(final Input input)
    {
        return this.mappedList.contains(input);
    }
    
    @JsMethod
    public BasicArrayList getMappedInput(final Input id)
    {
        final Object mappingInputCanBeNullList = this.hashtable.get(id);

        if (mappingInputCanBeNullList == null)
        {
            final BasicArrayList list = new BasicArrayListD();
            this.hashtable.put(id, list);
            return list;
        }

        return (BasicArrayList) mappingInputCanBeNullList;
    }

    @JsMethod
    public Hashtable getHashtable()
    {
        return this.hashtable;
    }
}
