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
package abcs.logic.visual.media;

import allbinary.logic.visual.media.MediaTypeData;
import java.util.HashMap;

public class MediaData
{
   private static HashMap hashMap = new HashMap();

   private String name;
   private String type;
   private String extension;
   
   protected MediaData(String a_Name)
   {
      this.name = a_Name;
      this.extension = "." + this.name;
      this.type = MediaTypeData.getInstance().NOT_RESIZABLE_MEDIA;

      MediaData.hashMap.put(a_Name, this);
   }

   public String getName()
   {
      return this.name;
   }

   public String getType()
   {
      return this.type;
   }

   public String getExtension()
   {
       return this.extension;
   }
   
   public boolean isConvertableTo(MediaData mediaData)
   {
      if(this == MediaDataFactory.getInstance().GIF && 
    		  mediaData == MediaDataFactory.getInstance().JPG)
      {
         return true;
      }
      return false;
   }

   public String toString()
   {
      return this.getName();
   }

   public static MediaData get(String a_Name) throws Exception
   {
      MediaData mediaData = (MediaData) MediaData.hashMap.get(a_Name);
      if(mediaData != null)
      {
         return mediaData;
      }
      else
      {
         throw new Exception("No Such MediaData");
      }
   }

   public static MediaData getDefault()
   {
      return MediaDataFactory.getInstance().JPG;
   }

   public static HashMap toHashMap()
   {
      return MediaData.hashMap;
   }
}
