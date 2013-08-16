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
package org.allbinary.game.testgamedemo;

import org.allbinary.game.testgamedemo.canvas.TestGameDemoGameCanvas;
import org.allbinary.game.testgamedemo.canvas.TestGameDemoInputMappingHelpPaintable;
import org.allbinary.game.testgamedemo.canvas.TestGameDemoSoftwareInfo;
import org.allbinary.game.testgamedemo.canvas.TestGameDemoStartCanvas;
import org.allbinary.game.testgamedemo.layer.TestGameDemoLayerManager;
import org.allbinary.media.audio.TestGameDemoSoundsFactoryFactory;

import abcs.logic.basic.string.CommonStrings;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import abcs.logic.communication.log.PreLogUtil;
import allbinary.game.GameInfo;
import allbinary.game.GameMode;
import allbinary.game.GameTypeFactory;
import allbinary.game.PlayerTypesFactory;
import allbinary.game.displayable.canvas.GameCanvasRunnableInterface;
import allbinary.game.layer.AllBinaryGameLayerManager;
import allbinary.game.midlet.LicenseLevelUtil;
import allbinary.game.midlet.LicenseLoadingTypeFactory;
import allbinary.game.midlet.SpecialDemoGameMidlet;
import allbinary.game.paint.help.HelpPaintable;
import allbinary.game.score.BasicHighScoresFactory;
import allbinary.game.score.HighScoresPaintable;
import allbinary.game.score.displayable.HighScoresCanvas;
import allbinary.media.audio.AllBinaryMediaManagerShutdown;

/**
 * @author user
 */
public class TestGameDemoMIDlet extends
   SpecialDemoGameMidlet
   //DemoGameMidlet
{
   public TestGameDemoMIDlet()
   {
       super(LicenseLoadingTypeFactory.getIntance().OTHER);
       //this.setSaveGameForm(SaveGameForm.getInstance(this, "Save Game"));
   }
   
   protected HelpPaintable getHelpPaintable()
   throws Exception
   {
       //return TestGameDemoHelpPaintable.getInstance();
       return TestGameDemoInputMappingHelpPaintable.getInstance();
   }
   
   protected GameCanvasRunnableInterface createDemoGameCanvasRunnableInterface() throws Exception
   {
      return new TestGameDemoStartCanvas(this);
   }

   protected GameCanvasRunnableInterface createGameCanvasRunnableInterface(
		   AllBinaryGameLayerManager allBinaryGameLayerManager) throws Exception
   {
	   return new TestGameDemoGameCanvas(this, allBinaryGameLayerManager);
   }
   
   protected HighScoresCanvas createHighScoresCanvas() throws Exception
   {
       return new HighScoresCanvas(this,
               this.createGameLayerManager(),
               new HighScoresPaintable(),
               new BasicHighScoresFactory(TestGameDemoSoftwareInfo.getInstance()));
   }

   public int getHighestLevel()
   {
	   PreLogUtil.put("******************Demo Level Limited To: 6", this, "getMaxLevel");
       return LicenseLevelUtil.getInstance().getMaxLevel(TestGameDemoLayerManager.MAX_LEVEL, 6);
   }

   protected AllBinaryGameLayerManager createGameLayerManager()
   {
       GameInfo gameInfo = new GameInfo(
               GameTypeFactory.getInstance().SINGLE_PLAYER, GameMode.SERVER,
               PlayerTypesFactory.getInstance().PLAYER_TYPE_ONE,
               this.getHighestLevel(), 1);

       return new TestGameDemoLayerManager(gameInfo);
   }

   /*
   protected void mediaInit() throws Exception
   {
   }
   */

   protected void mediaShutdown() throws Exception
   {
        //PreLogUtil.put(CommonStrings.getInstance().START, this, "mediaShutdown - postStopGameCanvasRunnableInterface");

        LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START, this,
                "mediaShutdown - postStopGameCanvasRunnableInterface"));

        AllBinaryMediaManagerShutdown.shutdown(
           TestGameDemoSoundsFactoryFactory.getInstance());

        LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().END, this,
                "mediaShutdown - postStopGameCanvasRunnableInterface"));
   }
}