package org.allbinary.game.input;

/**
 *
 * @author user
 */
public class PlatformKeyFactory {

    private static final PlatformKeyFactory SINGLETON = new PlatformKeyFactory();

    public static PlatformKeyFactory getInstance()
    {
        return PlatformKeyFactory.SINGLETON;
    }
    
    private final AndroidKeyFactory androidKeyFactory = ActivityFractureControllerUtilFactory.getInstance();
    
    public String getString(int keyCode)
    {
        return AndroidGameKey.getString(keyCode);
    }

    public boolean isSubmission(Input input)
    {
        return this.androidKeyFactory.isSubmission(input);
    }

    public boolean isDelete(Input input)
    {
        return this.androidKeyFactory.isDelete(input);
    }

    public boolean isBackSpace(Input input)
    {
        return this.androidKeyFactory.isBackSpace(input);
    }

    public boolean isLeft(Input input)
    {
        return this.androidKeyFactory.isLeft(input);
    }

    public boolean isRight(Input input)
    {
        return this.androidKeyFactory.isRight(input);
    }

    public boolean isUp(Input input)
    {
        return this.androidKeyFactory.isUp(input);
    }

    public boolean isDown(Input input)
    {
        return this.androidKeyFactory.isDown(input);
    }
    
    public boolean isEnter(Input input)
    {
        return this.androidKeyFactory.isEnter(input);
    }
}
