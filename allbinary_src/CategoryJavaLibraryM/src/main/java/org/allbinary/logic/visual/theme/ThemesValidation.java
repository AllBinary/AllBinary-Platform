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
package org.allbinary.logic.visual.theme;

import java.util.HashMap;
import java.util.Vector;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import org.allbinary.data.tree.dom.DomNodeInterface;
import org.allbinary.logic.control.validate.Validation;

public class ThemesValidation extends Validation implements DomNodeInterface
{
   private Vector themeVector;
   
   public ThemesValidation()
   {
      this.themeVector = new Vector();
   }

   public ThemesValidation(Node node) throws Exception
   {
   }

   public ThemesValidation(HashMap hashMap) throws Exception
   {
   }

   public Boolean isValid()
   {
      return Boolean.TRUE;
   }

   public String validationInfo()
   {
      return null;
   }

   public Document toValidationInfoDoc()
   {
      return null;
   }

   public Node toValidationInfoNode(Document document)
   {
      return null;
   }
 
   public Node toXmlNode(Document document) throws Exception
   {
      Node node = document.createElement(ThemesData.getInstance().NAME);
      
      int size = themeVector.size();
      for (int i = 0; i < size; i++)
      {
         DomNodeInterface themeDomNodeInterface = (DomNodeInterface) themeVector.get(i);
         node.appendChild(themeDomNodeInterface.toXmlNode(document));
      }
      return node;
   }
}