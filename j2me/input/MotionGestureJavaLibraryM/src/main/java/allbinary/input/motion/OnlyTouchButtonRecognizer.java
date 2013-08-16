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
package allbinary.input.motion;

import allbinary.input.motion.button.TouchButtonRecognizer;

/**
 * 
 * @author user
 */
public class OnlyTouchButtonRecognizer extends MotionRecognizer
{
    private final TouchButtonRecognizer touchButtonRecognizer;

    private boolean touchButtonProcessing = false;

    public OnlyTouchButtonRecognizer()
    {
        this.touchButtonRecognizer = new TouchButtonRecognizer();
    }
    
    public void processStartMotionEvent(int x, int y, int modifiers)
            throws Exception
    {
        //TWB - should now actually occur for more than one button
        //Only one touch button can be pressed by a pointer !touchButtonProcessing &&
        if (this.touchButtonRecognizer.pressTouchButtonInput(x, y))
        {
            touchButtonProcessing = true;
        }
    }

    public void processEndMotionEvent(int x, int y, int modifiers)
            throws Exception
    {
        if (touchButtonProcessing)
        {
            touchButtonProcessing = false;
            //else should never occur
        }

        if(this.touchButtonRecognizer.releaseTouchButtonInput(x, y))
        {
            //this.touchButtonRecognizer.releaseAll();
            return;
        }
    }

    public void processDraggedMotionEvent(int x, int y, int modifiers)
            throws Exception
    {
        //Allows sliding from one button to the next without releasing but only if a button press was already processing
        if (touchButtonProcessing)
        {
            this.touchButtonRecognizer.pressTouchButtonInput(x, y);
        }
    }
}
