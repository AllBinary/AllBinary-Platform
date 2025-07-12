/*
* AllBinary Open License Version 1
* Copyright (c) 2011 AllBinary
* 
* By agreeing to this license you and any business entity you represent are
* legally bound to the AllBinary Open License Version 1 legal agreement.
* 
* You may obtain the AllBinary Open License Version 1 legal agreement from
* AllBinary or the root directory of AllBinary's AllBinary Platform repository.
* II
* Created By: Travis Berthelot
* 
*/
package org.allbinary.logic.visual.transform.template.customizer.includes.meta;

import java.util.Vector;

import org.allbinary.business.context.modules.storefront.StoreFrontFactory;
import org.allbinary.business.context.modules.storefront.StoreFrontInterface;
import org.allbinary.data.tree.dom.DomNodeInterface;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.control.validate.Validation;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.visual.dhtml.html.head.meta.HtmlMetaAttributeDataFactory;
import org.allbinary.logic.visual.dhtml.html.head.meta.HtmlMetaAttributeValuesData;
import org.allbinary.logic.visual.dhtml.html.head.meta.HtmlMetasData;
import org.allbinary.string.CommonStrings;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class MetasValidation extends Validation implements DomNodeInterface
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    
   private StoreFrontInterface storeFrontInterface;
   private Vector metaValidationVector;
   
   //new
   public MetasValidation(String storeName)
   {
      this.storeFrontInterface = StoreFrontFactory.getInstance(storeName);
      this.metaValidationVector = new Vector();

      String contentValue = this.storeFrontInterface.getName() + " E-Commerce Site";

      MetaValidation keywordsMetaValidation = 
         new MetaValidation(HtmlMetaAttributeDataFactory.getInstance().NAME, 
            "Keywords", HtmlMetaAttributeValuesData.getInstance().KEYWORDS, contentValue);

      MetaValidation abstractMetaValidation = 
         new MetaValidation(HtmlMetaAttributeDataFactory.getInstance().NAME, 
            "Abstract", HtmlMetaAttributeValuesData.getInstance().ABSTRACT, contentValue);

      MetaValidation descriptionMetaValidation = 
         new MetaValidation(HtmlMetaAttributeDataFactory.getInstance().NAME, 
            "Description", HtmlMetaAttributeValuesData.getInstance().DESCRIPTION, contentValue);

      this.metaValidationVector.add(keywordsMetaValidation);
      this.metaValidationVector.add(abstractMetaValidation);
      this.metaValidationVector.add(descriptionMetaValidation);
   }
   
   //from db
   public MetasValidation(Document document) throws Exception
   {
      throw new Exception(CommonStrings.getInstance().NOT_IMPLEMENTED);
      //this.storeFrontInterface = new StoreFront(document);
   }
   
   //with request data
   /*
   public GlobalsValidation(HashMap hashMap)
   {
      
   }
     */
   
   public Boolean isValid()
   {
      try
      {
         Boolean valid = Boolean.TRUE;

         /*
         if(!this.isValid().booleanValue() && !this.logo.isValid().booleanValue())
         {
            valid = Boolean.FALSE;
         }
         */

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

         /*
         if(!this.isValid().booleanValue() && !this.isValid().booleanValue())
         {
            stringBuffer.append("Include a title or logo for the header.");
         }
         */

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
      Node node = document.createElement(HtmlMetasData.getInstance().NAME);

      MetaValidation[] metaArray = (MetaValidation[]) this.metaValidationVector.toArray(new MetaValidation[0]);
      int size = metaArray.length;
      for(int i = 0; i < size; i++)
      {
         MetaValidation metaValidation = metaArray[i];
         node.appendChild(metaValidation.toXmlNode(document));
      }

      //node.appendChild(new StoreFrontView(
        // this.storeFrontInterface).toXmlNode(document));

      return node;
   }
}