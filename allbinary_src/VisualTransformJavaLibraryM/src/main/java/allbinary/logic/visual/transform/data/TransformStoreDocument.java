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
package allbinary.logic.visual.transform.data;

import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.business.context.modules.storefront.StoreFrontViewFactory;
import allbinary.logic.communication.http.request.RequestParams;
import allbinary.logic.communication.http.request.session.WeblisketSession;
import allbinary.logic.control.search.SearchRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

public class TransformStoreDocument extends TransformHttpRequestDocument
{
   public TransformStoreDocument(
      PageContext pageContext, WeblisketSession weblisketSession) 
      throws Exception
   {
      super(pageContext, weblisketSession);
      try
      {
         //this.getBaseNode().appendChild(
           // UrlGlobalsViewFactory.getInstance().toXmlNode(this.getDoc()));

         String storeName = weblisketSession.getStoreName();
         /*
         if(StringValidationUtil.isEmpty(storeName))
         {
            storeName = pageContext.getRequest().getParameter(StoreFrontData.NAME);
            if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEW))
            {
               LogUtil.put("Using Request StoreName: " + storeName, this, "Constructor()");
            }
         }
         else
         {*/
            if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEW))
            {
               LogUtil.put(LogFactory.getInstance("Using Session StoreName: " + storeName, this, "Constructor()"));
            }
         //}

         this.getBaseNode().appendChild(
            StoreFrontViewFactory.getInstance(storeName).toXmlNode(this.getDoc()));
         
         this.getBaseNode().appendChild(
            new RequestParams((HttpServletRequest) 
               pageContext.getRequest()).toXmlNode(this.getDoc()));
      }
      catch(Exception e)
      {
         String error = "Failed to create view document";
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEWERROR))
         {
            LogUtil.put(LogFactory.getInstance(error, this, "Constructor()", e));
         }
         throw e;
      }
   }
   
   //, WeblisketSession weblisketSession
   public TransformStoreDocument(SearchRequest searchRequest) throws Exception
   {
      super(searchRequest);
      try
      {
         //this.getBaseNode().appendChild(
           // UrlGlobalsViewFactory.getInstance().toXmlNode(this.getDoc()));

         this.getBaseNode().appendChild(StoreFrontViewFactory.getInstance(
            searchRequest.getStoreFront().getName()).toXmlNode(this.getDoc()));

         this.getBaseNode().appendChild(
            searchRequest.getParams().getParamsNode(this.getDoc()));
      }
      catch(Exception e)
      {
         String error = "Failed to create view document";
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEWERROR))
         {
            LogUtil.put(LogFactory.getInstance(error, this, "Constructor()", e));
         }
         throw e;
      }
   }
}
