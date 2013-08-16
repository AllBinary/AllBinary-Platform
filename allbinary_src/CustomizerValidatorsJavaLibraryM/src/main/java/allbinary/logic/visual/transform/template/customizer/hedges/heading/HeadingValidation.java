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
package allbinary.logic.visual.transform.template.customizer.hedges.heading;

import abcs.data.tree.dom.DomSearchHelper;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.data.tree.dom.DomNodeInterface;
import allbinary.logic.control.validate.ValidationInterface;
import allbinary.logic.visual.transform.template.customizer.widgets.logo.LogoData;
import allbinary.logic.visual.transform.template.customizer.widgets.logo.LogoValidation;
import allbinary.logic.visual.transform.template.customizer.widgets.title.TitleData;
import allbinary.logic.visual.transform.template.customizer.widgets.title.TitleValidation;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.HashMap;

public class HeadingValidation implements ValidationInterface, DomNodeInterface
{   
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

         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEW))
         {
            LogUtil.put(LogFactory.getInstance("Start", this,"isValid()"));
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
         
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEW))
         {
            LogUtil.put(LogFactory.getInstance("HeadingValidation: " + valid, this, "isValid()"));
         }
         
         return valid;
      }
      catch(Exception e)
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGSERROR))
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
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGSERROR))
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
      Node node = document.createElement(HeadingData.NAME);
      node.appendChild(this.logo.toXmlNode(document));
      node.appendChild(this.title.toXmlNode(document));
      return node;
   }
}