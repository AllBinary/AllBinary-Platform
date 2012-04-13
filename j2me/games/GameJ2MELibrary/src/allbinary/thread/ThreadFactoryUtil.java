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
package allbinary.thread;

import abcs.logic.basic.string.CommonStrings;
import abcs.logic.communication.log.PreLogUtil;
import allbinary.canvas.RunnableCanvas;
import allbinary.game.configuration.feature.Features;
import allbinary.game.configuration.feature.HTMLFeatureFactory;
import allbinary.game.displayable.canvas.AllBinaryGameCanvas;
import allbinary.game.displayable.canvas.DemoCanvas;
import allbinary.game.displayable.canvas.RunnableCanvasSingleThreadStartRunnable;

public class ThreadFactoryUtil
{
    private static final ThreadFactoryUtil instance = new ThreadFactoryUtil();

    public static ThreadFactoryUtil getInstance()
    {
        return instance;
    }

    public Thread getInstance(Runnable runnable)
    {
        Features features = Features.getInstance();

        if (features.isDefault(HTMLFeatureFactory.getInstance().HTML))
        {
            if (runnable instanceof DemoCanvas
                    || runnable instanceof AllBinaryGameCanvas)
            {
                //GameCanvasRunnableInterface gameCanvasRunnableInterface = 
                //      this.demoGameMidlet.getGameCanvasRunnableInterface();

                RunnableCanvasSingleThreadStartRunnable demoGameSingleThreadStartRunnable =
                        new RunnableCanvasSingleThreadStartRunnable((RunnableCanvas) runnable);

                PreLogUtil.put("Using Pseudo Thread for DemoCanvas/AllBinaryGameCanvas under PlayN: " + runnable, this, CommonStrings.getInstance().CONSTRUCTOR);

                ThreadPool primaryThreadPool = PrimaryThreadPool.getInstance();

                primaryThreadPool.runTask(demoGameSingleThreadStartRunnable);
                //currentDisplayableFactory.setRunnable(demoGameSingleThreadStartRunnable);
            } else
            {
                PreLogUtil.put("Using Pseudo Thread for Runnable under PlayN: " + runnable, this, CommonStrings.getInstance().CONSTRUCTOR);

                ThreadPool primaryThreadPool = PrimaryThreadPool.getInstance();

                primaryThreadPool.runTask(runnable);
            }
        }

        return new Thread(runnable);
    }
}
