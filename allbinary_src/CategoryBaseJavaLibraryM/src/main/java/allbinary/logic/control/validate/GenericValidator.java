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
package allbinary.logic.control.validate;

import org.w3c.dom.Document;
import org.w3c.dom.Node;


public class GenericValidator extends ValidatorBase
{   
   public GenericValidator()
   {
   }

   public Document toValidationInfoDoc()
   {
      return null;
   }   
   
   public Node toValidationInfoNode(org.w3c.dom.Document document)
   {
      return null;
   }
}