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
package admin.taghelpers;

import java.util.HashMap;

import javax.servlet.jsp.PageContext;
import org.allbinary.business.context.modules.storefront.StoreFront;

import org.allbinary.string.CommonSeps;
import org.allbinary.logic.string.regex.replace.Replace;
import org.allbinary.business.context.modules.storefront.StoreFrontData;
import org.allbinary.business.context.modules.storefront.StoreFrontFactory;
import org.allbinary.business.context.modules.storefront.StoreFrontInterface;
import org.allbinary.logic.string.StringUtil;

public class StoreFrontHelper
    extends TagHelper
{
   private final StoreFrontInterface storeFrontInterface;
   private final String currentLocation;
   private final String currentHomeLocation;

   public StoreFrontHelper(HashMap hashMap, PageContext pageContext)
   {
      String storeName = (String) hashMap.get(StoreFrontData.getInstance().NAME);
      
      if(storeName!=null)
      {
         this.storeFrontInterface = StoreFrontFactory.getInstance(storeName);

         this.currentLocation =
             new Replace(CommonSeps.getInstance().SPACE, "%20").all(
             storeFrontInterface.getCurrentHostName() + storeFrontInterface.getCurrentHostNamePath());

         this.currentHomeLocation =
             new Replace(CommonSeps.getInstance().SPACE, "%20").all(
             storeFrontInterface.getCurrentHomeHostName() + storeFrontInterface.getCurrentHomeHostNamePath());
      }
      else
      {
          final StringUtil stringUtil = StringUtil.getInstance();
          this.storeFrontInterface = new StoreFront();
          this.currentLocation = stringUtil.EMPTY_STRING;
          this.currentHomeLocation = stringUtil.EMPTY_STRING;
      }
   }
   
   public String getCurrentLocation()
   {
      return this.currentLocation;
   }
   
   public String getCurrentHomeLocation()
   {
      return this.currentHomeLocation;
   }
}
