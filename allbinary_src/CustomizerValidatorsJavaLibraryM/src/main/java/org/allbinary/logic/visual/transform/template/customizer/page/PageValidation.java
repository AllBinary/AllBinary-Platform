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
package org.allbinary.logic.visual.transform.template.customizer.page;

import java.util.HashMap;

import org.allbinary.business.page.PageData;
import org.allbinary.data.tree.dom.DomNodeInterface;
import org.allbinary.data.tree.dom.DomSearchHelper;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.control.validate.Validation;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.visual.transform.template.customizer.widgets.title.TitleData;
import org.allbinary.logic.visual.transform.template.customizer.widgets.title.TitleValidation;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class PageValidation extends Validation implements DomNodeInterface
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    
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

         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
         {
            logUtil.put("PageValidation", this, commonStrings.IS_VALID);
         }
         
         if(!this.title.isValid().booleanValue())
         {
            valid = Boolean.FALSE;
         }
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
         {
            logUtil.put("PageValidation: " + valid, this, commonStrings.IS_VALID);
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
         StringMaker stringBuffer = new StringMaker();

         if(!this.title.isValid().booleanValue())
         {
            stringBuffer.append(this.title.validationInfo());
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
      Node node = document.createElement(PageData.getInstance().NAME);
      node.appendChild(this.title.toXmlNode(document));
      return node;
   }
}