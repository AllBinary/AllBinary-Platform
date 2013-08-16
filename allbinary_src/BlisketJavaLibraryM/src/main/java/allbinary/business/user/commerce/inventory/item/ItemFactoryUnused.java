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
package allbinary.business.user.commerce.inventory.item;

import allbinary.business.user.commerce.money.MoneyException;
import allbinary.data.tables.TableMappingInterface;
import allbinary.logic.communication.http.request.RequestParams;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public class ItemFactoryUnused
{
   private ItemFactoryUnused()
   {
   }
   
   public static TableMappingInterface getInstance(HashMap hashMap) throws MoneyException 
   {
      return (TableMappingInterface) new Item(hashMap);
   }

   public static TableMappingInterface getInstance(HttpServletRequest request) throws Exception, MoneyException 
   {
      return (TableMappingInterface) new Item(new RequestParams(request).toHashMap());
   }
}
