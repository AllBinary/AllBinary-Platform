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
package org.allbinary.input.motion.button;

import org.allbinary.graphics.GPoint;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.string.CommonSeps;

public class CurrentlyPressedTouchButtonSingletonDebug 
    extends CurrentlyPressedTouchButtonSingleton
{
    private static final CurrentlyPressedTouchButtonSingletonDebug instance =
        new CurrentlyPressedTouchButtonSingletonDebug();
    
    public static CurrentlyPressedTouchButtonSingleton getInstance()
    {
        return instance;
    }
    
    private String string = StringUtil.getInstance().EMPTY_STRING;
    private String listString = StringUtil.getInstance().EMPTY_STRING;
    private String append = StringUtil.getInstance().EMPTY_STRING;

    private final String PRESSED_AND_FIRED = "pressed & fired";
    private final String PRESSED_AND_NOT_FIRED = "pressed in button but not fired";
    
    private final String ASSOCIATED_RELEASED_AND_FIRED = "assoc. rel & fired";
    private final String RELEASED_AND_FIRED = "rel & fired 1";
    private final String RELEASED_AND_FIRED_2 = "rel & fired 2";
    private final String RELEASED_AND_NOT_FIRED = "rel & not fired";
    
    private void append(String append, TouchButtonInput touchButtonInput)
    {
        StringMaker stringBuffer = new StringMaker();
        
        stringBuffer.append(touchButtonInput.toString());
        stringBuffer.append(CommonSeps.getInstance().SPACE);
        stringBuffer.append(append);

        this.append = stringBuffer.toString();
        this.string = new StringMaker().append(listString).append(this.append).toString();
    }

    private void append(String append)
    {
        this.append = new StringMaker().append(this.append).append(append).toString();
        this.string = new StringMaker().append(listString).append(this.append).toString();
    }
    
    public void clearLog(int x, int y)
    {
        this.append = new StringMaker().append(GPoint.toStringStatic(x, y, 0)).append(CommonSeps.getInstance().SPACE).toString();
        this.string = new StringMaker().append(listString).append(this.append).toString();
        //this.append(StringUtil.getInstance());
    }

    public void releaseAndNotFired()
    {
        this.append(RELEASED_AND_NOT_FIRED);
    }

    public void releaseAndFired(TouchButtonInput touchButtonInput)
    {
        this.append(RELEASED_AND_FIRED, touchButtonInput);
    }

    public void releaseAndFired2(TouchButtonInput touchButtonInput)
    {
        this.append(RELEASED_AND_FIRED_2, touchButtonInput);
    }
    
    public void releaseAndFiredAssociated(TouchButtonInput touchButtonInput)
    {
        this.append(ASSOCIATED_RELEASED_AND_FIRED, touchButtonInput);
    }
    
    public void pressedAndFired(TouchButtonInput touchButtonInput)
    {
        this.append(PRESSED_AND_FIRED, touchButtonInput);
    }
    
    public void pressedAndNotFired(TouchButtonInput touchButtonInput)
    {
        this.append(PRESSED_AND_NOT_FIRED, touchButtonInput);
    }
    
    public TouchButtonInput remove(int index)
    {
        TouchButtonInput touchButtonInput = super.remove(index);

        this.listString = list.toString();
        this.string = new StringMaker().append(listString).append(this.append).toString();
        
        return touchButtonInput;
    }

    public boolean remove(TouchButtonInput touchButtonInput)
    { 
        boolean isRemoved = super.remove(touchButtonInput);

        this.listString = list.toString();
        this.string = new StringMaker().append(listString).append(this.append).toString();

        return isRemoved;
    }
        
    public void add(TouchButtonInput touchButtonInput)
    {
        super.add(touchButtonInput);
        this.listString = list.toString();
        this.string = new StringMaker().append(listString).append(this.append).toString();
    }
    
    public String toString()
    {
        return string;
    }    
}
