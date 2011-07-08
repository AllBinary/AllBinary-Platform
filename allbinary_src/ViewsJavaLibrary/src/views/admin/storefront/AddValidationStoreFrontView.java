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
package views.admin.storefront;

import abcs.globals.URLGLOBALS;
import abcs.logic.basic.io.file.AbFile;
import abcs.logic.basic.path.AbPath;
import abcs.logic.communication.log.LogFactory;
import javax.servlet.http.HttpServletRequest;

import org.w3c.dom.Node;
import org.w3c.dom.Document;


import abcs.logic.communication.log.LogUtil;


import allbinary.business.context.modules.storefront.StoreFront;
import allbinary.business.context.modules.storefront.StoreFrontInterface;
import allbinary.business.context.modules.storefront.StoreFrontFactory;
import allbinary.globals.FREEBLISKET_PATH_GLOBALS;

import allbinary.logic.visual.transform.info.TransformInfoInterface;

import allbinary.logic.control.validate.ValidationComponentInterface;

import views.business.context.HttpContextView;

public class AddValidationStoreFrontView extends HttpContextView 
   implements ValidationComponentInterface
{
   protected StoreFrontInterface newStoreFrontInterface;
   
   public AddValidationStoreFrontView(TransformInfoInterface transformInfoInterface)throws Exception
   {
      super(transformInfoInterface);
      
      this.newStoreFrontInterface = (StoreFrontInterface) 
         new StoreFront((HttpServletRequest) this.getPageContext().getRequest());

      //the storeName should always be in the session since only a store manager 
      //or higher can manage a store
      if(this.newStoreFrontInterface.getName()==null)
         this.newStoreFrontInterface.setName(
            this.getWeblisketSession().getStoreName());
      
   }
      
   public Boolean isValid()
   {
      try
      {
         Boolean valid = Boolean.TRUE;
         
         if(this.newStoreFrontInterface.isValid() == Boolean.FALSE)
         {
            valid = Boolean.FALSE;
         }         
         
         if(StoreFrontFactory.getInstance(this.newStoreFrontInterface.getName())!=null)
         {
            valid = Boolean.FALSE;
         }

         if(new AbFile(new AbPath(this.getStoreViewsPath())).isFile())
         {
             valid = Boolean.FALSE;
         }

         return valid;
      }
      catch(Exception e)
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEWERROR))
         {
            LogUtil.put(LogFactory.getInstance("Failed to validate form", this, "isValid", e));
         }
         return Boolean.FALSE;
      }
   }

   private String getStoreViewsPath() throws Exception
   {
         StringBuffer stringBuffer = new StringBuffer();

         stringBuffer.append(URLGLOBALS.getMainPath());
         stringBuffer.append(FREEBLISKET_PATH_GLOBALS.getInstance().XSLPATH);
         stringBuffer.append(this.newStoreFrontInterface.getName());

         return stringBuffer.toString();
   }

   public String validationInfo()
   {
      try
      {
         StringBuffer stringBuffer = new StringBuffer();
         
         if(this.newStoreFrontInterface.isValid() == Boolean.FALSE)
         {
            stringBuffer.append(this.newStoreFrontInterface.validationInfo());
         }         
         
         if(StoreFrontFactory.getInstance(this.newStoreFrontInterface.getName())!=null)
         {
            stringBuffer.append("Store name already used<br/>");
         }

         if(new AbFile(new AbPath(this.getStoreViewsPath())).isFile())
         {
             stringBuffer.append("Store name clashes with template name<br/>");
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
   
   public String view() throws Exception
   {
      return views.ValidationOnlyTempUtil.view(this);
   }
   
}
