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
package allbinary.logic.visual.transform.template.customizer.bodies.title;

import abcs.data.tree.dom.DomNodeHelper;
import abcs.data.tree.dom.DomSearchHelper;
import abcs.logic.basic.string.StringValidationUtil;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.data.tree.dom.DomData;
import allbinary.data.tree.dom.DomNodeInterface;
import allbinary.data.tree.dom.ModDomHelper;
import allbinary.logic.communication.sql.AbSqlData;
import allbinary.logic.control.validate.ValidationInterface;
import allbinary.logic.visual.transform.template.customizer.bodies.BodyData;
import allbinary.logic.visual.transform.template.customizer.widgets.title.TitleData;
import allbinary.logic.visual.transform.template.customizer.widgets.title.TitleNotRequiredValidation;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.HashMap;

public class TitleBodyValidation implements ValidationInterface, DomNodeInterface
{
   private String body;
   private TitleNotRequiredValidation titleValidation;

   public TitleBodyValidation()
   {
      if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEW))
      {
         LogUtil.put(LogFactory.getInstance("Start", this, "Constructor()"));
      }

      this.body = "";
      this.titleValidation = new TitleNotRequiredValidation();
   }

   public TitleBodyValidation(Document document) throws Exception
   {
      if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEW))
      {
         LogUtil.put(LogFactory.getInstance("Constructor()",this,"Constructor(document)"));
      }

      NodeList nodeList = document.getElementsByTagName(BodyData.getInstance().NAME);

      for(int index = 0; index < nodeList.getLength(); index++)
      {
         Node aBodyNode = nodeList.item(index);

         Node aBodyValueNode = DomSearchHelper.getNode(
            DomData.VALUE,
            aBodyNode.getChildNodes());

         this.body = DomNodeHelper.getTextNodeValue(aBodyValueNode);

         Node titleNode = 
            DomSearchHelper.getNode(TitleData.getInstance().NAME, aBodyNode.getChildNodes());

         this.titleValidation = new TitleNotRequiredValidation(titleNode);

         if(index > 1)
         {
            if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEW))
            {
               LogUtil.put(LogFactory.getInstance("To Many Body Nodes", this, "Constructor()"));
            }
         }
      }
   }

   public TitleBodyValidation(HashMap hashMap) throws Exception
   {
      if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEW))
      {
         LogUtil.put(LogFactory.getInstance("Start", this, "Constructor(hashmap)"));
      }
      
      this.getFormData(hashMap);
   }

   public void getFormData(HashMap hashMap) throws Exception
   {
      this.body = (String) hashMap.get(BodyData.getInstance().NAME);
      this.titleValidation = new TitleNotRequiredValidation(hashMap);
   }

   public Boolean isValid()
   {
      try
      {
         Boolean isValid = Boolean.TRUE;

         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEW))
         {
            LogUtil.put(LogFactory.getInstance("TitleBodyValidation", this, "isValid()"));
         }

         if(!StringValidationUtil.getInstance().isValidNotRequired(this.body, BodyData.getInstance().MIN, AbSqlData.MAXBLOB))
         {
            isValid = Boolean.FALSE;
         }

         if(!this.titleValidation.isValid().booleanValue())
         {
            isValid = Boolean.FALSE;
         }
         
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEW))
         {
            LogUtil.put(LogFactory.getInstance("TitleBodyValidation: " + isValid,this,"isValid()"));
         }
         
         return isValid;
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
         //Boolean isValid = Boolean.TRUE;
         
         StringBuffer stringBuffer = new StringBuffer();
         
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
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEWERROR))
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
      if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEW))
      {
         LogUtil.put(LogFactory.getInstance("Title Body: " + this.body, this, "toXmlNode(document)"));
      }

      Node node = 
         ModDomHelper.createNameValueNodes(document, BodyData.getInstance().NAME, 
            this.body);

      node.appendChild(this.titleValidation.toXmlNode(document));

      return node;
   }
}