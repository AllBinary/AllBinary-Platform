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
import org.allbinary.game.displayable.canvas.GameCanvasRunnableInterface;
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

    public Thread getInstance(final GameCanvasRunnableInterface runnable)
    {
        return this.getInstance(runnable, runnable.getType());
    }

    public Thread getInstance(final ABRunnable runnable)
    {
        return this.getInstance(runnable, runnable.getType());
    }
    
    private Thread getInstance(final Runnable runnable, final int type)
    {
        final Features features = Features.getInstance();

        if (features.isDefault(HTMLFeatureFactory.getInstance().HTML))
        {
            if (type == DemoCanvas.TYPE || type == AllBinaryGameCanvas.TYPE)
            {
                //GameCanvasRunnableInterface gameCanvasRunnableInterface = this.demoGameMidlet.getGameCanvasRunnableInterface();

                final RunnableCanvasSingleThreadStartRunnable demoGameSingleThreadStartRunnable =
                        new RunnableCanvasSingleThreadStartRunnable((RunnableCanvas) runnable);

                PreLogUtil.put(new StringMaker().append("Using Pseudo Thread for DemoCanvas/AllBinaryGameCanvas under PlayN/HTML5: ").append(runnable).toString(), this, CommonStrings.getInstance().CONSTRUCTOR);

                final ThreadPool primaryThreadPool = PrimaryThreadPool.getInstance();

                primaryThreadPool.runTask(demoGameSingleThreadStartRunnable);
                //currentDisplayableFactory.setRunnable(demoGameSingleThreadStartRunnable);
            } else
            {
                PreLogUtil.put(new StringMaker().append("Using Pseudo Thread for Runnable under PlayN/HTML5: ").append(runnable).toString(), this, CommonStrings.getInstance().CONSTRUCTOR);

                ThreadPool primaryThreadPool = PrimaryThreadPool.getInstance();

                primaryThreadPool.runTask(runnable);
            }
        }

        final Thread thread = new Thread(runnable, runnable.toString());
        LogUtil.put(LogFactory.getInstance(thread.toString(), this, CommonStrings.getInstance().CONSTRUCTOR));
        return thread;
    }
    
    public void start(final Thread thread) {
        
        thread.start();

    }

}
