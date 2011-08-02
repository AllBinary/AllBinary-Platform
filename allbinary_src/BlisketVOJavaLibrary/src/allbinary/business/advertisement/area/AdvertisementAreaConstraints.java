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
package allbinary.business.advertisement.area;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import abcs.data.tree.dom.DomSearchHelper;

import allbinary.logic.control.contraints.display.browser.DisplayInBrowserContraints;
import allbinary.logic.control.contraints.display.browser.DisplayInBrowserContraintsInterface;

import allbinary.logic.control.contraints.size.TwoDimensionalConstraint;
import allbinary.logic.control.contraints.size.TwoDimensionalConstraintInterface;

public class AdvertisementAreaConstraints 
   implements AdvertisementAreaConstraintsInterface
{
   private DisplayInBrowserContraintsInterface displayInBrowserContraintsInterface;
   private TwoDimensionalConstraintInterface twoDimensionConstraintsInterface;
   
   public AdvertisementAreaConstraints(Document document) throws Exception
   {
      Node node = 
         DomSearchHelper.getNode(
            AdvertisementAreaData.getInstance().NAME, 
            document.getChildNodes());

      this.displayInBrowserContraintsInterface = 
         new DisplayInBrowserContraints(node);

      this.twoDimensionConstraintsInterface = 
         new TwoDimensionalConstraint(node);
   }

   public void setDisplayInBrowserContraints(
      DisplayInBrowserContraintsInterface displayInBrowserContraintsInterface)
   {
      this.displayInBrowserContraintsInterface = displayInBrowserContraintsInterface;
   }

   public void getTwoDimensionContraints(
      TwoDimensionalConstraintInterface twoDimensionConstraintsInterface)
   {
      this.twoDimensionConstraintsInterface = twoDimensionConstraintsInterface;
   }

   public DisplayInBrowserContraintsInterface getDisplayInBrowserContraints()
   {
      return this.displayInBrowserContraintsInterface;
   }
   
   public TwoDimensionalConstraintInterface getTwoDimensionContraints()
   {
      return this.twoDimensionConstraintsInterface;
   }
}
