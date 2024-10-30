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
    
    private final AndroidKeyFactory androidKeyFactory = ActivityFractureControllerUtilFactory.getInstance();
    
    public String getString(int keyCode)
    {
        return AndroidGameKey.getString(keyCode);
    }

    public boolean isSubmission(Input input)
    {
        return androidKeyFactory.isSubmission(input);
    }

    public boolean isDelete(Input input)
    {
        return androidKeyFactory.isDelete(input);
    }

    public boolean isBackSpace(Input input)
    {
        return androidKeyFactory.isBackSpace(input);
    }

    public boolean isLeft(Input input)
    {
        return androidKeyFactory.isLeft(input);
    }

    public boolean isRight(Input input)
    {
        return androidKeyFactory.isRight(input);
    }

    public boolean isUp(Input input)
    {
        return androidKeyFactory.isUp(input);
    }

    public boolean isDown(Input input)
    {
        return androidKeyFactory.isDown(input);
    }
    
    public boolean isEnter(Input input)
    {
        return androidKeyFactory.isEnter(input);
    }
}
