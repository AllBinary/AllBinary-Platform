package org.allbinary.android;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

public class ViewSwapper
{
    public static final View[] NULL_VIEW_ARRAY = new View[0];

    private final int rootViewId;
    private final Activity activity;

    private int currentMainViewId = -1;
    private View[] mainViewArray = NULL_VIEW_ARRAY;
    
    private View[] topViewArray = NULL_VIEW_ARRAY;

    public ViewSwapper(Activity activity, int rootViewId)
    {
        this.activity = activity;
        this.rootViewId = rootViewId;
    }

    public void setMainViews(View[] viewArray)
    {
        this.mainViewArray = viewArray;
    }

    /**
     * @param topViewArray the topViewArray to set
     */
    public void setTopViewArray(View[] topViewArray)
    {
        this.topViewArray = topViewArray;
    }

    public void setMainView(int id)
    {
        ViewGroup viewGroup = (ViewGroup) this.activity.findViewById(rootViewId);

        if (id != this.currentMainViewId)
        {
            for (int index = 0; index < this.topViewArray.length; index++)
            {
                viewGroup.removeView(this.topViewArray[index]);
            }

            for (int index = 0; index < this.mainViewArray.length; index++)
            {
                if (id != this.mainViewArray[index].getId())
                {
                    viewGroup.removeView(this.mainViewArray[index]);
                }
            }

            for (int index = 0; index < this.mainViewArray.length; index++)
            {
                if (id == this.mainViewArray[index].getId())
                {
                    viewGroup.addView(this.mainViewArray[index]);
                    this.currentMainViewId = id;
                }
            }

            for (int index = 0; index < this.topViewArray.length; index++)
            {
                viewGroup.addView(this.topViewArray[index]);
            }
        }
    }

}
