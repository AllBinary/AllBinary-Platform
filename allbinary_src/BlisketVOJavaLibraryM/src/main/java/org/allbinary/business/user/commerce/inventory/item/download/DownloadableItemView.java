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
package org.allbinary.business.user.commerce.inventory.item.download;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Set;

import org.allbinary.data.tree.dom.DomNodeInterface;
import org.allbinary.data.tree.dom.ModDomHelper;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.string.CommonStrings;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class DownloadableItemView implements DomNodeInterface
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    
   private DownloadableItem downloadableItem;
   
   public DownloadableItemView(DownloadableItem downloadableItem)
   {
      this.downloadableItem = downloadableItem;
   }
   
   public Node toXmlNode(Document document) throws Exception
   {
       if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().PRODUCTSEARCHLOGGING))
       {
          logUtil.put(this.commonStrings.START, this, "toXmlNode");
       }

      /*
      try
      {
       */
         HashMap hashMap = downloadableItem.toHashMap();

         long totalTime = downloadableItem.getValidTime().longValue();

         Calendar calendar = Calendar.getInstance();

         int year = calendar.get(Calendar.YEAR);
         int month = calendar.get(Calendar.MONTH);
         int day = calendar.get(Calendar.DAY_OF_MONTH);
         int hour = calendar.get(Calendar.HOUR);
         int minute = calendar.get(Calendar.MINUTE);
         int second = calendar.get(Calendar.SECOND);

         calendar.setTimeInMillis(calendar.getTimeInMillis() + totalTime);

         int yearDelta = calendar.get(Calendar.YEAR);
         int monthDelta = calendar.get(Calendar.MONTH);
         int dayDelta = calendar.get(Calendar.DAY_OF_MONTH);
         int hourDelta = calendar.get(Calendar.HOUR);
         int minuteDelta = calendar.get(Calendar.MINUTE);
         int secondDelta = calendar.get(Calendar.SECOND);

         hashMap.put(DownloadItemData.VALID_TIME_YEARS, Integer.valueOf(yearDelta - year));
         hashMap.put(DownloadItemData.VALID_TIME_MONTHS, Integer.valueOf(monthDelta - month));
         hashMap.put(DownloadItemData.VALID_TIME_DAYS, Integer.valueOf(dayDelta - day));
         hashMap.put(DownloadItemData.VALID_TIME_HOURS, Integer.valueOf(hourDelta - hour));
         hashMap.put(DownloadItemData.VALID_TIME_MINUTES, Integer.valueOf(minuteDelta - minute));
         hashMap.put(DownloadItemData.VALID_TIME_SECONDS, Integer.valueOf(secondDelta - second));

         //hashMap.put(BasicItemData.IMAGE, StringUtil.getInstance());
         
         Set keySet = hashMap.keySet();
         
         Node node = document.createElement(DownloadItemData.NAME);
         
         StringUtil stringUtil = StringUtil.getInstance();
         
         final Object[] nameArray = keySet.toArray();
         final int size = nameArray.length;
         for (int index = 0; index < size; index++)
         {
            String name = (String) nameArray[index];
            String value = (String) hashMap.get(name);
            
            value = stringUtil.getInstance(value);
            
            node.appendChild(ModDomHelper.createNameValueNodes(document, name, value));
         }
                  
         return node;
         /*
      }
      catch(Exception e)
      {
         throw e;
      }
          */
   }
   
   public Document toXmlDoc()
   {
      return null;
   }
   
   public String view()
   {
      return null;
   }
   
}
