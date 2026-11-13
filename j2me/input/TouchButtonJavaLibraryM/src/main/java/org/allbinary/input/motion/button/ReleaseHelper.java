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

import org.allbinary.game.input.event.GameKeyEvent;

class ReleaseHelper
{
    protected final TouchButtonRecognizer touchButtonRecognizer;

    public ReleaseHelper(final TouchButtonRecognizer touchButtonRecognizer)
    {
        this.touchButtonRecognizer = touchButtonRecognizer;
    }

    public void release(final TouchButtonInput touchButtonInput, final int deviceId) throws Exception
    {
        TouchButtonInput nextTouchButtonInput;
        GameKeyEvent gameKeyEvent;
        for (int index = this.touchButtonRecognizer.currentlyPressedTouchButtonSingleton.size() - 1; index >= 0; index--)
        {
            nextTouchButtonInput = this.touchButtonRecognizer.currentlyPressedTouchButtonSingleton.get(index);

            gameKeyEvent = nextTouchButtonInput.getGameKeyEvent();

            this.touchButtonRecognizer.upGameKeyEventHandler.fireEvent(gameKeyEvent);
            this.touchButtonRecognizer.upGameKeyEventHandler.getInstanceForDevice(deviceId).fireEvent(gameKeyEvent);

            this.touchButtonRecognizer.currentlyPressedTouchButtonSingleton.removeAt(index);

          //TWB - Debugging
            //this.touchButtonRecognizer.currentlyPressedTouchButtonSingleton
              //      .releaseAndFiredAssociated(nextTouchButtonInput);
        }
    }
}
