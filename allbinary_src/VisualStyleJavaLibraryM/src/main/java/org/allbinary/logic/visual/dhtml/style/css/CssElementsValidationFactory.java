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
package org.allbinary.logic.visual.dhtml.style.css;

import org.allbinary.logic.control.validate.ValidationInterface;
import org.w3c.dom.Node;

import java.util.HashMap;
import java.util.Vector;

public class CssElementsValidationFactory
{
   private CssElementsValidationFactory()
   {
   }
   
   //Nodes with CssElementData.NAME
   public static Vector getInstance(Vector cssElementStyleNodeList) throws Exception
   //NodeList cssElementStyleNodeList
   {
      Vector styles = new Vector();
      
      for(int index = 0; index < cssElementStyleNodeList.size(); index++)
      {
         Node cssElementStyleNode = (Node) cssElementStyleNodeList.get(index);

         ValidationInterface cssValidationInterface = (ValidationInterface)
           CssElementValidationFactory.getInstance(cssElementStyleNode);

         styles.add(cssValidationInterface);
      }
      return styles;
   }
   
   public static Vector getInstance(HashMap hashMap)
   {
      Vector styles = new Vector();
      return styles;
   }
}
