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
package org.allbinary.logic.visual.transform.template.customizer.includes.style.css;

import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import org.allbinary.logic.string.StringUtil;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.data.tree.dom.DomNodeInterface;
import org.allbinary.data.tree.dom.ModDomHelper;
import org.allbinary.logic.control.validate.ValidationInterface;
import org.allbinary.logic.visual.dhtml.style.StylesData;
import org.allbinary.logic.visual.transform.template.customizer.bodies.BodyData;
import org.allbinary.string.CommonStrings;

public class GenericCssStyleValidation implements ValidationInterface, DomNodeInterface
{
    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    
   //private Vector styles;

   public GenericCssStyleValidation()
   {
      //this.styles = new Vector();
   }

   public GenericCssStyleValidation(Document document) throws Exception
   {
      NodeList nodeList = document.getElementsByTagName(StylesData.getInstance().NAME);

      //Future implementation to parse a css file
   }

   public GenericCssStyleValidation(HashMap hashMap) throws Exception
   {
      this.getFormData(hashMap);
   }

   public void getFormData(HashMap hashMap) throws Exception
   {
      //this.styles = CssValidationFactory.getInstance(hashMap);
   }

   public Boolean isValid()
   {
      try
      {
         Boolean isValid = Boolean.TRUE;

         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
         {
            LogUtil.put(LogFactory.getInstance(this.commonStrings.START, this, "isValid()"));
         }

         /*
         Iterator iter = this.styles.iterator();
         
         while(iter.hasNext())
         {
            CssValidation style = (CssValidation)
            if(!StringValidationUtil.isValidNotRequired(style, BodyData.MIN, BodyData.MAX))
            {
               isValid = Boolean.FALSE;
            }
         }
         */
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
         {
            LogUtil.put(LogFactory.getInstance("End: " + isValid, this, "isValid()"));
         }
         
         return isValid;
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance("Failed to validate form", this, "isValid()", e));
         }
         return Boolean.FALSE;
      }
   }

   public String validationInfo()
   {
      try
      {
         //Boolean isValid = Boolean.TRUE;
         
         StringBuffer stringBuffer = new StringBuffer();
         
         return stringBuffer.toString();
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEWERROR))
         {
            LogUtil.put(LogFactory.getInstance("Failed to generate validation error info", this, "validationInfo()", e));
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
      Node node = 
         ModDomHelper.createNameValueNodes(document, BodyData.getInstance().NAME, 
            StringUtil.getInstance().EMPTY_STRING);

      return node;
   }
}