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

import javax.microedition.khronos.opengles.GL;
import javax.microedition.khronos.opengles.GL10;
import javax.microedition.lcdui.Image;

import org.allbinary.graphics.opengles.OpenGLLogUtil;
import org.allbinary.graphics.displayable.DisplayInfoSingleton;
import org.allbinary.opengles.GLUtil;
import org.allbinary.platform.graphics.PlatformBitmapBaseFactory;
import org.allbinary.platform.opengles.PlatformTextureBaseFactory;

//This is not actually VBO impl
public class OpenGLESGL11VBOImage extends OpenGLESImage
{
    private final GLUtil glUtil = GLUtil.getInstance();
    
    private FloatBuffer textureVertexFloatBuffer = 
        ByteBuffer.allocateDirect(4 * 4 * 2).order(
            ByteOrder.nativeOrder()).asFloatBuffer();
    
    public OpenGLESGL11VBOImage(final Image image, final PlatformBitmapBaseFactory bitmapFactory, 
        final PlatformTextureBaseFactory textureFactory)
    {
        super(image, bitmapFactory, textureFactory);
        
        this.initVertices();
        
        this.regionRectangleVertexFloatBuffer.put(FloatBuffer.wrap(regionRectangleFloatArray));
        this.regionTextureVertexFloatBuffer.put(FloatBuffer.wrap(regionTextureRectangleFloatArray));        
    }

    /*
    public OpenGLESGL10Image(GL10 gl, Image image, boolean matchColor)
    {
        super(gl, image, matchColor);

        this.init();
        
        this.update(gl);
    }
    */
    
    protected void initVertices()
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
    
    public void set(final GL gl)
    {
        final GL10 gl10 = (GL10) gl;
        
        if (super.initTexture(gl10))
        {
            //if(!this.matchColor)
            //{
                //gl.glTexEnvf(GL10.GL_TEXTURE_ENV, GL10.GL_TEXTURE_ENV_MODE,
                  //      GL10.GL_REPLACE);
            //}
            
            this.textureFactory.load(gl10, GL10.GL_TEXTURE_2D, 0, this, 0, true);
            
            gl10.glDisable(GL10.GL_TEXTURE_2D);

            OpenGLLogUtil.getInstance().logError(gl10, this);            
        }

        /*
        ByteBuffer bytebuf = ByteBuffer.allocateDirect(bmp.getHeight() * bmp.getWidth() * 4);
        bytebuf.order(ByteOrder.nativeOrder());
        IntBuffer pixelbuf = bytebuf.asIntBuffer();
         
        for(int y = 0; y < bmp.getHeight(); y++)
          for(int x = 0; x < bmp.getWidth(); x++) {
            pixelbuf.put(bmp.getPixel(x, y));
          }
        this.glUtil.position(pixelbuf, 0);
        this.glUtil.position(bytebuf, 0);
        
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
    
    protected final float[] regionRectangleFloatArray = 
            //new float[12];
        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
    protected FloatBuffer regionRectangleVertexFloatBuffer = 
        //FloatBuffer.wrap(regionRectangleFloatArray);
        ByteBuffer.allocateDirect(4 * 4 * 3)
                .order(ByteOrder.nativeOrder()).asFloatBuffer();

    private final float[] regionTextureRectangleFloatArray = new float[8];
    private FloatBuffer regionTextureVertexFloatBuffer =
        //FloatBuffer.wrap(regionTextureRectangleFloatArray);
        ByteBuffer.allocateDirect(4 * 4 * 2)
                .order(ByteOrder.nativeOrder()).asFloatBuffer();

    public void drawRegion(final GL10 gl, final int viewHeight, 
            final float x_src, final float y_src, 
            final float width, final float height, 
            final int x, final int y, final int z)
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
        //this.glUtil.position(regionRectangleVertexFloatBuffer, 7);
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

        this.glUtil.position(regionRectangleVertexFloatBuffer, 0);
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, regionRectangleVertexFloatBuffer);
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);

        gl.glEnable(GL10.GL_TEXTURE_2D);        
        
        gl.glBindTexture(GL10.GL_TEXTURE_2D, textureID);

        gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
        
        this.glUtil.position(regionTextureVertexFloatBuffer, 0);
        gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, regionTextureVertexFloatBuffer);

        // GLUtils.texSubImage2D(GL10.GL_TEXTURE_2D, 0, x_src, y_src,
        // this.getBitmap());

        // gl.glTexSubImage2D(GL10.GL_TEXTURE_2D, 0,
        // x_src, y_src, width, height,
        // GL10.GL_RGBA, GL10.GL_UNSIGNED_BYTE, pixels);

        // ((GL11Ext) gl).glDrawTexiOES(x, a - y, z, width, height);

        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);

        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);

        gl.glDisable(GL10.GL_TEXTURE_2D);
    }
    
    public void draw(final GL10 gl, final int x, final int y, final int z)
    {
        this.regionRectangleFloatArray[0] = x;
        this.regionRectangleFloatArray[7] = DisplayInfoSingleton.getInstance().getLastHeight() - y;
        this.regionRectangleFloatArray[1] = this.regionRectangleFloatArray[7] - this.getHeight();

        this.regionRectangleFloatArray[3] = x + this.getWidth();
        this.regionRectangleFloatArray[4] = this.regionRectangleFloatArray[1];

        this.regionRectangleFloatArray[6] = x;
        //this.glUtil.position(regionRectangleVertexFloatBuffer, 7);
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
        //this.glUtil.position(regionRectangleVertexFloatBuffer, 7);
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

        this.glUtil.position(regionRectangleVertexFloatBuffer, 0);
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, regionRectangleVertexFloatBuffer);
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);

        gl.glEnable(GL10.GL_TEXTURE_2D);
        
        gl.glBindTexture(GL10.GL_TEXTURE_2D, textureID);

        gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
        
        this.glUtil.position(textureVertexFloatBuffer, 0);
        gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, textureVertexFloatBuffer);

        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);

        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);

        gl.glDisable(GL10.GL_TEXTURE_2D);        
    }
}
