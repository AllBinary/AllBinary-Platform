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
        return J2MEGameKey.getString(keyCode);
    }

    public boolean isSubmission(Input input)
    {
        return J2MEKeyFactory.getInstance().isSubmission(input);
    }

    public boolean isDelete(Input input)
    {
        return J2MEKeyFactory.getInstance().isDelete(input);
    }

    public boolean isBackSpace(Input input)
    {
        return J2MEKeyFactory.getInstance().isBackSpace(input);
    }

    public boolean isLeft(Input input)
    {
        return J2MEKeyFactory.getInstance().isLeft(input);
    }

    public boolean isRight(Input input)
    {
        return J2MEKeyFactory.getInstance().isRight(input);
    }

    public boolean isUp(Input input)
    {
        return J2MEKeyFactory.getInstance().isUp(input);
    }

    public boolean isDown(Input input)
    {
        return J2MEKeyFactory.getInstance().isDown(input);
    }
    
    public boolean isEnter(Input input)
    {
        return J2MEKeyFactory.getInstance().isEnter(input);
    }
}
