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
package allbinary.logic.visual.transform.template.customizer.includes.style.css.template.retail;

import allbinary.data.tree.dom.DomNodeInterface;
import allbinary.logic.control.validate.ValidationInterface;
import allbinary.logic.visual.dhtml.style.StyleData;
import allbinary.logic.visual.dhtml.style.StylesData;
import allbinary.logic.visual.dhtml.style.color.ColorsView;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import java.util.HashMap;
import java.util.Iterator;

public class EditCssStyleValidation extends CssStyleValidation 
   implements ValidationInterface, DomNodeInterface
{
   public EditCssStyleValidation()
   {
   }

   public EditCssStyleValidation(Document document) throws Exception
   {
      super(document);
   }

   public EditCssStyleValidation(HashMap hashMap) throws Exception
   {
      super(hashMap);
   }

   public Boolean isValid()
   {
      return super.isValid();
   }

   public String validationInfo()
   {
      return super.validationInfo();
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
      Node node = document.createElement(StylesData.getInstance().NAME);
      Node styleNode = document.createElement(StyleData.getInstance().NAME);
      node.appendChild(styleNode);

      styleNode.appendChild(new ColorsView().toXmlNode(document));

      Iterator iter = this.cssStyleElementVector.iterator();
      while(iter.hasNext())
      {
         DomNodeInterface styleDomNodeInterface = (DomNodeInterface) iter.next();
         styleNode.appendChild(styleDomNodeInterface.toXmlNode(document));
      }
      return node;
   }
}