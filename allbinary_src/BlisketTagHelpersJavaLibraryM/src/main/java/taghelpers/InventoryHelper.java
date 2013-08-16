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

import abcs.logic.communication.log.LogFactory;
import java.util.HashMap;

import javax.servlet.jsp.PageContext;

import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpServletRequest;

import abcs.logic.communication.log.LogUtil;
import admin.taghelpers.TagHelperInterface;

import allbinary.business.user.address.StreetAddress;

import views.admin.inventory.listings.InventoryViewFactory;
import views.admin.inventory.listings.InventoryViewSearchInterface;

import allbinary.business.context.modules.storefront.StoreFrontData;
import allbinary.business.context.modules.storefront.StoreFrontInterface;
import allbinary.business.context.modules.storefront.StoreFrontFactory;

import allbinary.business.user.commerce.inventory.item.BasicItemData;

import allbinary.logic.communication.http.AcceptableResponseGenerator;
import allbinary.logic.communication.http.request.session.WeblisketSession;

import allbinary.logic.control.search.SearchRequest;
import allbinary.logic.control.search.SearchParams;

import allbinary.logic.visual.transform.info.TransformInfoData;

public class InventoryHelper implements TagHelperInterface
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
         
         String contentType = AcceptableResponseGenerator.get(this.request);
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

         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGS))
         {
            LogUtil.put(LogFactory.getInstance("Successfull Inventory View Summary",this,"viewSummary()"));
         }
         return result;
      }
      catch(Exception e)
      {
         String error = "Failed to View Summary";

         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance(error,this,"viewSummary()",e));
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
            
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGS))
         {
            LogUtil.put(LogFactory.getInstance("Successful Inventory Dynamic Search",this,"searchSingleDynamicPage()"));
         }
         return result;

         /*
         String fileName = inventoryDomNodeInterface.searchStatic();
         
         if(fileName==null || fileName.compareTo("")==0)
         {
            String result = inventoryDomNodeInterface.searchSingleDynamicPage();
            
            
            if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGS))
            {
               LogUtil.put(LogFactory.getInstance("Successful Inventory Dynamic Search",this,"search(String,HttpServletResponse)"));
            }
            return result;
         }
         else
         {            
            response.sendRedirect(fileName);            
            if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGS))
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
         
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance(error,this,"searchSingleDynamicPage()",e));
         }
         return error + "<br>" + "Exception: " + e + "<br>";
      }
   }
}
