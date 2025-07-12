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
package org.allbinary.input.automation.module;

import org.allbinary.input.media.image.capture.ScreenCaptureImagesWorker;
import org.allbinary.media.image.comparison.ImageComparisonWorker;
import org.allbinary.media.image.comparison.SaveImageComparisonWorker;
import org.allbinary.media.image.comparison.motion.MotionRectanglesWorker;
import org.allbinary.media.image.comparison.motion.SaveMotionRectanglesResultsWorker;
import org.allbinary.media.image.comparison.motion.TestInputForMotionRectanglesResultsWorker;

public class TestInputAutomationWorker
{
   private ScreenCaptureImagesWorker captureWorker;

   private ImageComparisonWorker imageComparisonWorker;

   private MotionRectanglesWorker motionRectanglesWorker;
   
   private SaveImageComparisonWorker changedPixelsImageComparisonResultsWorker;
   
   private SaveMotionRectanglesResultsWorker saveMotionRectanglesAsImagesWorker;

   private TestInputForMotionRectanglesResultsWorker 
      moveMouseToFirstMotionRectanglesResultsWorker;
    
    public TestInputAutomationWorker() throws Exception
    {
      this.captureWorker = new ScreenCaptureImagesWorker();
      //this.imageComparisonWorker = new ImageComparisonWorker();
      //this.motionRectanglesWorker = new MotionRectanglesWorker();
      
      this.changedPixelsImageComparisonResultsWorker = 
         new SaveImageComparisonWorker();
      this.saveMotionRectanglesAsImagesWorker = 
         new SaveMotionRectanglesResultsWorker();
      this.moveMouseToFirstMotionRectanglesResultsWorker = 
         new TestInputForMotionRectanglesResultsWorker();

      this.motionRectanglesWorker.addListener(this.saveMotionRectanglesAsImagesWorker);
      this.motionRectanglesWorker.addListener(this.moveMouseToFirstMotionRectanglesResultsWorker);
      this.imageComparisonWorker.addListener(this.changedPixelsImageComparisonResultsWorker);
      
      this.imageComparisonWorker.addListener(this.motionRectanglesWorker);
      this.captureWorker.addListener(this.imageComparisonWorker);
        
    }
    
}
