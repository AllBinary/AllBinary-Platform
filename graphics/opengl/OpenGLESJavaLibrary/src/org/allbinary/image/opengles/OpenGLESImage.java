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

import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL11;
import javax.microedition.lcdui.Image;

import abcs.logic.basic.NotImplemented;
import abcs.logic.basic.string.CommonStrings;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;

public class OpenGLESImage extends Image
{
    protected int textureID;
    //protected boolean matchColor;
    
    public OpenGLESImage(Image image)
    {
        super(image);
    }

    public OpenGLESImage(GL10 gl, Image image, boolean matchColor)
    {
        super(image);
        
        //this.matchColor = matchColor;
    }
    
    protected void update(GL10 gl)
    {
        try
        {
            this.set(gl);
        } catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, CommonStrings.getInstance().UPDATE, e));
        }
    }

    public void set(GL10 gl) throws Exception
    {
        throw new Exception(NotImplemented.NAME);
    }
 
    private GL10 gl;
    
    protected boolean initTexture(GL10 gl)
    {
        if (this.gl != gl)
        {
            final int[] textures = new int[1];

            /*
            if(this.matchColor)
            {
                gl.glActiveTexture(GL10.GL_TEXTURE0);
                gl.glClientActiveTexture(GL10.GL_TEXTURE0); 
            }
            else
            {
                gl.glActiveTexture(GL10.GL_TEXTURE1);
                gl.glClientActiveTexture(GL10.GL_TEXTURE1);
            }
            */
            
            gl.glEnable(GL10.GL_TEXTURE_2D);

            //Delete old texture
            if(this.gl != null)
            {
                textures[0] = textureID;
                gl.glDeleteTextures(1, textures, 0);
            }

            gl.glGenTextures(1, textures, 0);

            textureID = textures[0];
            
            gl.glBindTexture(GL10.GL_TEXTURE_2D, textureID);

            if(false) 
            {
                gl.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_GENERATE_MIPMAP, GL11.GL_TRUE);
            } else
            {
                gl.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_GENERATE_MIPMAP, GL11.GL_FALSE);
            }

            gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);
            gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);

            //gl.glTexEnvx(GL10.GL_TEXTURE_ENV, GL10.GL_TEXTURE_ENV_MODE, GL10.GL_REPLACE);
            //TWB - working to fix gl images on a555 so remarked below
                  //GL10.GL_MODULATE);

            // texture wrapping settings
            //gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S, GL10.GL_CLAMP_TO_EDGE);
            //gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T, GL10.GL_CLAMP_TO_EDGE);
            
            // gl.glTexParameterf(GL10.GL_TEXTURE_2D,
            // GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);
            // gl.glTexParameterf(GL10.GL_TEXTURE_2D,
            // GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);

            // gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S,
            // GL10.GL_CLAMP_TO_EDGE);
            // gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T,
            // GL10.GL_CLAMP_TO_EDGE);

        /*
        ByteBuffer bytebuf = ByteBuffer.allocateDirect(bmp.getHeight() * bmp.getWidth() * 4);
        bytebuf.order(ByteOrder.nativeOrder());
        IntBuffer pixelbuf = bytebuf.asIntBuffer();
         
        for(int y = 0; y &lt; bmp.getHeight(); y++)
          for(int x = 0; x &lt; bmp.getWidth(); x++) {
            pixelbuf.put(bmp.getPixel(x, y));
          }
        pixelbuf.position(0);
        bytebuf.position(0);
         
        gl.glTexImage2D(GL10.GL_TEXTURE_2D, level, GL10.GL_RGBA, bmp.getWidth(), bmp.getHeight(), 0, GL10.GL_RGBA, GL10.GL_UNSIGNED_BYTE, pixelbuf);
        */        

            return true;
        }
        return false;
    }
    
    public void drawRegion(GL10 gl, int viewHeight, 
            float x_src, float y_src, 
            float width, float height, 
            int x, int y, int z)
    {
    }    

    public void draw(GL10 gl, int x, int y, int z)
    {
    }
}
