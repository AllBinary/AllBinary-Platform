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
import org.allbinary.graphics.opengles.renderer.AllBinaryRendererBase3;

import org.allbinary.util.BasicArrayList;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.image.ImageCache;
import org.allbinary.image.ImageCacheFactory;
import org.allbinary.image.PreResourceImageUtil;
import org.allbinary.logic.string.CommonLabels;
import org.allbinary.logic.string.StringMaker;

public class OpenGLImageCache extends ImageCache
{
    private final ImageCache imageCache = ImageCacheFactory.getInstance();
    private final PreResourceImageUtil preResourceImageUtil = PreResourceImageUtil.getInstance();

    private GL10 gl;
    
    private final BasicArrayList list = new BasicArrayList();
    
    private AllBinaryRendererBase3 renderer = new AllBinaryRendererBase3();
    
    protected OpenGLImageCache()
    {
    }
    
    public void addListener(Object renderer) {
        this.renderer = (AllBinaryRendererBase3) renderer;
    }
    
    public void update(final GL10 gl) throws Exception
    {
        //LogUtil.put(LogFactory.getInstance(CommonLabels.getInstance().START_LABEL + list, this, commonStrings.UPDATE));
     
        this.gl = gl;
        
        for(int index = list.size() - 1; index >= 0; index--)
        {
            ((OpenGLESImage) list.objectArray[index]).set(gl);
        }
    }

    @Override
    protected Image createImage(final String caller, int width, int height)
    throws Exception
    {             
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
        
        final Image image2 = this.imageCache.get(caller, width, height);
        final Image image = preResourceImageUtil.encapsulate(image2);
        
        //LogUtil.put(LogFactory.getInstance("opengl: createImageD: " + image.getName(), this, commonStrings.CREATE));
        list.add(image);
        return image; 
    }

    @Override
    protected Image createImage(final Object key, final InputStream inputStream)
    throws Exception
    {
        final Image cachedImage = this.imageCache.get(key);

        //...
        //Use fake images
        //return new OpenGLESImage(cachedImage);
        final Image image = preResourceImageUtil.encapsulate(cachedImage);

        //LogUtil.put(LogFactory.getInstance("opengl: createImage: " + image.getName(), this, commonStrings.CREATE));
        list.add(image);

        //ForcedLogUtil.log(image.toString(), this);
        //LogUtil.put(LogFactory.getInstance(key + " = " + image.toString(), this, commonStrings.GET));

        return image;
    }

    public GL10 getGl()
    {
        return gl;
    }
    
    public void init(final Image image) {
        try {
            //LogUtil.put(LogFactory.getInstance(new StringMaker().append("opengl: init add ").append(image).append(image.getName()).toString(), this, commonStrings.INIT));
            
            if(list.contains(image)) {
                throw new RuntimeException();
            }
            
            list.add(image);
            this.renderer.list.add(image);

        } catch(Exception e) {
            LogUtil.put(LogFactory.getInstance(this.commonStrings.EXCEPTION, this, commonStrings.INIT, e));
        }
    }

}
