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

import jsinterop.annotations.JsType;

import org.allbinary.util.BasicArrayList;
import org.allbinary.util.BasicArrayListD;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsProperty;


@JsType
public class CurrentlyPressedTouchButtonSingleton
{
    private static final CurrentlyPressedTouchButtonSingleton instance =
        new CurrentlyPressedTouchButtonSingleton();
    
    @JsMethod
    public static CurrentlyPressedTouchButtonSingleton getInstance()
    {
        return CurrentlyPressedTouchButtonSingleton.instance;
    }
    
    @JsProperty
    protected final BasicArrayList list = new BasicArrayListD();
    
    @JsConstructor
    protected CurrentlyPressedTouchButtonSingleton()
    {
        
    }
    
    @JsMethod
    public int size()
    {
        return this.list.size();
    }

    @JsMethod
    public TouchButtonInput removeAt(int index)
    {
        return (TouchButtonInput) this.list.removeAt(index);
    }
    
    @JsMethod
    public boolean remove(TouchButtonInput touchButtonInput)
    { 
        return this.list.remove(touchButtonInput);
    }
    
    @JsMethod
    public TouchButtonInput get(int index)
    {
        return (TouchButtonInput) this.list.objectArray[index];
    }
    
    @JsMethod
    public boolean contains(TouchButtonInput touchButtonInput)
    {
        return this.list.contains(touchButtonInput);
    }

    @JsMethod
    public void add(TouchButtonInput touchButtonInput)
    {
        this.list.add(touchButtonInput);
    }
}
