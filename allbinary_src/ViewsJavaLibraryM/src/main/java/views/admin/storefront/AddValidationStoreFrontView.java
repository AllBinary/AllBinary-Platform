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

import javax.servlet.http.HttpServletRequest;

import org.allbinary.business.context.modules.storefront.StoreFront;
import org.allbinary.business.context.modules.storefront.StoreFrontFactory;
import org.allbinary.business.context.modules.storefront.StoreFrontInterface;
import org.allbinary.globals.FREEBLISKET_PATH_GLOBALS;
import org.allbinary.globals.URLGLOBALS;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.control.validate.ValidationComponentInterface;
import org.allbinary.logic.io.file.AbFile;
import org.allbinary.logic.io.path.AbPath;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.visual.transform.info.TransformInfoInterface;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import views.business.context.HttpContextView;

public class AddValidationStoreFrontView extends HttpContextView 
   implements ValidationComponentInterface
{
    protected final LogUtil logUtil = LogUtil.getInstance();

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
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEWERROR))
         {
            logUtil.put("Failed to validate form", this, commonStrings.IS_VALID, e);
         }
         return Boolean.FALSE;
      }
   }

   private String getStoreViewsPath() throws Exception
   {
         StringMaker stringBuffer = new StringMaker();

         stringBuffer.append(URLGLOBALS.getMainPath());
         stringBuffer.append(FREEBLISKET_PATH_GLOBALS.getInstance().XSLPATH);
         stringBuffer.append(this.newStoreFrontInterface.getName());

         return stringBuffer.toString();
   }

   public String validationInfo()
   {
      try
      {
         StringMaker stringBuffer = new StringMaker();
         
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
   
   public String view() throws Exception
   {
      return views.ValidationOnlyTempUtil.getInstance().view(this);
   }
   
}
