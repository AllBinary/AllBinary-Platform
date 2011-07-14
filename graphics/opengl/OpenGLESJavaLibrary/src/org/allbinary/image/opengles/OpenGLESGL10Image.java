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

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;
import javax.microedition.lcdui.Image;

import org.allbinary.graphics.opengles.TextureFactory;

import abcs.logic.basic.string.CommonStrings;
import abcs.logic.communication.log.PreLogUtil;
import allbinary.graphics.displayable.DisplayInfoSingleton;

public class OpenGLESGL10Image extends OpenGLESImage
{
    private FloatBuffer textureVertexFloatBuffer = 
        ByteBuffer.allocateDirect(4 * 4 * 2).order(
            ByteOrder.nativeOrder()).asFloatBuffer();
    
    public OpenGLESGL10Image(Image image)
    {
        super(image);
        
        this.init();
    }

    public OpenGLESGL10Image(GL10 gl, Image image, boolean matchColor)
    {
        super(gl, image, matchColor);

        this.init();
        
        this.update(gl);
    }
    
    private void init()
    {
        textureVertexFloatBuffer.put(0);
        textureVertexFloatBuffer.put(1);

        textureVertexFloatBuffer.put(1);
        textureVertexFloatBuffer.put(1);

        textureVertexFloatBuffer.put(0);
        textureVertexFloatBuffer.put(0);

        textureVertexFloatBuffer.put(1);
        textureVertexFloatBuffer.put(0);

        textureVertexFloatBuffer.rewind();
    }

    public void set(GL10 gl)
    {
        if (super.initTexture(gl))
        {
            //if(!this.matchColor)
            //{
                //gl.glTexEnvf(GL10.GL_TEXTURE_ENV, GL10.GL_TEXTURE_ENV_MODE,
                  //      GL10.GL_REPLACE);
            //}
            
            TextureFactory.getInstance().load(GL10.GL_TEXTURE_2D, 0, this, 0);
            
            gl.glDisable(GL10.GL_TEXTURE_2D);

            int error = gl.glGetError();
            if (error != GL10.GL_NO_ERROR)
            {
                PreLogUtil.put("GLError: " + error, this, CommonStrings.getInstance().CONSTRUCTOR);
            }
        }

        /*
        ByteBuffer bytebuf = ByteBuffer.allocateDirect(bmp.getHeight() * bmp.getWidth() * 4);
        bytebuf.order(ByteOrder.nativeOrder());
        IntBuffer pixelbuf = bytebuf.asIntBuffer();
         
        for(int y = 0; y < bmp.getHeight(); y++)
          for(int x = 0; x < bmp.getWidth(); x++) {
            pixelbuf.put(bmp.getPixel(x, y));
          }
        pixelbuf.position(0);
        bytebuf.position(0);
        
        gl10.glTexImage2D(
                GL10.GL_TEXTURE_2D, 0, GL10.GL_RGBA, 
                image.getWidth(), image.getHeight(), 
                0, GL10.GL_RGBA, GL10.GL_UNSIGNED_BYTE, pixels);
        */
        
        /*
        textureArray[0] = 0;
        textureArray[1] = 1;
        
        textureArray[2] = 1;
        textureArray[3] = textureArray[1];

        textureArray[4] = textureArray[0];
        textureArray[5] = 0;

        textureArray[6] = textureArray[2];
        textureArray[7] = textureArray[5];

        textureVertexFloatBuffer.put(textureArray);
        */        
    }
    
    public void draw(GL10 gl, int x, int y, int z)
    {
        
        this.regionRectangleFloatArray[0] = x;
        this.regionRectangleFloatArray[7] = DisplayInfoSingleton.getInstance().getLastHeight() - y;
        this.regionRectangleFloatArray[1] = this.regionRectangleFloatArray[7] - this.getHeight();

        this.regionRectangleFloatArray[3] = x + this.getWidth();
        this.regionRectangleFloatArray[4] = this.regionRectangleFloatArray[1];

        this.regionRectangleFloatArray[6] = x;
        //regionRectangleVertexFloatBuffer.position(7);
        //regionRectangleVertexFloatBuffer.put(regionRectangleVertexFloatBuffer.get(7));

        this.regionRectangleFloatArray[9] = this.regionRectangleFloatArray[3];
        this.regionRectangleFloatArray[10] = this.regionRectangleFloatArray[7];

        /*
        regionRectangleVertexFloatBuffer.put(0, x);
        regionRectangleVertexFloatBuffer.put(7, DisplayInfoSingleton.getInstance().getLastHeight() - y);
        regionRectangleVertexFloatBuffer.put(1, regionRectangleVertexFloatBuffer.get(7) - this.getHeight());

        regionRectangleVertexFloatBuffer.put(3, x + this.getWidth());
        regionRectangleVertexFloatBuffer.put(4, regionRectangleVertexFloatBuffer.get(1));

        regionRectangleVertexFloatBuffer.put(6, x);
        //
        //regionRectangleVertexFloatBuffer.position(7);
        //regionRectangleVertexFloatBuffer.put(regionRectangleVertexFloatBuffer.get(7));

        regionRectangleVertexFloatBuffer.put(9, regionRectangleVertexFloatBuffer.get(3));
        regionRectangleVertexFloatBuffer.put(10, regionRectangleVertexFloatBuffer.get(7));
        */

        /*
        vertexArray[0] = x;
        vertexArray[7] = DisplayInfoSingleton.getInstance().getLastHeight() - y;
        vertexArray[1] = vertexArray[7] - this.getHeight();

        vertexArray[3] = x + this.getWidth();
        vertexArray[4] = vertexArray[1];

        vertexArray[6] = x;
        //

        vertexArray[9] = vertexArray[3];
        vertexArray[10] = vertexArray[7];

        regionRectangleVertexFloatBuffer.put(vertexArray);
        */
        //regionRectangleVertexFloatBuffer.rewind();
        
        //textureVertexFloatBuffer.rewind();
                
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, regionRectangleVertexFloatBuffer);
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);

        gl.glEnable(GL10.GL_TEXTURE_2D);
        
        gl.glBindTexture(GL10.GL_TEXTURE_2D, textureID);

        gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);

        gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, textureVertexFloatBuffer);

        // gl.glPushMatrix();
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);
        // gl.glPopMatrix();

        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);

        gl.glDisable(GL10.GL_TEXTURE_2D);        
    }
}
