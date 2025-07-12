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

import org.allbinary.data.tree.dom.DomNodeInterface;
import org.allbinary.data.tree.dom.ModDomHelper;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.control.validate.Validation;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.logic.visual.dhtml.style.StylesData;
import org.allbinary.logic.visual.transform.template.customizer.bodies.BodyData;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class GenericCssStyleValidation extends Validation implements DomNodeInterface
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    
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
            logUtil.put(this.commonStrings.START, this, commonStrings.IS_VALID);
         }

         /*
         // CssValidation[] stylesArray = (CssValidation[]) this.styles.toArray(new CssValidation[0]);
         // int size = stylesArray.length;
         // for(int i = 0; i < size; i++)
         // {
         //    CssValidation style = stylesArray[i];
         //    if(!StringValidationUtil.isValidNotRequired(style, BodyData.MIN, BodyData.MAX))
         //    {
         //       isValid = Boolean.FALSE;
         //    }
         // }
         */
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
         {
            logUtil.put("End: " + isValid, this, commonStrings.IS_VALID);
         }
         
         return isValid;
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
         {
            logUtil.put("Failed to validate form", this, commonStrings.IS_VALID, e);
         }
         return Boolean.FALSE;
      }
   }

   public String validationInfo()
   {
      try
      {
         //Boolean isValid = Boolean.TRUE;
         
         StringMaker stringBuffer = new StringMaker();
         
         return stringBuffer.toString();
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEWERROR))
         {
            logUtil.put("Failed to generate validation error info", this, "validationInfo()", e);
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