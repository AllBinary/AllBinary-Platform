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
package org.allbinary.business.advertisement.area;

import org.allbinary.logic.control.contraints.display.browser.DisplayInBrowserContraintsInterface;
import org.allbinary.logic.control.contraints.size.TwoDimensionalConstraintInterface;

public interface AdvertisementAreaConstraintsInterface
{
   public DisplayInBrowserContraintsInterface getDisplayInBrowserContraints();
   public TwoDimensionalConstraintInterface getTwoDimensionContraints();
}
