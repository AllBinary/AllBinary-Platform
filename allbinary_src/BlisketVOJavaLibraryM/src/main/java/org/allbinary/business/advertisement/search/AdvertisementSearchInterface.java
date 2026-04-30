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
package org.allbinary.business.advertisement.search;

import java.util.HashMap;

import org.allbinary.business.advertisement.search.category.AdvertisementCategoryInterface;
import org.allbinary.business.advertisement.search.website.AdvertisementWebsiteInterface;
import org.allbinary.logic.control.contraints.price.PriceConstraintInterface;
import org.allbinary.logic.control.contraints.size.TwoDimensionalConstraintInterface;

public interface AdvertisementSearchInterface
{
   AdvertisementWebsiteInterface getWebSite();

   AdvertisementCategoryInterface getCategory();
   
   TwoDimensionalConstraintInterface getSizeConstraints();
   
   PriceConstraintInterface getPricingConstraints();
   
   int getPageNumber();
   
   int getProductsPerPage();
   
   HashMap getSearchParams();
   
   //public String getSortOrder();

   void setCategory(AdvertisementCategoryInterface advertisementCategoryInterface);

   void setSizeConstraints(
      TwoDimensionalConstraintInterface twoDimensionConstraintsInterface);
   
   void setPricingConstraints(
      PriceConstraintInterface priceConstraintInterface);
   
   void setPageNumber(int pageNumber);
   
   void setProductsPerPage(int productsPerPage);
   
   void setSearchParams(HashMap columnAndKeywordHashMap);
   
   //public void setSortOrder(String columnName);
}
