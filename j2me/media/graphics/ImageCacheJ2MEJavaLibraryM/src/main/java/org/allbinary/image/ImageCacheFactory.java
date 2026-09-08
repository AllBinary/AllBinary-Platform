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

//J2ME
/*//JSNI Expose so JSNI can access this class *** &#10;
globalThis.org.allbinary = globalThis.org.allbinary || {}; &#10;
globalThis.org.allbinary.image globalThis.org.allbinary.image || {}; &#10;
globalThis.org.allbinary.image.ImageCacheFactory = ImageCacheFactory; &#10;
console.log('Exported ImageCacheFactory as globalThis'); &#10;
*/
public class ImageCacheFactory
{
    private final static ImageCache IMAGE_CACHE = new ImageCache();

    public static ImageCache getInstance()
    {
        return ImageCacheFactory.IMAGE_CACHE;
    }

    //While the image cache really can't be cleared currently 
    //it could be in the future
    //This is because Images are not really freed on J2ME and 
    //require special attention and special calls for Android
    public static void init()
    {
        /*        
        if(ImageCacheFactory.IMAGE_CACHE == null)
        {
            ImageCacheFactory.IMAGE_CACHE = new ImageCache();
        }
        */
    }
}
