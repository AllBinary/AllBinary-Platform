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
package org.allbinary.thread;

import org.allbinary.logic.string.CommonStrings;
import org.allbinary.logic.communication.log.PreLogUtil;
import org.allbinary.canvas.RunnableCanvas;
import org.allbinary.game.configuration.feature.Features;
import org.allbinary.game.configuration.feature.HTMLFeatureFactory;
import org.allbinary.game.displayable.canvas.AllBinaryGameCanvas;
import org.allbinary.game.displayable.canvas.DemoCanvas;
import org.allbinary.game.displayable.canvas.RunnableCanvasSingleThreadStartRunnable;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringMaker;

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

                PreLogUtil.put(new StringMaker().append("Using Pseudo Thread for DemoCanvas/AllBinaryGameCanvas under PlayN: ").append(runnable).toString(), this, CommonStrings.getInstance().CONSTRUCTOR);

                ThreadPool primaryThreadPool = PrimaryThreadPool.getInstance();

                primaryThreadPool.runTask(demoGameSingleThreadStartRunnable);
                //currentDisplayableFactory.setRunnable(demoGameSingleThreadStartRunnable);
            } else
            {
                PreLogUtil.put(new StringMaker().append("Using Pseudo Thread for Runnable under PlayN: ").append(runnable).toString(), this, CommonStrings.getInstance().CONSTRUCTOR);

                ThreadPool primaryThreadPool = PrimaryThreadPool.getInstance();

                primaryThreadPool.runTask(runnable);
            }
        }

        final Thread thread = new Thread(runnable);
        LogUtil.put(LogFactory.getInstance(thread.toString(), this, CommonStrings.getInstance().CONSTRUCTOR));
        return thread;
    }
}
