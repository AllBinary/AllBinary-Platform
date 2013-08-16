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
package allbinary.business.advertisement;

public interface AdvertisementsInterface
{
   public void add(AdvertisementInterface advertisementInterface);
   public AdvertisementInterface get(int index);
   
   public int getCurrentPageNum();
   public String getErrorDescription();
   public String getErrorNum();
   public int getNumberOfPosters();
   public int getNumberOfProductsInPage();
   public int getStatusCode();
   public int getTotalNumberOfProducts();
   public boolean isSearchValid();

   //If status code = 0 search is valid, if statuscode is 1 search is invalid.
   public void setCurrentPageNum(int currentPageNum);
   public void setErrorDescription(String errorDescription);
   public void setErrorNum(String errorNum);
   public void setNumberOfProductsInPage(int numberOfProductsInPage);
   public void setStatusCode(int statusCode);
   public void setTotalNumberOfProducts(int totalNumberOfProducts);

   public String toString();
}
