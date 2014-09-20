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

import org.allbinary.logic.basic.util.event.BasicEventListenerInterface;

public interface ImageComparisonSearchResultsListener 
   extends BasicEventListenerInterface
{
   public void onImageComparisonResultsEvent(
      ImageComparisonSearchResultsEvent imageComparisonSearchResultsEvent);
}
