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
import javax.microedition.lcdui.NullCanvas;
import org.allbinary.graphics.opengles.renderer.AllBinaryRendererBase3;

import org.allbinary.util.BasicArrayList;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.image.ImageCache;
import org.allbinary.image.ImageCacheFactory;
import org.allbinary.image.PreResourceImageUtil;
import org.allbinary.thread.SynchObject;

public class OpenGLImageCache extends ImageCache
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private final ImageCache imageCache = ImageCacheFactory.getInstance();
    private final PreResourceImageUtil preResourceImageUtil = PreResourceImageUtil.getInstance();

    private GL10 gl;
    
    private final SynchObject lock = new SynchObject();
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
        //logUtil.put(CommonLabels.getInstance().START_LABEL + list, this, commonStrings.UPDATE);
     
        this.gl = gl;
        
        synchronized(lock) {
            for (int index = list.size() - 1; index >= 0; index--) {
                OpenGLESImage openGLESImage = ((OpenGLESImage) list.objectArray[index]);
                if(openGLESImage != OpenGLESImage.NULL_OPENGL_IMAGE) {
                    openGLESImage.set(gl);
                }
            }
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
        
        //logUtil.put("opengl: createImageD: " + image.getName(), this, commonStrings.CREATE);
        synchronized(lock) {
            if(image != NullCanvas.NULL_IMAGE) {
                list.add(image);
            }
        }
        
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

        //logUtil.put("opengl: createImage: " + image.getName(), this, commonStrings.CREATE);
        synchronized(lock) {
            if(image != NullCanvas.NULL_IMAGE) {
                list.add(image);
            }
        }

        //ForcedLogUtil.log(image.toString(), this);
        //logUtil.put(key + " = " + image.toString(), this, commonStrings.GET);

        return image;
    }

    public GL10 getGlP()
    {
        return gl;
    }
    
    public void init(final Image image) {
        try {
            //logUtil.put(new StringMaker().append("opengl: init add ").append(image).append(image.getName()).toString(), this, commonStrings.INIT);
            
            synchronized(lock) {
                if (list.contains(image)) {
                    throw new RuntimeException();
                }

                list.add(image);
            }
            
            this.renderer.add(image);

        } catch(Exception e) {
            logUtil.put(this.commonStrings.EXCEPTION, this, commonStrings.INIT, e);
        }
    }

}
