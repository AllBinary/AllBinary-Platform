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

import org.allbinary.logic.NullUtil;
import jsinterop.annotations.JsMethod;

/**
 *
 * @author user
 */

@JsType
public class PlatformKeyFactory {

    private static Object instance = NullUtil.getInstance().NULL_OBJECT;

    @JsMethod
    public static PlatformKeyFactory getInstance()
    {
        if(PlatformKeyFactory.instance == NullUtil.getInstance().NULL_OBJECT) {
            PlatformKeyFactory.instance = new PlatformKeyFactory();
        }

        return (PlatformKeyFactory) PlatformKeyFactory.instance;
    }

    @JsMethod
    public String getString(int keyCode)
    {
        return PCGameKey.getString(keyCode);
    }

    @JsMethod
    public boolean isSubmission(Input input)
    {
        return PCKeyFactory.getInstance().isSubmission(input);
    }

    @JsMethod
    public boolean isDelete(Input input)
    {
        return PCKeyFactory.getInstance().isDelete(input);
    }

    @JsMethod
    public boolean isBackSpace(Input input)
    {
        return PCKeyFactory.getInstance().isBackSpace(input);
    }

    @JsMethod
    public boolean isUp(Input input)
    {
        return PCKeyFactory.getInstance().isUp(input);
    }

    @JsMethod
    public boolean isDown(Input input)
    {
        return PCKeyFactory.getInstance().isDown(input);
    }

    @JsMethod
    public boolean isEnter(Input input)
    {
        return PCKeyFactory.getInstance().isEnter(input);
    }

    @JsMethod
    public boolean isLeft(Input input)
    {
        return PCKeyFactory.getInstance().isLeft(input);
    }

    @JsMethod
    public boolean isRight(Input input)
    {
        return PCKeyFactory.getInstance().isRight(input);
    }

}
