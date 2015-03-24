package org.allbinary;

import org.allbinary.game.testgamedemo.R;

public class AndroidResources
{
    private static final AndroidResources instance = new AndroidResources();
    
    /**
     * @return the instance
     */
    public static AndroidResources getInstance()
    {
        return instance;
    }
    
    public R.drawable drawable = new R.drawable();

    public R.id id = new R.id();

    public R.layout layout = new R.layout();

    public R.raw raw = new R.raw();

    public R.string string = new R.string();
}
