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
    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    protected final RendererStrings renderStrings = RendererStrings.getInstance();
    
    //Wait until emulator is initialized
    public void onSurfaceCreated(final GL10 gl, final EGLConfig eglConfig)
    {        
    	LogUtil.put(LogFactory.getInstance(commonStrings.START, this, this.renderStrings.ON_SURFACE_CREATED));

        final InitEmulatorFactory initEmulatorFactory = InitEmulatorFactory.getInstance();
        
        if(!initEmulatorFactory.isInitEmulator())
        {
            try
            {
                final String WAIT_FOR_EMULATOR = "Waiting on Emulator Initialization";

                while(!initEmulatorFactory.isInitEmulator())
                {
                    //LogUtil.put(LogFactory.getInstance(WAIT_FOR_EMULATOR, this, METHOD_NAME));
                    PreLogUtil.put(WAIT_FOR_EMULATOR, this, this.renderStrings.ON_SURFACE_CREATED);
                    Thread.sleep(180);
                }
            } catch (Exception e)
            {
                LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, this.renderStrings.ON_SURFACE_CREATED, e));
            }
        }
        
    }
    
}
