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

import org.allbinary.graphics.displayable.DisplayInfoSingleton;
import org.allbinary.graphics.opengles.OpenGLLogUtil;
import org.allbinary.opengles.GLUtil;
import org.allbinary.platform.graphics.PlatformBitmapBaseFactory;
import org.allbinary.platform.opengles.PlatformTextureBaseFactory;

public class OpenGLESGL10Image extends OpenGLESImage
{
    //protected final LogUtil logUtil = LogUtil.getInstance();
    
    private final FloatBuffer textureVertexFloatBuffer = 
        ByteBuffer.allocateDirect(4 * 4 * 2).order(
            ByteOrder.nativeOrder()).asFloatBuffer();
    
    private final OpenGLESImageDraw realOpenGLESImageDraw = new OpenGLESImageDraw() {

        private final GLUtil glUtil = GLUtil.getInstance();
        
        private final DisplayInfoSingleton displayInfoSingleton = DisplayInfoSingleton.getInstance();
        
        private final FloatBuffer textureVertexFloatBuffer = OpenGLESGL10Image.this.textureVertexFloatBuffer;
            
        private final float[] regionTextureRectangleFloatArray = new float[8];

        private final FloatBuffer regionTextureVertexFloatBuffer =
            //FloatBuffer.wrap(regionTextureRectangleFloatArray);
            ByteBuffer.allocateDirect(4 * 4 * 2).order(ByteOrder.nativeOrder()).asFloatBuffer();
        
        private final float[] regionRectangleFloatArray = 
            //new float[12];
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
//        { 
//            0.0f, -0.5f, 0.0f, 
//            0.5f, -0.5f, 0.0f, 
//            0.0f, 0.5f, 0.0f, 
//            0.5f, 0.5f, 0.0f 
//        };    
//    {
//        -0.5f, -0.5f, 0.0f,  // Bottom-left
//         0.5f, -0.5f, 0.0f,  // Bottom-right
//        -0.5f,  0.5f, 0.0f,  // Top-left
//
//         0.5f, -0.5f, 0.0f,  // Bottom-right
//         0.5f,  0.5f, 0.0f,  // Top-right
//        -0.5f,  0.5f, 0.0f   // Top-left
//    };    
        
        protected final FloatBuffer regionRectangleVertexFloatBuffer = 
            //FloatBuffer.wrap(regionRectangleFloatArray);
            ByteBuffer.allocateDirect(4 * 4 * 3)
            //ByteBuffer.allocateDirect(6 * 4 * 3)
                .order(ByteOrder.nativeOrder()).asFloatBuffer();
    
        final OpenGLESImageProperties openGLESImageProperties = OpenGLESGL10Image.this.openGLESImageProperties;
            
        @Override
        public void drawRegion(final GL10 gl, final int viewHeight,
            final float x_src, final float y_src,
            final float width, final float height,
            final int x, final int y, final int z) {
            //PreLogUtil.put(this.commonStrings.START + "Texture", this, "drawRegion");

            final int imageWidth = OpenGLESGL10Image.this.getWidth();
            final int imageHeight = OpenGLESGL10Image.this.getHeight();
            
//            regionRectangleFloatArray[0] = x;
//            regionRectangleFloatArray[7] = viewHeight - y;
//            regionRectangleFloatArray[1] = regionRectangleFloatArray[7] - height;
//
//            regionRectangleFloatArray[3] = x + width;
//            regionRectangleFloatArray[4] = regionRectangleFloatArray[1];
//
//            regionRectangleFloatArray[6] = x;
//
//            regionRectangleFloatArray[9] = regionRectangleFloatArray[3];
//            regionRectangleFloatArray[10] = regionRectangleFloatArray[7];

            this.regionRectangleFloatArray[7] = viewHeight;
            this.regionRectangleFloatArray[1] = this.regionRectangleFloatArray[7] - height;

            this.regionRectangleFloatArray[3] = width;
            this.regionRectangleFloatArray[4] = this.regionRectangleFloatArray[1];

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
        //glUtil.position(regionRectangleVertexFloatBuffer, 7);
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
        regionTextureVertexFloatBuffer.put(0, x_src / imageWidth);
        regionTextureVertexFloatBuffer.put(1, ((float) (y_src + height)) / imageHeight);

        regionTextureVertexFloatBuffer.put(2, ((float) (x_src + width)) / imageWidth);
        regionTextureVertexFloatBuffer.put(3, regionTextureVertexFloatBuffer.get(1));

        regionTextureVertexFloatBuffer.put(4, regionTextureVertexFloatBuffer.get(0));
        regionTextureVertexFloatBuffer.put(5, y_src / imageHeight);

        regionTextureVertexFloatBuffer.put(6, regionTextureVertexFloatBuffer.get(2));
        regionTextureVertexFloatBuffer.put(7, regionTextureVertexFloatBuffer.get(5));
             */
            //regionTextureVertexFloatBuffer.rewind();
            this.regionTextureRectangleFloatArray[0] = x_src / imageWidth;
            this.regionTextureRectangleFloatArray[1] = ((y_src + height)) / imageHeight;

            this.regionTextureRectangleFloatArray[2] = ((x_src + width)) / imageWidth;
            this.regionTextureRectangleFloatArray[3] = this.regionTextureRectangleFloatArray[1];

            this.regionTextureRectangleFloatArray[4] = this.regionTextureRectangleFloatArray[0];
            this.regionTextureRectangleFloatArray[5] = y_src / imageHeight;

            this.regionTextureRectangleFloatArray[6] = this.regionTextureRectangleFloatArray[2];
            this.regionTextureRectangleFloatArray[7] = this.regionTextureRectangleFloatArray[5];
            
            final float u_center = (this.regionTextureRectangleFloatArray[0] + this.regionTextureRectangleFloatArray[2]) / 2.0f;
            final float v_center = (this.regionTextureRectangleFloatArray[5] + this.regionTextureRectangleFloatArray[1]) / 2.0f;
            this.glUtil.rotateUVs(this.regionTextureRectangleFloatArray, -this.openGLESImageProperties.angle, u_center, v_center);

            //textureVertexFloatBuffer.put(textureArray);

            /*
        float textureX1 = x_src / imageWidth;
        float textureY1 = y_src / imageHeight;
        float textureY2 = ((float) (y_src + height)) / imageHeight;
        float textureX2 = ((float) (x_src + width)) / imageWidth;
        
        textureVertexFloatBuffer.put(textureX1);
        textureVertexFloatBuffer.put(textureY2);

        textureVertexFloatBuffer.put(textureX2);
        textureVertexFloatBuffer.put(textureY2);

        textureVertexFloatBuffer.put(textureX1);
        textureVertexFloatBuffer.put(textureY1);

        textureVertexFloatBuffer.put(textureX2);
        textureVertexFloatBuffer.put(textureY1);
             */

            //Matrix operations in modelview
            gl.glPushMatrix();

//            imageProcessor.translate(gl, -x + (width / 2), -(viewHeight - y + (height / 2)));
//            imageProcessor.rotate(gl, angle);
//            imageProcessor.translate(gl, x - (width / 2), (viewHeight - y - (height / 2)));
            
            final OpenGLESImageProcessor imageProcessor = OpenGLESGL10Image.this.imageProcessor;
            
            OpenGLESGL10Image.this.openGLESImageTranslate.translate(gl, OpenGLESGL10Image.this, x, -y);

            imageProcessor.scale(gl, this.openGLESImageProperties.scaleX, this.openGLESImageProperties.scaleY);
            //imageProcessor.scale(gl, scaleX * width, scaleY * height * 2.0f);
            
            imageProcessor.colorMask(gl, this.openGLESImageProperties.redf, this.openGLESImageProperties.greenf, this.openGLESImageProperties.bluef, this.openGLESImageProperties.alphaf);
            
            OpenGLESGL10Image.this.openGLESImageTranslate.translate2(gl, OpenGLESGL10Image.this);
            
            this.regionRectangleVertexFloatBuffer.put(this.regionRectangleFloatArray);
            this.glUtil.position(this.regionRectangleVertexFloatBuffer, 0);
            gl.glVertexPointer(3, GL10.GL_FLOAT, 0, this.regionRectangleVertexFloatBuffer);
            gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);

            gl.glEnable(GL10.GL_TEXTURE_2D);

            gl.glBindTexture(GL10.GL_TEXTURE_2D, this.openGLESImageProperties.textureID);

            gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
            
            this.regionTextureVertexFloatBuffer.put(this.regionTextureRectangleFloatArray);
            this.glUtil.position(this.regionTextureVertexFloatBuffer, 0);
            gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, this.regionTextureVertexFloatBuffer);

