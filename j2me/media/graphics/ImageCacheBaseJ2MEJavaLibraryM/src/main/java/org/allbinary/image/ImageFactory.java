/*
 * AllBinary Open License Version 1
 * Copyright (c) 2025 AllBinary
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
package org.allbinary.image;

import java.io.InputStream;

import javax.microedition.lcdui.Image;

/**
 *
 * @author User
 */
//ImageCacheBaseJavaLibrary - J2ME
public class ImageFactory {

    private static final ImageFactory instance = new ImageFactory();
    
    /**
     * @return the instance
     */
    public static ImageFactory getInstance() {
        return ImageFactory.instance;
    }
    
    public Image createImageUrl(final String url) throws Exception
    {
        return Image.createImage(url);
    }
    
    public Image createImage(final String caller, final int width, final int height) throws Exception
    {
        return Image.createImage(width, height);
    }
    
    public Image createImageFromInputStream(final Object key, final InputStream inputStream) throws Exception
    {
        final Image image = Image.createImage(inputStream);
        //image.setName((String) key + image.getName());
        //this.logUtil.putF(image.getName(), this, "createImage");
        //image.init(image.getImage());
        return image;
    }
     
}
