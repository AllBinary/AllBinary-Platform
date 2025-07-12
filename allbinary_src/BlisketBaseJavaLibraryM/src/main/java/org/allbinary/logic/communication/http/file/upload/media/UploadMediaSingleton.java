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
package org.allbinary.logic.communication.http.file.upload.media;

import java.util.HashMap;
import java.util.Set;

import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.visual.media.MediaData;
import org.allbinary.logic.visual.media.MediaIOUtil;
import org.allbinary.logic.visual.media.MediaTypeData;
import org.allbinary.string.CommonStrings;

public class UploadMediaSingleton
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    
   private HashMap readerFileTypesHashMap;
   private HashMap writerFileTypesHashMap;
   private static UploadMediaSingleton uploadMedia;

   private UploadMediaSingleton()
   {
      this.readerFileTypesHashMap = new HashMap();
      this.writerFileTypesHashMap = new HashMap();

      final HashMap hashMap = MediaData.toHashMap();
      final Set set = hashMap.keySet();

       final Object[] mediaDataNameArray = set.toArray();
       final int size = mediaDataNameArray.length;
       for (int index = 0; index < size; index++)      {
         String mediaDataName = (String) mediaDataNameArray[index];
         MediaData mediaData = (MediaData) hashMap.get(mediaDataName);
         this.readerFileTypesHashMap.put(mediaData.getName(), mediaData.getType());
      }

      MediaIOUtil mediaIOUtil = MediaIOUtil.getInstance();

      final String RESIZABLE_MEDIA = MediaTypeData.getInstance().RESIZABLE_MEDIA;
      
      String[] readerFileTypes = mediaIOUtil.getReaderFormatNames();
      //StringMaker readerFileTypesBuffer = new StringMaker();
      for(int index = 0; index < readerFileTypes.length; index++)
      {
         this.readerFileTypesHashMap.put(
            readerFileTypes[index], RESIZABLE_MEDIA);
      }

      String[] writerFileTypes = mediaIOUtil.getWriterFormatNames();
      //StringMaker writerFileTypesBuffer = new StringMaker();
      for(int index = 0; index < writerFileTypes.length; index++)
      {
         this.writerFileTypesHashMap.put(
            writerFileTypes[index], RESIZABLE_MEDIA);
      }

        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().HTTPREQUEST))
        {
            StringMaker stringBuffer = new StringMaker();

            stringBuffer.append("Supported Media Readers: ");
            stringBuffer.append(this.readerFileTypesHashMap.toString());
            stringBuffer.append(" Writers: ");
            stringBuffer.append(this.writerFileTypesHashMap.toString());

            logUtil.put(stringBuffer.toString(), this, this.commonStrings.CONSTRUCTOR);
        }
   }

   public static synchronized UploadMediaSingleton getInstance()
   {
      if(UploadMediaSingleton.uploadMedia == null)
      {
         UploadMediaSingleton.uploadMedia = new UploadMediaSingleton();
      }

      return UploadMediaSingleton.uploadMedia;
   }

   public boolean isReaderSupported(String mediaFileType)
   {
      return this.readerFileTypesHashMap.containsKey(mediaFileType);
   }

   public boolean isWriterSupported(String mediaFileType)
   {
      return this.readerFileTypesHashMap.containsKey(mediaFileType);
   }

   public boolean isSupported(String mediaFileType)
   {
      return this.isReaderSupported(mediaFileType) && 
         this.isWriterSupported(mediaFileType);
   }

   public boolean isMedia(String mediaFileType, String aMediaType)
   {
      return this.isReaderMedia(mediaFileType, aMediaType) &&
         this.isWriterMedia(mediaFileType, aMediaType);
   }

   public boolean isReaderMedia(String mediaFileType, String aMediaType)
   {
      return this.isMedia(mediaFileType, aMediaType, this.readerFileTypesHashMap);
   }

   public boolean isWriterMedia(String mediaFileType, String aMediaType)
   {
      return this.isMedia(mediaFileType, aMediaType, this.writerFileTypesHashMap);
   }

   private boolean isMedia(String mediaFileType, String aMediaType, HashMap hashMap)
   {
      if(hashMap.containsKey(mediaFileType))
      {
         String mediaType = (String) hashMap.get(mediaFileType);

         if(mediaType.compareTo(aMediaType) == 0)
         {
            return true;
         }
         else
         {
            return false;
         }
      }
      else
      {
         return false;
      }
   }
}