            // GLUtils.texSubImage2D(GL10.GL_TEXTURE_2D, 0, x_src, y_src,
            // getBitmap());
            // gl.glTexSubImage2D(GL10.GL_TEXTURE_2D, 0,
            // x_src, y_src, width, height,
            // GL10.GL_RGBA, GL10.GL_UNSIGNED_BYTE, pixels);
            // ((GL11Ext) gl).glDrawTexiOES(x, a - y, z, width, height);
            //gl.glDrawArrays(GL10.GL_TRIANGLES, 0, 6);
            //gl.glDrawArrays(GL10.GL_TRIANGLES, 0, 3);
            //gl.glDrawArrays(GL10.GL_TRIANGLES, 1, 3);
            gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);

            gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
            gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);

            gl.glDisable(GL10.GL_TEXTURE_2D);
            
            //Restore matrix
            gl.glPopMatrix();
            
        }

        public void draw(final GL10 gl, final int x, final int y, final int z) {
            //PreLogUtil.put(commonStrings.START + "Texture", this, "draw");

            final int width = OpenGLESGL10Image.this.getWidth();
            final int height = OpenGLESGL10Image.this.getHeight();
            
//            regionRectangleFloatArray[0] = x;
//            regionRectangleFloatArray[7] = displayInfoSingleton.getLastHeight() - y;
//            regionRectangleFloatArray[1] = regionRectangleFloatArray[7] - getHeight();
//
//            regionRectangleFloatArray[3] = x + getWidth();
//            regionRectangleFloatArray[4] = regionRectangleFloatArray[1];
//
//            regionRectangleFloatArray[6] = x;
//            //glUtil.position(regionRectangleVertexFloatBuffer, 7);
//            //regionRectangleVertexFloatBuffer.put(regionRectangleVertexFloatBuffer.get(7));
//
//            regionRectangleFloatArray[9] = regionRectangleFloatArray[3];
//            regionRectangleFloatArray[10] = regionRectangleFloatArray[7];

            this.regionRectangleFloatArray[7] = this.displayInfoSingleton.getLastHeight();
            this.regionRectangleFloatArray[1] = this.regionRectangleFloatArray[7] - height;

            this.regionRectangleFloatArray[3] = width;
            this.regionRectangleFloatArray[4] = this.regionRectangleFloatArray[1];

            this.regionRectangleFloatArray[9] = this.regionRectangleFloatArray[3];
            this.regionRectangleFloatArray[10] = this.regionRectangleFloatArray[7];
            
            /*
        regionRectangleVertexFloatBuffer.put(0, x);
        regionRectangleVertexFloatBuffer.put(7, DisplayInfoSingleton.getInstance().getLastHeight() - y);
        regionRectangleVertexFloatBuffer.put(1, regionRectangleVertexFloatBuffer.get(7) - getHeight());

        regionRectangleVertexFloatBuffer.put(3, x + getWidth());
        regionRectangleVertexFloatBuffer.put(4, regionRectangleVertexFloatBuffer.get(1));

        regionRectangleVertexFloatBuffer.put(6, x);
        //
        //glUtil.position(regionRectangleVertexFloatBuffer, 7);
        //regionRectangleVertexFloatBuffer.put(regionRectangleVertexFloatBuffer.get(7));

        regionRectangleVertexFloatBuffer.put(9, regionRectangleVertexFloatBuffer.get(3));
        regionRectangleVertexFloatBuffer.put(10, regionRectangleVertexFloatBuffer.get(7));
             */

        this.regionTextureRectangleFloatArray[0] = 0;
        this.regionTextureRectangleFloatArray[1] = 1;
        this.regionTextureRectangleFloatArray[2] = 1;
        this.regionTextureRectangleFloatArray[3] = 1;
        this.regionTextureRectangleFloatArray[4] = 0;
        this.regionTextureRectangleFloatArray[5] = 0;
        this.regionTextureRectangleFloatArray[6] = 1;
        this.regionTextureRectangleFloatArray[7] = 0;
            
        /*
        vertexArray[0] = x;
        vertexArray[7] = DisplayInfoSingleton.getInstance().getLastHeight() - y;
        vertexArray[1] = vertexArray[7] - getHeight();

        vertexArray[3] = x + getWidth();
        vertexArray[4] = vertexArray[1];

        vertexArray[6] = x;
        //

        vertexArray[9] = vertexArray[3];
        vertexArray[10] = vertexArray[7];

        regionRectangleVertexFloatBuffer.put(vertexArray);
             */
            //regionRectangleVertexFloatBuffer.rewind();
            //textureVertexFloatBuffer.rewind();

            //Matrix operations in modelview
            gl.glPushMatrix();
            
//            imageProcessor.translate(gl, -x + (width / 2), -(viewHeight - y + (height / 2)));
//            imageProcessor.rotate(gl, angle);
//            imageProcessor.translate(gl, x - (width / 2), (viewHeight - y - (height / 2)));

            OpenGLESGL10Image.this.openGLESImageTranslate.translate(gl, OpenGLESGL10Image.this, x, -y);

            final OpenGLESImageProcessor imageProcessor = OpenGLESGL10Image.this.imageProcessor;
            imageProcessor.scale(gl, this.openGLESImageProperties.scaleX, this.openGLESImageProperties.scaleY);
            //imageProcessor.scale(gl, scaleX * width, scaleY * height * 2.0f);
            imageProcessor.colorMask(gl, this.openGLESImageProperties.redf, this.openGLESImageProperties.greenf, this.openGLESImageProperties.bluef, this.openGLESImageProperties.alphaf);
            
            OpenGLESGL10Image.this.openGLESImageTranslate.translate2(gl, OpenGLESGL10Image.this);            
            
            this.regionRectangleVertexFloatBuffer.put(this.regionRectangleFloatArray);
            this.glUtil.position(this.regionRectangleVertexFloatBuffer, 0);
            gl.glVertexPointer(3, GL10.GL_FLOAT, 0, this.regionRectangleVertexFloatBuffer);
            gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);

            gl.glEnable(GL10.GL_TEXTURE_2D);

            gl.glBindTexture(GL10.GL_TEXTURE_2D, this.openGLESImageProperties.textureID);

            gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);

            this.glUtil.rotateUVs(this.regionTextureRectangleFloatArray, -this.openGLESImageProperties.angle, 0.5f, 0.5f);

            this.glUtil.position(this.textureVertexFloatBuffer, 0);
            this.textureVertexFloatBuffer.put(this.regionTextureRectangleFloatArray);
            this.glUtil.position(this.textureVertexFloatBuffer, 0);
            
            gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, this.textureVertexFloatBuffer);

            //gl.glDrawArrays(GL10.GL_TRIANGLES, 0, 6);
            //gl.glDrawArrays(GL10.GL_TRIANGLES, 0, 3);
            //gl.glDrawArrays(GL10.GL_TRIANGLES, 1, 3);            
            gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);

            gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
            gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);

            gl.glDisable(GL10.GL_TEXTURE_2D);
            
            //Restore matrix
            gl.glPopMatrix();
            
        }
                
    };

    private OpenGLESImageDraw openGLESImageDraw = OpenGLESImageDraw.getInstance();

    public OpenGLESGL10Image(final Image image, final PlatformBitmapBaseFactory bitmapFactory, 
        final PlatformTextureBaseFactory textureFactory)
    {
        super(image, bitmapFactory, textureFactory);
        
        this.initVertices();
    }

    /*
    public OpenGLESGL10Image(GL10 gl, Image image, boolean matchColor)
    {
        super(gl, image, matchColor);

        this.initVertices();
        
        this.update(gl);
    }
    */
    
    protected void initVertices()
    {
        this.textureVertexFloatBuffer.put(0);
        this.textureVertexFloatBuffer.put(1);

        this.textureVertexFloatBuffer.put(1);
        this.textureVertexFloatBuffer.put(1);

        this.textureVertexFloatBuffer.put(0);
        this.textureVertexFloatBuffer.put(0);

        this.textureVertexFloatBuffer.put(1);
        this.textureVertexFloatBuffer.put(0);

        this.textureVertexFloatBuffer.rewind();
    }
    
    public void set(final GL gl)
    {
        final GL10 gl10 = (GL10) gl;
        
        if (super.initTexture(gl10))
        {
//            final SpacialStrings spacialStrings = SpacialStrings.getInstance();
//            PreLogUtil.put(new StringMaker().append(this.commonStrings.START).append(this.toString()).append(spacialStrings.WIDTH_LABEL).append(this.getWidth()).append(spacialStrings.HEIGHT_LABEL).append(this.getHeight()).toString(), this, "initTexture");

            //if(!this.matchColor)
            //{
                //gl.glTexEnvf(GL10.GL_TEXTURE_ENV, GL10.GL_TEXTURE_ENV_MODE,
                  //      GL10.GL_REPLACE);
            //}
            
            this.textureFactory.load(gl10, GL10.GL_TEXTURE_2D, 0, this, 0, true);
            
            gl10.glDisable(GL10.GL_TEXTURE_2D);
            
            this.openGLESImageDraw = this.realOpenGLESImageDraw;

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
    
    public void drawRegion(final GL10 gl, final int viewHeight, 
            final float x_src, final float y_src, 
            final float width, final float height, 
            final int x, final int y, final int z)
    {
        this.openGLESImageDraw.drawRegion(gl, viewHeight, x_src, y_src, width, height, x, y, z);
    }
    
    public void draw(final GL10 gl, final int x, final int y, final int z)
    {
        this.openGLESImageDraw.draw(gl, x, y, z);
    }
}
