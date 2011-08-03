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
package allbinary.data.tables.advertisement.areas;

import allbinary.business.advertisement.area.AdvertisementAreaInterface;
import allbinary.data.tables.BasicTableInterface;

import java.util.HashMap;
import java.util.Vector;

public interface AdvertisementAreasEntityInterface extends BasicTableInterface
{   
   //AdvertisementAreaInterface
   public Vector get(String storeName) throws Exception;

   public AdvertisementAreaInterface get(
      String storeName, String advertisementAreaName) throws Exception;

   public void delete(String value);

   public void insert(Vector values);

   public void update(HashMap updatedValues);
}
