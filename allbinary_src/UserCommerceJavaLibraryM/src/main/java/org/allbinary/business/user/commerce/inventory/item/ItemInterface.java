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
package org.allbinary.business.user.commerce.inventory.item;

import org.allbinary.business.user.commerce.money.Money;
import org.allbinary.data.tables.TableMappingInterface;

public interface ItemInterface extends TableMappingInterface
{
   void setId(String itemId);
   
   void setNumber(String number);
   
   void setInBaskets(String value);
   
   void setWeight(String value);
   
   void setEnabled(String value);
   
   void setNewOrUsed(String value);
   
   void setSummary(String value);
   
   void setDistributor(String value);
   
   void setIdUsedByDistributor(String value);
   
   void setProducedBy(String value);
   
   void setProductionDate(String value);
      
   void setStartProductionDate(String value);
   
   void setDescription(String value);
   
   void setKeywords(String value);
   
   void setCategory(String value);
   
   void setType(String value);
   
   void setSmallImage(String value);
   
   void setMediumImage(String value);
   
   void setLargeImage(String value);
   
   void setTimeEntered(String value);
   
   void setLastModified(String value);
   
   void setPrice(Money value);
   
   void setComment(String value);
   
   void setCustoms(String value);
   
   void setDownloads(String value);
   
   void setGroups(String value);
   
   void setOptions(String value);
   
   void setPermissions(String value);
   
   void setSpecials(String value);
   
   String getId();
   
   String getNumber();

   String getInBaskets();

   String getWeight();
   
   String getEnabled();
   
   String getNewOrUsed();
   
   String getSummary();

   String getDistributor();
   
   String getIdUsedByDistributor();

   String getProducedBy();

   String getProductionDate();
      
   String getStartProductionDate();

   String getDescription();
   
   String getKeywords();

   String getCategory();

   String getType();

   String getSmallImage();
   
   String getMediumImage();
   
   String getLargeImage();

   String getTimeEntered();

   String getLastModified();

   Money getPrice();

   String getComment();

   String getCustoms();

   String getDownloads();
   
   String getGroups();

   String getOptions();

   String getPermissions();
 
   String getSpecials();
   
   Money getTotal();
}
