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
package org.allbinary.game.score;

import org.allbinary.logic.string.CommonStrings;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;

/**
 *
 * @author user
 */
public class HighScoresUpdateRunnable
        implements Runnable
{
    private final CommonStrings commonStrings = CommonStrings.getInstance();
    
   private final HighScoresCompositeInterface highScoresCompositeInterface;

   public HighScoresUpdateRunnable(HighScoresCompositeInterface highScoresCompositeInterface)
   {
      this.highScoresCompositeInterface = highScoresCompositeInterface;
   }

   public void run()
   {
      try
      {
         LogUtil.put(LogFactory.getInstance(commonStrings.START_RUNNABLE, this, commonStrings.RUN));

         this.highScoresCompositeInterface.setHighScores();

         LogUtil.put(LogFactory.getInstance(commonStrings.END_RUNNABLE, this, commonStrings.RUN));
      } catch (Exception e)
      {
         LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, commonStrings.RUN, e));
      }
   }
}
