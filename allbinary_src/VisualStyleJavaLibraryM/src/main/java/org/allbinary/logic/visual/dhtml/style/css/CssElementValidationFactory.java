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

public class CssElementValidationFactory
{
   private CssElementValidationFactory()
   {
   }
   
   //Node is CssElementData.NAME
   public static ValidationInterface getInstance(Node node) throws Exception
   {
      return (ValidationInterface) new CssElementValidation(node);
   }

   /*
   public static ValidationInterface getInstance(HashMap hashMap)
   {
      return (ValidationInterface) new CssElementValidation(hashMap);
   }
    **/
}
