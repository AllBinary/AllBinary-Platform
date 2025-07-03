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
package org.allbinary.logic.visual.transform.template.customizer.includes.style.css.template.retail;

import java.util.HashMap;
import java.util.Vector;

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.data.tree.dom.DomNodeInterface;
import org.allbinary.logic.control.validate.Validation;
import org.allbinary.logic.control.validate.ValidationInterface;
import org.allbinary.logic.visual.dhtml.style.StyleData;
import org.allbinary.logic.visual.dhtml.style.StylesValidationFactory;
import org.allbinary.logic.visual.dhtml.style.StylesData;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class CssStyleValidation extends Validation implements DomNodeInterface
{
    
   protected Vector cssStyleElementVector;

   public CssStyleValidation()
   {
      this.cssStyleElementVector = new Vector();
   }

   public CssStyleValidation(Document document) throws Exception
   {
      this.cssStyleElementVector = StylesValidationFactory.getInstance().getInstance(document);
   }

   public CssStyleValidation(HashMap hashMap) throws Exception
   {
      this.cssStyleElementVector = StylesValidationFactory.getInstance(hashMap);
   }

   public Boolean isValid()
   {
      try
      {
         Boolean isValid = Boolean.TRUE;

         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
         {
            LogUtil.put(LogFactory.getInstance(this.commonStrings.START,this,commonStrings.IS_VALID));
         }

         int size = cssStyleElementVector.size();
         for (int i = 0; i < size; i++)
         {
            ValidationInterface styleValidationInterface = 
               (ValidationInterface) cssStyleElementVector.get(i);

            if(!styleValidationInterface.isValid().booleanValue())
            {
               isValid = Boolean.FALSE;
            }
         }

         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
         {
            LogUtil.put(LogFactory.getInstance("End: " + isValid,this,commonStrings.IS_VALID));
         }
         
         return isValid;
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance("Failed to validate form",this,commonStrings.IS_VALID,e));
         }
         return Boolean.FALSE;
      }
   }

   public String validationInfo()
   {
      try
      {
         StringBuffer stringBuffer = new StringBuffer();
         
         return stringBuffer.toString();
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEWERROR))
         {
            LogUtil.put(LogFactory.getInstance("Failed to generate validation error info",this,"validationInfo()",e));
         }
         return "Error Validating Form";
      }
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

      int size = cssStyleElementVector.size();
      for (int i = 0; i < size; i++)
      {
         DomNodeInterface styleDomNodeInterface = (DomNodeInterface) cssStyleElementVector.get(i);
         styleNode.appendChild(styleDomNodeInterface.toXmlNode(document));
      }
      return node;
   }
}