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
package org.allbinary.media.image.comparison.motion;

import org.allbinary.logic.util.event.BasicEventListenerInterface;

public interface MotionRectanglesResultsListener
   extends BasicEventListenerInterface
{
   public void onMotionRectanglesImageComparisonResultsEvent(
      MotionRectanglesResultsEvent
         motionRectanglesImageComparisonResultsEvent);
}
