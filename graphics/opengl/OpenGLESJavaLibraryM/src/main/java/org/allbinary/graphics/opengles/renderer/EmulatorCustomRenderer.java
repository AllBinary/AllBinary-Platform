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
package org.allbinary.graphics.opengles.renderer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import org.allbinary.emulator.InitEmulatorFactory;
import org.allbinary.logic.string.CommonStrings;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.communication.log.PreLogUtil;

/**
 *
 * @author User
 */
public class EmulatorCustomRenderer //extends CustomRenderer
{
    //Wait until emulator is initialized
    public void onSurfaceCreated(final GL10 gl, final EGLConfig eglConfig)
    {        
    	final String METHOD_NAME = "onSurfaceCreated";
    	
    	LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START, this, METHOD_NAME));

        InitEmulatorFactory initEmulatorFactory = InitEmulatorFactory.getInstance();
        
        if(!initEmulatorFactory.isInitEmulator())
        {
            try
            {
                final String WAIT_FOR_EMULATOR = "Waiting on Emulator Initialization";

                while(!initEmulatorFactory.isInitEmulator())
                {
                    //LogUtil.put(LogFactory.getInstance(WAIT_FOR_EMULATOR, this, METHOD_NAME));
                    PreLogUtil.put(WAIT_FOR_EMULATOR, this, METHOD_NAME);
                    Thread.sleep(180);
                }
            } catch (Exception e)
            {
                LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, METHOD_NAME, e));
            }
        }
        
    }
    
}
