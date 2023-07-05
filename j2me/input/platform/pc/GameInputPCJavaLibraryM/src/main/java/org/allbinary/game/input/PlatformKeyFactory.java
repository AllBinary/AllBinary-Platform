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

/**
 *
 * @author user
 */
public class PlatformKeyFactory {

    private static final PlatformKeyFactory SINGLETON = new PlatformKeyFactory();

    public static PlatformKeyFactory getInstance()
    {
        return SINGLETON;
    }

    public String getString(int keyCode)
    {
        return PCGameKey.getString(keyCode);
    }

    public boolean isSubmission(Input input)
    {
        return PCKeyFactory.getInstance().isSubmission(input);
    }

    public boolean isDelete(Input input)
    {
        return PCKeyFactory.getInstance().isDelete(input);
    }

    public boolean isBackSpace(Input input)
    {
        return PCKeyFactory.getInstance().isBackSpace(input);
    }

    public boolean isUp(Input input)
    {
        return PCKeyFactory.getInstance().isUp(input);
    }

    public boolean isDown(Input input)
    {
        return PCKeyFactory.getInstance().isDown(input);
    }

    public boolean isEnter(Input input)
    {
        return PCKeyFactory.getInstance().isEnter(input);
    }

    public boolean isLeft(Input input)
    {
        return PCKeyFactory.getInstance().isLeft(input);
    }

    public boolean isRight(Input input)
    {
        return PCKeyFactory.getInstance().isRight(input);
    }

}
