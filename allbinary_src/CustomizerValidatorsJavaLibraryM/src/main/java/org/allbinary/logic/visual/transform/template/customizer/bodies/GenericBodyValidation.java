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
package org.allbinary.logic.visual.transform.template.customizer.bodies;

import java.util.HashMap;

import org.allbinary.data.tree.dom.DomData;
import org.allbinary.data.tree.dom.DomNodeHelper;
import org.allbinary.data.tree.dom.DomNodeInterface;
import org.allbinary.data.tree.dom.DomSearchHelper;
import org.allbinary.data.tree.dom.ModDomHelper;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.communication.sql.AbSqlData;
import org.allbinary.logic.control.validate.Validation;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.logic.string.StringValidationUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class GenericBodyValidation extends Validation implements DomNodeInterface
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    
   private String body;

   public GenericBodyValidation()
   {
      this.body = StringUtil.getInstance().EMPTY_STRING;
   }

   public GenericBodyValidation(Document document) throws Exception
   {
      NodeList nodeList = document.getElementsByTagName(BodyData.getInstance().NAME);
      
      for(int index = 0; index < nodeList.getLength(); index++)
      {
         Node aBodyNode = nodeList.item(index);
                  
         Node aBodyValueNode = DomSearchHelper.getNode(
            DomData.VALUE,
            aBodyNode.getChildNodes());

         this.body = DomNodeHelper.getTextNodeValue(aBodyValueNode);
      }
   }

   public GenericBodyValidation(HashMap hashMap) throws Exception
   {
      this.getFormData(hashMap);
   }

   public void getFormData(HashMap hashMap) throws Exception
   {
      this.body = (String) hashMap.get(BodyData.getInstance().NAME);
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

         if(!StringValidationUtil.getInstance().isValidNotRequired(this.body, BodyData.getInstance().MIN, AbSqlData.MAXBLOB))
         {
            isValid = Boolean.FALSE;
         }

         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
         {
            logUtil.put("BodyValidation: " + isValid, this, commonStrings.IS_VALID);
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
         
         //if(!isValid.booleanValue())
         {
            stringBuffer.append("Error: Data submitted is < ");
            stringBuffer.append(BodyData.getInstance().MIN);
            stringBuffer.append(" or > ");
            stringBuffer.append(AbSqlData.MAXBLOB);
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
      Node node = ModDomHelper.createNameValueNodes(
        		 document, BodyData.getInstance().NAME, this.body);

      return node;
   }
}