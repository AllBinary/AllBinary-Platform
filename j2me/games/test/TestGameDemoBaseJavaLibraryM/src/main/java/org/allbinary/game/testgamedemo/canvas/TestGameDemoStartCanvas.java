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
package org.allbinary.game.testgamedemo.canvas;

import javax.microedition.lcdui.CommandListener;

import org.allbinary.game.testgamedemo.init.TestGameDemoStaticInitializerFactory;
import org.allbinary.game.testgamedemo.layer.TestGameDemoLayerManager;

import abcs.logic.communication.log.PreLogUtil;
import allbinary.game.GameInfo;
import allbinary.game.GameMode;
import allbinary.game.GameTypeFactory;
import allbinary.game.PlayerTypesFactory;
import allbinary.game.configuration.GameSpeed;
import allbinary.game.displayable.canvas.DemoCanvas;
import allbinary.game.displayable.canvas.GameCanvasRunnableInterface;
import allbinary.game.layer.AllBinaryGameLayerManager;
import allbinary.game.paint.ColorFillPaintableFactory;
import allbinary.game.score.BasicHighScoresFactory;
import allbinary.graphics.color.BasicColorFactory;
import allbinary.graphics.form.FormPaintable;
import allbinary.graphics.paint.NullInitUpdatePaintable;
import allbinary.graphics.paint.NullPaintable;

public class TestGameDemoStartCanvas extends DemoCanvas
{
    private final int WAIT = ((GameSpeed.getInstance().getDelay() * 3) >> 1);

    public TestGameDemoStartCanvas(CommandListener commandListener) throws Exception
    {
        super(commandListener, new BasicHighScoresFactory(TestGameDemoSoftwareInfo.getInstance()),
                NullPaintable.getInstance(), NullInitUpdatePaintable.getInstance(),
                new TestGameDemoStaticInitializerFactory(), false);

        this.setWait(WAIT);
    }

    public void initPostPaint() throws Exception
    {
        this.setBasicGameDemoPaintable(
                new TestDemoPaintable(new FormPaintable(this.getMenuForm()))
                );

        this.setSpecialAnimationInterface(
                TitleAnimationFactory.getInstance());

        this.setDefaultPaintableInterface(
                ColorFillPaintableFactory.getInstance(BasicColorFactory.getInstance().RED));
    }

    protected int getNextRandom()
    {
    	PreLogUtil.put("******************Demo Next Random Is Always 1", this, "getNextRandom");
        // return MyRandom.getAbsoluteNextInt(Integer.MAX_VALUE / 10) + 1;
        return 1;
    }

    protected AllBinaryGameLayerManager createGameLayerManager(int randomValue) throws Exception
    {
        GameInfo gameInfo = new GameInfo(GameTypeFactory.getInstance().BOT, GameMode.SERVER, PlayerTypesFactory
                .getInstance().PLAYER_TYPE_ONE, TestGameDemoLayerManager.MAX_LEVEL, randomValue);

        return new TestGameDemoLayerManager(gameInfo);
    }

    public GameCanvasRunnableInterface createRunnable(int randomValue) throws Exception
    {
        return new TestGameDemoGameCanvas(this.createGameLayerManager(this.getNextRandom()));
    }
}