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
package allbinary.game.score;

import abcs.logic.basic.string.CommonStrings;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;

/**
 *
 * @author user
 */
public class HighScoresUpdateRunnable
        implements Runnable
{
   private final HighScoresCompositeInterface highScoresCompositeInterface;

   public HighScoresUpdateRunnable(HighScoresCompositeInterface highScoresCompositeInterface)
   {
      this.highScoresCompositeInterface = highScoresCompositeInterface;
   }

   public void run()
   {
      try
      {
         LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START_RUNNABLE, this, CommonStrings.getInstance().RUN));

         this.highScoresCompositeInterface.setHighScores();

         LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().END_RUNNABLE, this, CommonStrings.getInstance().RUN));
      } catch (Exception e)
      {
         LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, CommonStrings.getInstance().RUN, e));
      }
   }
}
