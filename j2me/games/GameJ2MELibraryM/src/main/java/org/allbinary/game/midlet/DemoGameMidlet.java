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
package org.allbinary.game.midlet;

import java.util.Hashtable;

import org.allbinary.logic.basic.NotImplemented;
import org.allbinary.logic.basic.string.CommonStrings;
import org.allbinary.logic.basic.string.StringUtil;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.game.displayable.canvas.GameCanvasRunnableInterface;
import org.allbinary.game.layer.AllBinaryGameLayerManager;
import org.allbinary.graphics.canvas.transition.progress.ProgressCanvasFactory;
import org.allbinary.thread.PrimaryThreadPool;

public class DemoGameMidlet extends GameMidlet
{
    public DemoGameMidlet()
    {
    }
    
    protected boolean isContinue() throws Exception
    {
        Hashtable hashtable = this.getStartStateHashtable();
        if (hashtable != null && hashtable.size() > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public int getHighestLevel()
    {
        return 1;
    }
    
    public GameCanvasRunnableInterface createDemoGameCanvasRunnableInterface()
            throws Exception
    {
        throw new Exception(NotImplemented.NAME);
    }

    protected GameCanvasRunnableInterface createGameCanvasRunnableInterface(
            AllBinaryGameLayerManager allBinaryGameLayerManager)
            throws Exception
    {
        throw new Exception(NotImplemented.NAME);
    }
    
    protected GameCanvasRunnableInterface createGameCanvasRunnableInterface()
            throws Exception
    {
        return this.createGameCanvasRunnableInterface(this.createGameLayerManager());
    }
    
    /*
     * protected void mediaInit() throws Exception { throw new Exception("Not
     * Implemented"); }
     */

    public void demoSetup()
    {
    }

    
    public void postDemoSetup()
    {
        
    }
    
    public synchronized void setDemo() throws Exception
    {
        LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START, this, "setDemo"));

        ////TWB - Loading Feature Change - Can remove remark after testing
        ProgressCanvasFactory.getInstance().start();

        PrimaryThreadPool.getInstance().runTask(new DemoRunnable(this));
        //this.postDemoSetup();
    }

    public synchronized void createGame() throws Exception
    {
        final String CREATE_GAME = "createGame";
        LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START, this, CREATE_GAME));

        ////TWB - Loading Feature Change - Can remove remark after testing
        ProgressCanvasFactory.getInstance().start();
        
        Hashtable hashtable = this.getStartStateHashtable();
        this.setStartStateHashtable(null);

        PrimaryThreadPool.getInstance().runTask(new CreateGameRunnable(this, hashtable));

        LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().END, this, CREATE_GAME));
    }

    public boolean isReady()
    {
       return true;
    }
}
