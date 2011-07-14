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
package allbinary.input.motion.button;

import abcs.logic.basic.string.CommonSeps;
import abcs.logic.basic.string.StringUtil;
import allbinary.graphics.GPoint;

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
        StringBuilder stringBuffer = new StringBuilder();
        
        stringBuffer.append(touchButtonInput.toString());
        stringBuffer.append(CommonSeps.getInstance().SPACE);
        stringBuffer.append(append);

        this.append = stringBuffer.toString();
        this.string = listString + this.append;
    }

    private void append(String append)
    {
        this.append = this.append + append;
        this.string = listString + this.append;
    }
    
    public void clearLog(int x, int y)
    {
        this.append = GPoint.toStringStatic(x, y) + CommonSeps.getInstance().SPACE;
        this.string = listString + this.append;
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

        listString = list.toString();
        this.string = listString + this.append;
        
        return touchButtonInput;
    }

    public boolean remove(TouchButtonInput touchButtonInput)
    { 
        boolean isRemoved = super.remove(touchButtonInput);

        listString = list.toString();
        this.string = listString + this.append;

        return isRemoved;
    }
        
    public void add(TouchButtonInput touchButtonInput)
    {
        super.add(touchButtonInput);
        listString = list.toString();
        this.string = listString + this.append;
    }
    
    public String toString()
    {
        return string;
    }    
}
