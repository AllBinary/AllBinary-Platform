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
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.OpenGLESPostLoadPlatformImage;

import org.allbinary.graphics.OpenGLBitmap;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonStrings;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.platform.graphics.PlatformBitmapBaseFactory;
import org.allbinary.platform.opengles.PlatformTextureBaseFactory;
import org.allbinary.util.BasicArrayList;

public class OpenGLESImage extends Image
implements OpenGLSurfaceChangedInterface
{
    public static final BasicArrayList texture2dList = new BasicArrayList();
    
    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    
    protected final PlatformTextureBaseFactory textureFactory;
    
    public final OpenGLBitmap openGLBitmap;
    
    protected int textureID = -1;
    //protected boolean matchColor;
    
    public OpenGLESImageProcessor imageProcessor = OpenGLESImageProcessor.getInstance();
    public OpenGLESImageTranslate openGLESImageTranslate = OpenGLESImageTranslate.getInstance();
    public float scaleX = 1;
    public float scaleY = 1;
    public float scaleX2 = 1;
    public float scaleY2 = 1;
    public int angle;
    public float redf = 1.0f;
    public float greenf = 1.0f;
    public float bluef = 1.0f;
    public float alphaf;
    
    //JOGL
    public OpenGLESImage(final Image image, final PlatformBitmapBaseFactory bitmapFactory, 
        final PlatformTextureBaseFactory textureFactory)
    {
        super(image.getName(), null);
        //super(image);

        //LogUtil.put(LogFactory.getInstance(new StringMaker().append("opengl: create ").append(this).append(this.getName()).append(" from " ).append(image).toString(), this, commonStrings.INIT));
        this.openGLBitmap = (OpenGLBitmap) bitmapFactory.createBitmap(image);
        this.textureFactory = textureFactory;
        OpenGLImageCacheFactory.getInstance().init(this);
        this.platformImage = OpenGLESPostLoadPlatformImage.getInstance();
    }

    /*
    public OpenGLESImage(GL10 gl, Image image, boolean matchColor)
    {
        super(image);
        
        //this.matchColor = matchColor;
    }
    */

    public int getHeight() {
        return (int) (this.openGLBitmap.getHeight() * scaleY2);
    }

    public int getWidth() {
        return (int) (this.openGLBitmap.getWidth() * scaleX2);
    }

    /*
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
    */

    public void set(GL gl) throws Exception
    {
        throw new Exception(CommonStrings.getInstance().NOT_IMPLEMENTED);
    }
 
    protected boolean initTexture(GL10 gl)
    {   
        if (!texture2dList.contains(this))
        {
            texture2dList.add(this);

            //PreLogUtil.put(this.commonStrings.INIT, this, "OpenGLESImage->initTexture");
            
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
            if(this.textureID != -1)
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
            gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S, GL10.GL_CLAMP_TO_EDGE);
            gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T, GL10.GL_CLAMP_TO_EDGE);
            
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
        this.glUtil.position(pixelbuf, 0);
        this.glUtil.position(bytebuf, 0);
         
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
    
    public static final int TYPE = 4;

    public int getType() {
        return TYPE;
    }

    public String toString() {
        return this.getName() + super.toString();
    }    

}
