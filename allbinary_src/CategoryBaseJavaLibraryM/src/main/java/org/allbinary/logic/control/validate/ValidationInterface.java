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
package org.allbinary.logic.control.validate;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

public interface ValidationInterface
{
   //Form or other data validation 
   Boolean isValid() throws Exception;

   Node toValidationInfoNode(Document document) throws Exception;

   Document toValidationInfoDoc() throws Exception;

   String validationInfo() throws Exception;
}