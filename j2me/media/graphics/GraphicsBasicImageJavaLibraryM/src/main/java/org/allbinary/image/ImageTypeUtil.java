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

import org.allbinary.util.BasicArrayList;

public class ImageTypeUtil
{
    private static ImageTypeUtil SINGLETON = new ImageTypeUtil();
 
    public final String PNG = "png";
    public final String JPG = "jpg";
    public final String GIF = "gif";
    
    private final BasicArrayList list = new BasicArrayList();
    
    private ImageTypeUtil()
    {
        list.add(JPG);
        list.add(GIF);
        list.add(PNG);
    }
    
    //MPEG4, H.264, MP3, AAC, AMR, JPG, PNG, GIF
    public boolean isSupported(String mediaFileType)
    {
        mediaFileType = mediaFileType.toLowerCase();
        for(int index = list.size() - 1; index >= 0; index--)
        {
            String name = (String) list.get(index);
            if(mediaFileType.compareTo(name) == 0)
            {
                return true;
            }
        }

        return false;
    }
    
    public static ImageTypeUtil getInstance()
    {
        return SINGLETON;
    }
}
