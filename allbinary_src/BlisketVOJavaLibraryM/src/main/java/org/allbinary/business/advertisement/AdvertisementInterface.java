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
package org.allbinary.business.advertisement;

import org.allbinary.business.advertisement.image.AdvertisementImageInterface;
import org.allbinary.business.advertisement.product.AdvertisementProductInterface;
import org.allbinary.business.advertisement.thumbnail.AdvertisementThumbnailInterface;
import org.allbinary.business.user.commerce.money.Money;
import org.allbinary.logic.control.contraints.display.browser.DisplayInBrowserContraintsInterface;

public interface AdvertisementInterface
{
   String getArtist();
   String getArtistLink();
   String getTitle();

   String getDateAdded();
   String getDirectory();
   String getFramedLink();

   Money getListPrice();
   Money getOurPrice();

   int getRank();

   AdvertisementImageInterface getImage();

   AdvertisementProductInterface getProduct();

   AdvertisementThumbnailInterface getThumbnail();

   DisplayInBrowserContraintsInterface getDisplayInBrowserContraintsInterface();

   void setArtist(String artist);
   void setArtistLink(String artistLink);
   void setTitle(String title);

   void setDateAdded(String dateAdded);
   void setDirectory(String directory);
   void setFramedLink(String framedLink);

   //void setListPrice(double listPrice);
   //void setOurPrice(double ourPrice);

   void setRank(int rank);

   void setImage(AdvertisementImageInterface AdvertisementImageInterface);

   void setProduct(AdvertisementProductInterface AdvertisementProductInterface);

   void setThumbnail(AdvertisementThumbnailInterface AdvertisementThumbnailInterface);
}
