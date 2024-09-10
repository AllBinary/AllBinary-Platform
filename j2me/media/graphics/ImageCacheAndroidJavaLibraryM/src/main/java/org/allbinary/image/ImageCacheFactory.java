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
package org.allbinary.image;

public class ImageCacheFactory
{
    private final static ImageCache IMAGE_CACHE = new ImageCache();

    public static ImageCache getInstance()
    {
        return IMAGE_CACHE;
    }

    //While the image cache really can't be cleared currently 
    //it could be in the future
    //This is because Images are not really freed on J2ME and 
    //require special attention and special calls for Android
    public static void init()
    {
        /*        
        if(IMAGE_CACHE == null)
        {
            IMAGE_CACHE = new ImageCache();
        }
        */
    }
}
