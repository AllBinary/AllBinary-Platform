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

public class BaseTextureFactory
{
    protected BaseTextureFactory()
    {

    }

    /*
    public FloatBuffer makeFloatBuffer(float[] arr) {
        ByteBuffer bb = ByteBuffer.allocateDirect(arr.length*4);
        bb.order(ByteOrder.nativeOrder());
        FloatBuffer fb = bb.asFloatBuffer();
        fb.put(arr);
        fb.position(0);
        return fb;
    }
    
    public IntBuffer makeIntBuffer(int[] arr) {
        ByteBuffer bb = ByteBuffer.allocateDirect(arr.length*4);
        bb.order(ByteOrder.nativeOrder());
        IntBuffer ib = bb.asIntBuffer();
        ib.put(arr);
        ib.position(0);
        return ib;
    }
    
    protected ByteBuffer makeByteBuffer(Bitmap bmp, ByteBuffer bb, int height, int width)
    {
        bb.order(ByteOrder.BIG_ENDIAN);
        IntBuffer ib = bb.asIntBuffer();

        for (int y = 0; y < height; y++)
            for (int x = 0; x < width; x++)
            {
                int pix = bmp.getPixel(x, height - y - 1);
                // Convert ARGB -> RGBA
                byte alpha = (byte) ((pix >> 24) & 0xFF);
                byte red = (byte) ((pix >> 16) & 0xFF);
                byte green = (byte) ((pix >> 8) & 0xFF);
                byte blue = (byte) ((pix) & 0xFF);

                // It seems like alpha is currently broken in Android...
                ib.put(((red & 0xFF) << 24) | ((green & 0xFF) << 16) | ((blue & 0xFF) << 8)
                        | ((alpha & 0xFF))); // 255-alpha);
            }
        ib.position(0);
        bb.position(0);
        return bb;
    }

    public int load(GL10 gl, Image img)
    {
        return -1;
    }
     */
}
