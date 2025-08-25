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

import org.allbinary.game.input.Input;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.string.CommonLabels;
import org.allbinary.string.CommonStrings;
import org.allbinary.util.BasicArrayList;

public class InputMapping
{
    protected final LogUtil logUtil = LogUtil.getInstance();
    
    protected final CommonStrings commonStrings = CommonStrings.getInstance();

   //TWB - Use MapList someday
    private final Hashtable hashtable = new Hashtable();
    private final BasicArrayList mappedList = new BasicArrayList();

    protected InputMapping()
    {
    }

    public int getTotalMapped()
    {
        return this.hashtable.size();
    }
    
    protected boolean isDefaultNew()
    {
        return false;
    }
    
    protected InputToGameKeyMapping getDefault()
    {
        return InputToGameKeyMapping.NULL_INPUT_TO_GAME_KEY_MAPPING;
    }
    
    protected void removeAll()
    {
        this.hashtable.clear();
        this.mappedList.clear();
    }

    public void remove(final Input input, final Input mappedToInput)
    {
        StringMaker stringBuffer = new StringMaker();
        
        final StringUtil stringUtil = StringUtil.getInstance();
        
        stringBuffer.append(CommonLabels.getInstance().START_LABEL);
        stringBuffer.append(stringUtil.toString(input));
        stringBuffer.append(" == ");
        stringBuffer.append(stringUtil.toString(mappedToInput));
        
        logUtil.put(stringBuffer.toString(), this, "InputMapping::remove");
        
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
            
            logUtil.put(stringBuffer.toString(), this, "InputMapping::remove");
        }
        else
        {
            stringBuffer.delete(0, stringBuffer.length());
            stringBuffer.append("Not Deleted: ");
            stringBuffer.append(stringUtil.toString(input));
            stringBuffer.append(" == ");
            stringBuffer.append(stringUtil.toString(mappedToInput));
            
            logUtil.put(stringBuffer.toString(), this, "InputMapping::remove");
        }
    }
    
    public void add(final InputToGameKeyMapping inputToGameKeyMapping)
    throws Exception
    {
        throw new Exception(commonStrings.NOT_IMPLEMENTED);
    }
    
    public void add(final Input input, final Input mappedToInput)
    {
        //logUtil.put(commonStrings.START_LABEL + input + " == " + mappedToInput, this, "InputMapping::add");
        BasicArrayList list = this.getMappedInput(input);
        if(!list.contains(mappedToInput))
        {
            list.add(mappedToInput);
            this.mappedList.add(mappedToInput);
        }
    }

    public boolean isMapped(final Input input)
    {
        return this.mappedList.contains(input);
    }
    
    public BasicArrayList getMappedInput(final Input id)
    {
        final Object mappingInputCanBeNullList = this.hashtable.get(id);

        if (mappingInputCanBeNullList == null)
        {
            final BasicArrayList list = new BasicArrayList();
            this.hashtable.put(id, list);
        }

        return (BasicArrayList) mappingInputCanBeNullList;
    }

    public Hashtable getHashtable()
    {
        return hashtable;
    }
}
