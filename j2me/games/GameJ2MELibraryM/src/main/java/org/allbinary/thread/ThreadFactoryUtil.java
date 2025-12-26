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

import org.allbinary.J2MEUtil;
import org.allbinary.canvas.RunnableCanvas;
import org.allbinary.game.displayable.canvas.AllBinaryGameCanvas;
import org.allbinary.game.displayable.canvas.DemoCanvas;
import org.allbinary.game.displayable.canvas.GameCanvasRunnableInterface;
import org.allbinary.game.displayable.canvas.RunnableCanvasSingleThreadStartRunnable;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.communication.log.PreLogUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.string.CommonStrings;

public class ThreadFactoryUtil
{
    protected final LogUtil logUtil = LogUtil.getInstance();

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
        final CommonStrings commonStrings = CommonStrings.getInstance();

        if (J2MEUtil.isHTML())
        {
            if (type == DemoCanvas.TYPE || type == AllBinaryGameCanvas.TYPE)
            {
                //GameCanvasRunnableInterface gameCanvasRunnableInterface = this.demoGameMidlet.getGameCanvasRunnableInterface();

                final RunnableCanvasSingleThreadStartRunnable demoGameSingleThreadStartRunnable =
                        new RunnableCanvasSingleThreadStartRunnable((RunnableCanvas) runnable);

                PreLogUtil.put(new StringMaker().append("Using Pseudo Thread for DemoCanvas/AllBinaryGameCanvas under PlayN/HTML5: ").append(StringUtil.getInstance().toString(runnable)).toString(), this, commonStrings.CONSTRUCTOR);

                final ThreadPool primaryThreadPool = PrimaryThreadPool.getInstance();

                primaryThreadPool.runTask(demoGameSingleThreadStartRunnable);
                //currentDisplayableFactory.setRunnable(demoGameSingleThreadStartRunnable);
            } else
            {
                PreLogUtil.put(new StringMaker().append("Using Pseudo Thread for Runnable under PlayN/HTML5: ").append(StringUtil.getInstance().toString(runnable)).toString(), this, commonStrings.CONSTRUCTOR);

                ThreadPool primaryThreadPool = PrimaryThreadPool.getInstance();

                primaryThreadPool.runTask(runnable);
            }
        }

        final Thread thread = new Thread(runnable, runnable.toString());
        logUtil.put(thread.toString(), this, commonStrings.CONSTRUCTOR);
        return thread;
    }
    
    public void start(final Thread thread) {
        
        thread.start();

    }

}
