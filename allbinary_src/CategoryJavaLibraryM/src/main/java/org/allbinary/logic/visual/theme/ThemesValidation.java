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

import org.allbinary.logic.visual.theme.ThemesData;
import org.allbinary.data.tree.dom.DomNodeInterface;
import org.allbinary.logic.control.validate.ValidationInterface;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

public class ThemesValidation implements ValidationInterface, DomNodeInterface
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
      
      Iterator iter = this.themeVector.iterator();
      while(iter.hasNext())
      {
         DomNodeInterface themeDomNodeInterface = (DomNodeInterface) iter.next();
         node.appendChild(themeDomNodeInterface.toXmlNode(document));
      }
      return node;
   }
}