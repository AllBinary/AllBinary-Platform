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

import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;
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

            gl.glTexEnvf(GL10.GL_TEXTURE_ENV, GL10.GL_TEXTURE_ENV_MODE,
                    GL10.GL_MODULATE);

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

    public void draw(GL10 gl, int x, int y, int z)
    {
    }
    
    protected final float[] regionRectangleFloatArray = 
        //new float[12];
    { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
    protected FloatBuffer regionRectangleVertexFloatBuffer = 
        FloatBuffer.wrap(regionRectangleFloatArray);
        //ByteBuffer.allocateDirect(4 * 4 * 3)
            //.order(ByteOrder.nativeOrder()).asFloatBuffer();

    private final float[] regionTextureRectangleFloatArray = new float[8];
    private FloatBuffer regionTextureVertexFloatBuffer =
        FloatBuffer.wrap(regionTextureRectangleFloatArray);
        //ByteBuffer.allocateDirect(4 * 4 * 2).order(
          //ByteOrder.nativeOrder()).asFloatBuffer();

    public void drawRegion(GL10 gl, int viewHeight, 
            float x_src, float y_src, 
            float width, float height, 
            int x, int y, int z)
    {
        this.regionRectangleFloatArray[0] = x;
        this.regionRectangleFloatArray[7] = viewHeight - y;
        this.regionRectangleFloatArray[1] = this.regionRectangleFloatArray[7] - height;

        this.regionRectangleFloatArray[3] = x + width;
        this.regionRectangleFloatArray[4] = this.regionRectangleFloatArray[1];

        this.regionRectangleFloatArray[6] = x;

        this.regionRectangleFloatArray[9] = this.regionRectangleFloatArray[3];
        this.regionRectangleFloatArray[10] = this.regionRectangleFloatArray[7];

        /*
        regionRectangleVertexFloatBuffer.put(0, x);
        regionRectangleVertexFloatBuffer.put(7, viewHeight - y);
        regionRectangleVertexFloatBuffer.put(1, regionRectangleVertexFloatBuffer.get(7) - height);

        regionRectangleVertexFloatBuffer.put(3, x + width);
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
        vertexArray[7] = viewHeight - y;
        vertexArray[1] = vertexArray[7] - height;

        vertexArray[3] = x + width;
        vertexArray[4] = vertexArray[1];

        vertexArray[6] = x;
        //

        vertexArray[9] = vertexArray[3];
        vertexArray[10] = vertexArray[7];

        regionRectangleVertexFloatBuffer.put(vertexArray);
        */
        
        /*
        int aY = viewHeight - y;
        float y2 = aY - height;
        float x2 = x + width;
        
        regionRectangleVertexFloatBuffer.put(x);
        regionRectangleVertexFloatBuffer.put(y2);
        regionRectangleVertexFloatBuffer.put(0.0f);

        regionRectangleVertexFloatBuffer.put(x2);
        regionRectangleVertexFloatBuffer.put(y2);
        regionRectangleVertexFloatBuffer.put(0.0f);

        regionRectangleVertexFloatBuffer.put(x);
        regionRectangleVertexFloatBuffer.put(aY);
        regionRectangleVertexFloatBuffer.put(0.0f);
        
        regionRectangleVertexFloatBuffer.put(x2);
        regionRectangleVertexFloatBuffer.put(aY);
        regionRectangleVertexFloatBuffer.put(0.0f);
        */

        //regionRectangleVertexFloatBuffer.rewind();

        /*
        regionTextureVertexFloatBuffer.put(0, x_src / this.getWidth());
        regionTextureVertexFloatBuffer.put(1, ((float) (y_src + height)) / this.getHeight());

        regionTextureVertexFloatBuffer.put(2, ((float) (x_src + width)) / this.getWidth());
        regionTextureVertexFloatBuffer.put(3, regionTextureVertexFloatBuffer.get(1));

        regionTextureVertexFloatBuffer.put(4, regionTextureVertexFloatBuffer.get(0));
        regionTextureVertexFloatBuffer.put(5, y_src / this.getHeight());

        regionTextureVertexFloatBuffer.put(6, regionTextureVertexFloatBuffer.get(2));
        regionTextureVertexFloatBuffer.put(7, regionTextureVertexFloatBuffer.get(5));
        */
        
        //regionTextureVertexFloatBuffer.rewind();
        
        regionTextureRectangleFloatArray[0] = x_src / this.getWidth();
        regionTextureRectangleFloatArray[1] = ((y_src + height)) / this.getHeight();

        regionTextureRectangleFloatArray[2] = ((x_src + width)) / this.getWidth();
        regionTextureRectangleFloatArray[3] = regionTextureRectangleFloatArray[1];

        regionTextureRectangleFloatArray[4] = regionTextureRectangleFloatArray[0];
        regionTextureRectangleFloatArray[5] = y_src / this.getHeight();

        regionTextureRectangleFloatArray[6] = regionTextureRectangleFloatArray[2];
        regionTextureRectangleFloatArray[7] = regionTextureRectangleFloatArray[5];

        //textureVertexFloatBuffer.put(textureArray);

        /*
        float textureX1 = x_src / this.getWidth();
        float textureY1 = y_src / this.getHeight();
        float textureY2 = ((float) (y_src + height)) / this.getHeight();
        float textureX2 = ((float) (x_src + width)) / this.getWidth();
        
        textureVertexFloatBuffer.put(textureX1);
        textureVertexFloatBuffer.put(textureY2);

        textureVertexFloatBuffer.put(textureX2);
        textureVertexFloatBuffer.put(textureY2);

        textureVertexFloatBuffer.put(textureX1);
        textureVertexFloatBuffer.put(textureY1);

        textureVertexFloatBuffer.put(textureX2);
        textureVertexFloatBuffer.put(textureY1);
        */

        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, regionRectangleVertexFloatBuffer);
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);

        gl.glEnable(GL10.GL_TEXTURE_2D);        
        
        gl.glBindTexture(GL10.GL_TEXTURE_2D, textureID);

        gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);

        gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, regionTextureVertexFloatBuffer);

        // GLUtils.texSubImage2D(GL10.GL_TEXTURE_2D, 0, x_src, y_src,
        // this.getBitmap());

        // gl.glTexSubImage2D(GL10.GL_TEXTURE_2D, 0,
        // x_src, y_src, width, height,
        // GL10.GL_RGBA, GL10.GL_UNSIGNED_BYTE, pixels);

        // ((GL11Ext) gl).glDrawTexiOES(x, a - y, z, width, height);

        // gl.glPushMatrix();
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);
        // gl.glPopMatrix();

        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);

        gl.glDisable(GL10.GL_TEXTURE_2D);
    }
}
