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
package org.allbinary.android.api19;

import android.app.Activity;
import android.view.View;
import org.allbinary.android.api5.ActivityFractureUtilAPI5;

public class ActivityFractureUtilAPI19 extends ActivityFractureUtilAPI5
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

    private static final ActivityFractureUtilAPI19 instance = new ActivityFractureUtilAPI19();

    public static ActivityFractureUtilAPI19 getInstance()
    {
        return instance;
    }

    @Override
    public void setFullScreen(final Activity activity)
    {

        final View decorView = activity.getWindow().getDecorView();
        //API 19-30 View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        //API 14-30 View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        //API 16-30 View.SYSTEM_UI_FLAG_FULLSCREEN
        //API 11-30 setSystemUiVisibility
        final int visibility = View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(visibility);
    }

}

