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
package org.allbinary.logic.visual.transform.template.customizer.hedges.heading;

import java.util.HashMap;

import org.allbinary.data.tree.dom.DomSearchHelper;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.data.tree.dom.DomNodeInterface;
import org.allbinary.logic.control.validate.Validation;
import org.allbinary.logic.visual.transform.template.customizer.widgets.logo.LogoData;
import org.allbinary.logic.visual.transform.template.customizer.widgets.logo.LogoValidation;
import org.allbinary.logic.visual.transform.template.customizer.widgets.title.TitleData;
import org.allbinary.logic.visual.transform.template.customizer.widgets.title.TitleValidation;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class HeadingValidation extends Validation implements DomNodeInterface
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    
   private TitleValidation title;
   private LogoValidation logo;
      
   public HeadingValidation()
   {
      this.title = new TitleValidation();
      this.logo  = new LogoValidation();
   }

   public HeadingValidation(Document document) throws Exception
   {
      NodeList headingNodeList = document.getElementsByTagName(HeadingData.NAME);
      
      Node titleNode = DomSearchHelper.getNode(
         TitleData.getInstance().NAME, headingNodeList.item(0).getChildNodes());
     
      Node logoNode = DomSearchHelper.getNode(
         LogoData.getInstance().NAME, headingNodeList.item(0).getChildNodes());

      this.title = new TitleValidation(titleNode);
      this.logo  = new LogoValidation(logoNode);
   }
   
   public HeadingValidation(HashMap hashMap) throws Exception
   {
      this.getFormData(hashMap);
   }
   
   public void getFormData(HashMap hashMap) throws Exception
   {
      this.title = new TitleValidation(hashMap);
      this.logo  = new LogoValidation(hashMap);
   }
   
   public Boolean isValid()
   {
      try
      {
         Boolean valid = Boolean.TRUE;
         Boolean titleValid = Boolean.TRUE;
         Boolean logoValid = Boolean.TRUE;

         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
         {
            logUtil.put(this.commonStrings.START, this,commonStrings.IS_VALID);
         }
         
         if(!this.title.isValid().booleanValue())
         {
            titleValid = Boolean.FALSE;
         }

         if(!this.logo.isValid().booleanValue())
         {
   logoValid = Boolean.FALSE;
         }
         else
         {
            this.logo.processLogoFile();
         }

         if(!titleValid.booleanValue() && !logoValid.booleanValue())
         {
            valid = Boolean.FALSE;
         }                  
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
         {
            logUtil.put("HeadingValidation: " + valid, this, commonStrings.IS_VALID);
         }
         
         return valid;
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
         Boolean valid = Boolean.TRUE;
         Boolean titleValid = Boolean.TRUE;
         Boolean logoValid = Boolean.TRUE;
         
         StringBuffer stringBuffer = new StringBuffer();

         if(!this.title.isValid().booleanValue())
         {
            titleValid = Boolean.FALSE;
         }

         if(!this.logo.isValid().booleanValue())
         {
   logoValid = Boolean.FALSE;
         }

         if(!titleValid.booleanValue() && !logoValid.booleanValue())
         {
            valid = Boolean.FALSE;
         }
         
         if(!valid.booleanValue())
         {
            stringBuffer.append("Include a title and/or logo for the header.");
         }
         
         return stringBuffer.toString();
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
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
      Node node = document.createElement(HeadingData.NAME);
      node.appendChild(this.logo.toXmlNode(document));
      node.appendChild(this.title.toXmlNode(document));
      return node;
   }
}