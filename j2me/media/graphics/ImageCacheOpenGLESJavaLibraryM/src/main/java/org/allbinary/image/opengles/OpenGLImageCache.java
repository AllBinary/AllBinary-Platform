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
import javax.microedition.lcdui.NullImage;
import org.allbinary.device.NullGL10;

import org.allbinary.graphics.opengles.renderer.AllBinaryRendererBase3;
import org.allbinary.util.BasicArrayList;
import org.allbinary.util.BasicArrayListD;
import org.allbinary.image.ImageCache;
import org.allbinary.image.ImageCacheFactory;
import org.allbinary.image.PreResourceImageUtil;
import org.allbinary.thread.SynchObject;

public class OpenGLImageCache extends ImageCache
{
    
    private final ImageCache imageCache = ImageCacheFactory.getInstance();
    private final PreResourceImageUtil preResourceImageUtil = PreResourceImageUtil.getInstance();

    private GL10 gl = NullGL10.NULL_GL10;
    
    private final SynchObject lock = new SynchObject();
    private final BasicArrayList list = new BasicArrayListD();
    
    private AllBinaryRendererBase3 renderer = new AllBinaryRendererBase3();
    
    //protected
    public OpenGLImageCache()
    {
    }
    
    @Override
    public void addListener(Object renderer) {
        this.renderer = (AllBinaryRendererBase3) renderer;
    }
    
    public void update(final GL10 gl) throws Exception
    {
        //this.logUtil.putF(CommonLabels.getInstance().START_LABEL + list, this, this.commonStrings.UPDATE);
     
        this.gl = gl;
        
        synchronized(this.lock) {
            for (int index = this.list.size() - 1; index >= 0; index--) {
                OpenGLESImage openGLESImage = ((OpenGLESImage) this.list.objectArray[index]);
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
        final Image image = this.preResourceImageUtil.encapsulate(image2);
        
        //this.logUtil.putF("opengl: createImageD: " + image.getName(), this, this.commonStrings.CREATE);
        synchronized(this.lock) {
            if(image != NullImage.NULL_IMAGE) {
                this.list.add(image);
            }
        }
        
        return image; 
    }

    @Override
    protected Image createImageFromInputStream(final Object key, final InputStream inputStream)
    throws Exception
    {
        final Image cachedImage = this.imageCache.getWithKey(key);

        //...
        //Use fake images
        //return new OpenGLESImage(cachedImage);
        final Image image = this.preResourceImageUtil.encapsulate(cachedImage);

        //this.logUtil.putF("opengl: createImage: " + image.getName(), this, this.commonStrings.CREATE);
        synchronized(this.lock) {
            if(image != NullImage.NULL_IMAGE) {
                this.list.add(image);
            }
        }

        //ForcedLogUtil.log(image.toString(), this);
        //this.logUtil.putF(key + " = " + image.toString(), this, this.commonStrings.GET);

        return image;
    }

    public GL10 getGlP()
    {
        return this.gl;
    }
    
    @Override
    public void init(final Image image) {
        try {
            //this.logUtil.putF(new StringMaker().append("opengl: init add ").append(image).append(image.getName()).toString(), this, this.commonStrings.INIT);
            
            synchronized(this.lock) {
                if (this.list.contains(image)) {
                    throw new RuntimeException();
                }

                this.list.add(image);
            }
            
            this.renderer.add(image);

        } catch(Exception e) {
            this.logUtil.put(this.commonStrings.EXCEPTION, this, this.commonStrings.INIT, e);
        }
    }

}
