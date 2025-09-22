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
package org.allbinary.image.opengles;

import javax.microedition.khronos.opengles.GL;
import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL11;
import javax.microedition.khronos.opengles.GL11Ext;
import javax.microedition.lcdui.Image;

import org.allbinary.graphics.displayable.DisplayInfoSingleton;
import org.allbinary.graphics.displayable.event.DisplayChangeEvent;
import org.allbinary.graphics.opengles.OpenGLLogUtil;

import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonStrings;
import org.allbinary.platform.graphics.PlatformBitmapBaseFactory;
import org.allbinary.platform.opengles.PlatformTextureBaseFactory;

//Many devices don't support this even though it is supposed to
public class OpenGLESGL11ExtImage extends OpenGLESImage
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    // private IntBuffer rectParams;
    private int a;
    private final int[] rectangle;

    public OpenGLESGL11ExtImage(final Image image, final PlatformBitmapBaseFactory bitmapFactory, 
        final PlatformTextureBaseFactory textureFactory)
    {
        super(image, bitmapFactory, textureFactory);

        this.onDisplayChangeEvent(null);
        rectangle = new int[]
        { 0, this.getHeight(), this.getWidth(), -this.getHeight() };

    }
    
    /*
    public OpenGLESGL11ExtImage(GL10 gl, Image image, boolean matchColor)
    {
        super(gl, image, matchColor);

        this.onDisplayChangeEvent(null);

        rectangle = new int[]
        { 0, this.getHeight(), this.getWidth(), -this.getHeight() };
        
        this.update(gl);
    }
    */

    public void onDisplayChangeEvent(final DisplayChangeEvent displayChangeEvent)
    {
        try
        {
            logUtil.put(commonStrings.START, this, "onResize");
            
            this.a = DisplayInfoSingleton.getInstance().getLastHeight() - this.getHeight();
        }
        catch(Exception e)
        {
            logUtil.put(commonStrings.EXCEPTION, this, "onResize", e);
        }
    }
    
    public void set(final GL gl)
    {
        this.onDisplayChangeEvent(null);
        
        final GL11 gl11 = (GL11) gl;
        
        if (super.initTexture(gl11))
        {
            // IntBuffer rectBB = IntBuffer.allocate(rect.length);
            // rectBB.order();
            // rectParams = rectBB;
            // rectParams.put(rect);

            //if(!this.matchColor)
            //{
                //gl.glTexEnvf(GL10.GL_TEXTURE_ENV, GL10.GL_TEXTURE_ENV_MODE,
                  //      GL10.GL_REPLACE);
                //((GL11) gl).glTexEnvi(GL10.GL_TEXTURE_ENV, GL10.GL_TEXTURE_ENV_MODE,
                  //      GL10.GL_REPLACE);
            //}

            this.textureFactory.load(gl11, GL10.GL_TEXTURE_2D, 0, this, 0, true);
            
            gl11.glTexParameteriv(GL10.GL_TEXTURE_2D, GL11Ext.GL_TEXTURE_CROP_RECT_OES, rectangle, 0);

            gl11.glDisable(GL10.GL_TEXTURE_2D);

            OpenGLLogUtil.getInstance().logError(gl11, this);
        }
    }
    
    public void draw(final GL10 gl, final int x, final int y, final int z)
    {
        gl.glEnable(GL10.GL_TEXTURE_2D);
        
        gl.glBindTexture(GL10.GL_TEXTURE_2D, openGLESImageProperties.textureID);
        
        //glDrawTexiOES
        ((GL11Ext) gl).glDrawTexfOES(x, a - y, z, this.getWidth(), this.getHeight());

        gl.glDisable(GL10.GL_TEXTURE_2D);
    }
}