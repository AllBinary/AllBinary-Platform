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
package org.allbinary.logic.visual.dhtml.style.css.property;

import java.util.HashMap;
import org.allbinary.util.BasicArrayList;
import org.allbinary.util.BasicArrayListD;
import org.allbinary.logic.StdUtil;

import org.allbinary.logic.control.validate.ValidationInterface;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class CssPropertiesValidationFactory
{
   private CssPropertiesValidationFactory()
   {
   }
   
   //Create Properties from Nodes with CssPropertyData.NAME
   public static BasicArrayList getInstance(NodeList nodeList) throws Exception
   {
      BasicArrayList propertiesVector = new BasicArrayListD();
      
      for(int index = 0; index < nodeList.getLength(); index++)
      {
         Node node = nodeList.item(index);
       
         //Some Nodes May Not Be Property Nodes
         if(node.getNodeName().compareTo(CssPropertyData.getInstance().NAME)==0)
         {
            ValidationInterface cssPropertyValidationInterface = 
               (ValidationInterface)
                  CssPropertyValidationFactory.getInstance(node);
         
            propertiesVector.add(cssPropertyValidationInterface);
         }
      }
      return propertiesVector;
   }
   
   public static BasicArrayList getInstance(HashMap hashMap)
   {
      BasicArrayList styles = new BasicArrayListD();
      return styles;
   }
}
