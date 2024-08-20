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

import java.io.InputStream;

import javax.microedition.khronos.opengles.GL10;
import javax.microedition.lcdui.Image;
import org.allbinary.graphics.OpenGLBitmapFactory;

import org.allbinary.util.BasicArrayList;

import org.allbinary.logic.string.CommonStrings;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.image.ImageCache;
import org.allbinary.image.ImageCacheFactory;
import org.allbinary.logic.string.CommonLabels;
import org.platform.opengl.OpenGLTextureFactory;

public class OpenGLImageCache extends ImageCache
{
    private GL10 gl;
    
    private final BasicArrayList list = new BasicArrayList();
    
    protected OpenGLImageCache()
    {
    }
    
    public void update(GL10 gl) throws Exception
    {
        LogUtil.put(LogFactory.getInstance(CommonLabels.getInstance().START_LABEL + list, this, CommonStrings.getInstance().UPDATE));
     
        this.gl = gl;
        
        for(int index = list.size() - 1; index >= 0; index--)
        {
            ((OpenGLESImage) list.objectArray[index]).set(gl);
        }
    }

    protected Image createImage(String caller, int width, int height)
    throws Exception
    {
        OpenGLImageFactory imageFactory = 
            OpenGLImageSpecificFactory.getInstance().getImageFactory();
             
        int textureSize = width;
        
        if(height > width)
        {
            textureSize = height;
        }
        
        while((textureSize % 4) != 0)
        {
            textureSize++;
        }
        
        width = textureSize;
        height = textureSize;
        
        final Image image = imageFactory.getInstance(
                ImageCacheFactory.getInstance().get(
                        caller, width, height),
            OpenGLBitmapFactory.getInstance(),
            OpenGLTextureFactory.getInstance()
            );
        list.add(image);
        return image; 
    }

    protected Image createImage(Object key, InputStream inputStream)
    throws Exception
    {
        OpenGLImageFactory imageFactory = 
            OpenGLImageSpecificFactory.getInstance().getImageFactory();

        Image cachedImage = ImageCacheFactory.getInstance().get(key);

        //...
        //Use fake images
        //return new OpenGLESImage(cachedImage);
        final Image image = imageFactory.getInstance(cachedImage,
            OpenGLBitmapFactory.getInstance(),
            OpenGLTextureFactory.getInstance()
            );

        list.add(image);

        //ForcedLogUtil.log(image.toString(), this);
        //LogUtil.put(LogFactory.getInstance(key + " = " + image.toString(), this, CommonStrings.getInstance().GET));

        return image;
    }

    public GL10 getGl()
    {
        return gl;
    }
}
