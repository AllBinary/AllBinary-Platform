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
package taghelpers;

import admin.taghelpers.TagHelper;
import org.allbinary.logic.communication.log.LogFactory;
import java.util.HashMap;

import javax.servlet.jsp.PageContext;

import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpServletRequest;

import org.allbinary.logic.communication.log.LogUtil;

import org.allbinary.business.user.address.StreetAddress;

import views.admin.inventory.listings.InventoryViewFactory;
import views.admin.inventory.listings.InventoryViewSearchInterface;

import org.allbinary.business.context.modules.storefront.StoreFrontData;
import org.allbinary.business.context.modules.storefront.StoreFrontInterface;
import org.allbinary.business.context.modules.storefront.StoreFrontFactory;

import org.allbinary.business.user.commerce.inventory.item.BasicItemData;

import org.allbinary.logic.communication.http.AcceptableResponseGenerator;
import org.allbinary.logic.communication.http.request.session.WeblisketSession;

import org.allbinary.logic.control.search.SearchRequest;
import org.allbinary.logic.control.search.SearchParams;

import org.allbinary.logic.visual.transform.info.TransformInfoData;

public class InventoryHelper extends TagHelper
{
   private WeblisketSession weblisketSession;
   
   private HttpServletResponse response;
   private HttpServletRequest request;
       
   private StreetAddress streetAddress;
   
   private String id;
   //private String keywords;
   //private SearchParams searchParams;
   private SearchRequest searchRequest;

   public InventoryHelper(HashMap propertiesHashMap, PageContext pageContext) throws Exception
   {
      this.response = (HttpServletResponse) pageContext.getResponse();
      this.request = (HttpServletRequest) pageContext.getRequest();                  

      String storeName = (String) propertiesHashMap.get(StoreFrontData.getInstance().NAME);
      String xslFile = (String) propertiesHashMap.get(TransformInfoData.getInstance().TEMPLATEFILENAME);      
      
      if(storeName!=null)
      {
         StoreFrontInterface storeFrontInterface = StoreFrontFactory.getInstance(storeName);                  
         
         String contentType = AcceptableResponseGenerator.getInstance().get(this.request);
         SearchParams searchParams = new SearchParams(this.request);
         this.searchRequest = 
            new SearchRequest(storeFrontInterface, searchParams, xslFile, contentType, propertiesHashMap, pageContext);         
      }
      
      this.getFormData();
   }
    
   private void getFormData()
   {
      this.id = this.request.getParameter(BasicItemData.ID);
   }

   public String viewSummary()
   {
      try
      {         
         InventoryViewSearchInterface inventoryViewSearchInterface = 
            InventoryViewFactory.getInstance(searchRequest);

         String result = inventoryViewSearchInterface.getProduct(this.id);

         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGS))
         {
            LogUtil.put(LogFactory.getInstance("Successfull Inventory View Summary",this,"viewSummary()"));
         }
         return result;
      }
      catch(Exception e)
      {
         String error = "Failed to View Summary";

         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION,this,"viewSummary()",e));
         }
         return error + "<br>" + "Exception: " + e + "<br>";
      }
   }

   public String search()
   {
      try
      {
         String keywords = (String) this.searchRequest.getParams().get().get(BasicItemData.KEYWORDS);
         if(keywords == null || keywords.compareTo("") == 0)
         {
            return "Please enter a keyword in the search field above the Search button.";
         }

         InventoryViewSearchInterface inventoryViewSearchInterface = 
            InventoryViewFactory.getInstance(searchRequest);

         //if(inventoryDomNodeInterface==null) 
         String result = inventoryViewSearchInterface.searchSingleDynamicPage();
            
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGS))
         {
            LogUtil.put(LogFactory.getInstance("Successful Inventory Dynamic Search",this,"searchSingleDynamicPage()"));
         }
         return result;

         /*
         String fileName = inventoryDomNodeInterface.searchStatic();
         
         if(fileName==null || fileName.compareTo("")==0)
         {
            String result = inventoryDomNodeInterface.searchSingleDynamicPage();
            
            
            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGS))
            {
               LogUtil.put(LogFactory.getInstance("Successful Inventory Dynamic Search",this,"search(String,HttpServletResponse)"));
            }
            return result;
         }
         else
         {            
            response.sendRedirect(fileName);            
            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGS))
            {
               LogUtil.put(LogFactory.getInstance("Successful Inventory Static Search",this,"search(String,HttpServletResponse)"));
            }
            return null;
         }
  */       
      }
      catch(Exception e)
      {
         String error = "Failed to search Inventory by keyword";
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION,this,"searchSingleDynamicPage()",e));
         }
         return error + "<br>" + "Exception: " + e + "<br>";
      }
   }
}
