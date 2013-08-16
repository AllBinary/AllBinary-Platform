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
package allbinary.logic.visual.transform.template.customizer.page;

import abcs.data.tree.dom.DomSearchHelper;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.business.page.PageData;
import allbinary.data.tree.dom.DomNodeInterface;
import allbinary.logic.control.validate.ValidationInterface;
import allbinary.logic.visual.transform.template.customizer.widgets.title.TitleData;
import allbinary.logic.visual.transform.template.customizer.widgets.title.TitleValidation;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import java.util.HashMap;

public class PageValidation implements ValidationInterface, DomNodeInterface
{
   private TitleValidation title;
   
   public PageValidation()
   {
      this.title = new TitleValidation();
   }

   public PageValidation(Document document) throws Exception
   {
      Node node = DomSearchHelper.getNode(
         TitleData.getInstance().NAME,
         document.getElementsByTagName(PageData.getInstance().NAME));
      
      this.title = new TitleValidation(node);
   }
   
   public PageValidation(HashMap hashMap)
   {
      this.getFormData(hashMap);
   }
   
   public void getFormData(HashMap hashMap)
   {
      this.title = new TitleValidation(hashMap);
   }
   
   public Boolean isValid()
   {
      try
      {
         Boolean valid = Boolean.TRUE;

         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEW))
         {
            LogUtil.put(LogFactory.getInstance("PageValidation", this, "isValid()"));
         }
         
         if(!this.title.isValid().booleanValue())
         {
            valid = Boolean.FALSE;
         }
         
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEW))
         {
            LogUtil.put(LogFactory.getInstance("PageValidation: " + valid, this, "isValid()"));
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
         StringBuffer stringBuffer = new StringBuffer();

         if(!this.title.isValid().booleanValue())
         {
            stringBuffer.append(this.title.validationInfo());
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
      Node node = document.createElement(PageData.getInstance().NAME);
      node.appendChild(this.title.toXmlNode(document));
      return node;
   }
}