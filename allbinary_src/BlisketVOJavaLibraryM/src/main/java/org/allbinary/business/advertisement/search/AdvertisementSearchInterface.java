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
   public AdvertisementWebsiteInterface getWebSite();

   public AdvertisementCategoryInterface getCategory();
   
   public TwoDimensionalConstraintInterface getSizeConstraints();
   
   public PriceConstraintInterface getPricingConstraints();
   
   public int getPageNumber();
   
   public int getProductsPerPage();
   
   public HashMap getSearchParams();
   
   //public String getSortOrder();

   public void setCategory(AdvertisementCategoryInterface advertisementCategoryInterface);

   public void setSizeConstraints(
      TwoDimensionalConstraintInterface twoDimensionConstraintsInterface);
   
   public void setPricingConstraints(
      PriceConstraintInterface priceConstraintInterface);
   
   public void setPageNumber(int pageNumber);
   
   public void setProductsPerPage(int productsPerPage);
   
   public void setSearchParams(HashMap columnAndKeywordHashMap);
   
   //public void setSortOrder(String columnName);
}
