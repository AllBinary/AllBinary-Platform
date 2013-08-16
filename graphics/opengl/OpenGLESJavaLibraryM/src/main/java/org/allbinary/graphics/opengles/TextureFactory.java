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
package org.allbinary.graphics.opengles;

import javax.microedition.lcdui.Image;

import android.opengl.GLUtils;

public class TextureFactory extends BaseTextureFactory
{
    private static final TextureFactory instance = new TextureFactory();

    public static TextureFactory getInstance()
    {
        return instance;
    }

    private TextureFactory()
    {

    }

    /*
    gl.glTexImage2D(
            GL10.GL_TEXTURE_2D, 
            0,                     
            GL10.GL_RGBA,               
            this.getImage().getWidth(), 
            this.getImage().getHeight(),
            0,    
            GL10.GL_RGBA,
            GL10.GL_UNSIGNED_BYTE,
            null);
    
    glTexSubImage2D(GL10.GL_TEXTURE_2D, 
               0,
               0,
               0,
               this.getImage().getWidth(), 
               this.getImage().getHeight(),
               GL10.GL_RGBA,
               GL10.GL_UNSIGNED_BYTE, 
               );
    */
    
    public void load(int target, int level, Image image, int border)
    {
        //return GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, this.image.getBitmap(), 0);
        GLUtils.texImage2D(target, level, image.getBitmap(), border);
    }
    
    /*
    public int load(GL10 gl, Image image)
    {
        return this.loadTexture(gl, image.getBitmap());
    }

    protected ByteBuffer makeByteBuffer(Bitmap bmp)
    {

        return super.makeByteBuffer(bmp, ByteBuffer.allocateDirect(bmp.getHeight() * bmp.getWidth()
                * 4), bmp.getHeight(), bmp.getWidth());
    }

    protected int loadTexture(GL10 gl, int resource) {
        return loadTexture(gl, BitmapFactory.decodeResource(
                ResourceUtil.getInstance().getResources(), resource));
    }
    */

    /**
     * Create a texture and send it to the graphics system
     * 
     * @param gl
     *            The GL object
     * @param bmp
     *            The bitmap of the texture
     * @param reverseRGB
     *            Should the RGB values be reversed? (necessary workaround for
     *            loading .pngs...)
     * @return The newly created identifier for the texture.
     */
    /*
    private int loadTexture(GL10 gl, Bitmap bmp)
    {
        int[] tmp_tex = new int[1];

        gl.glGenTextures(1, tmp_tex, 0);
        int tex = tmp_tex[0];
        loadTexture(tex, GL10.GL_TEXTURE_2D, bmp, gl);
        return tex;
    }

    public void loadTexture(int texture, int type,  int resource, GL gl) {
        loadTexture(texture, type, BitmapFactory.decodeResource(
                ResourceUtil.getInstance().getResources(), resource), gl);
    }

    private void loadTexture(int texture, int type, Bitmap bmp, GL gl)
    {
        loadTexture(texture, type, bmp.getWidth(), bmp.getHeight(), makeByteBuffer(bmp), gl);
    }

    private void loadTexture(int texture, int type, int width, int height, ByteBuffer bb, GL gl)
    {
        gl.glBindTexture(type, texture);
        // GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bmp, 0);
        gl.glTexImage2D(type, 0, GL10.GL_RGBA, width, height, 0, GL10.GL_RGBA,
                GL10.GL_UNSIGNED_BYTE, bb);
        gl.glTexParameterf(type, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_LINEAR);
        gl.glTexParameterf(type, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);

    }
    */
}
