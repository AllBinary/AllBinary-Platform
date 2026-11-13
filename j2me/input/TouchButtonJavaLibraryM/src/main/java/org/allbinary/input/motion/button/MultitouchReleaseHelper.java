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

class MultitouchReleaseHelper extends ReleaseHelper
{

    public MultitouchReleaseHelper(final TouchButtonRecognizer touchButtonRecognizer)
    {
        super(touchButtonRecognizer);
    }

    // This releases an associated button even if it has not been released
    @Override
    public void release(final TouchButtonInput touchButtonInput, final int deviceId) throws Exception
    {
        final TouchButtonInput cancelTouchButtonInput = CancelTouchButtonInputFactory.getInstance().getCancel(touchButtonInput);

        TouchButtonInput nextTouchButtonInput;
        GameKeyEvent gameKeyEvent;
        for (int index = this.touchButtonRecognizer.currentlyPressedTouchButtonSingleton.size() - 1; index >= 0; index--)
        {
            nextTouchButtonInput =
                this.touchButtonRecognizer.currentlyPressedTouchButtonSingleton.get(index);

            // the following should never happen nextTouchButtonInput ==
            // touchButtonInput ||
            if (cancelTouchButtonInput == nextTouchButtonInput)
            {
                // this.logUtil.putF(
                // "Release Associated Button Press", this, // "processTouchButtonInput");

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
}
