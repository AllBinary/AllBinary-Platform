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
package org.allbinary.media.image.search;

import org.allbinary.logic.util.event.AllBinaryEventObject;

public class ImageComparisonSearchResultsEvent extends AllBinaryEventObject
{
   private ImageComparisonSearch imageComparisonSearch;

   public ImageComparisonSearchResultsEvent(
      Object object, ImageComparisonSearch imageComparisonSearch)
   {
      super(object);
      this.setImageComparisonSearch(imageComparisonSearch);
   }

   public ImageComparisonSearch getImageComparisonSearch()
   {
      return imageComparisonSearch;
   }

   private void setImageComparisonSearch(ImageComparisonSearch imageComparisonSearch)
   {
      this.imageComparisonSearch = imageComparisonSearch;
   }
}