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
package org.allbinary.logic.visual.transform.data;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

import org.allbinary.business.context.modules.storefront.StoreFrontViewFactory;
import org.allbinary.logic.communication.http.request.RequestParams;
import org.allbinary.logic.communication.http.request.session.WeblisketSession;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.control.search.SearchRequest;

public class TransformStoreDocument extends TransformHttpRequestDocument
{
    protected final LogUtil logUtil = LogUtil.getInstance();

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
            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
            {
               logUtil.put("Using Request StoreName: " + storeName, this, this.commonStrings.CONSTRUCTOR);
            }
         }
         else
         {*/
            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
            {
               logUtil.put("Using Session StoreName: " + storeName, this, this.commonStrings.CONSTRUCTOR);
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
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEWERROR))
         {
            logUtil.put(commonStrings.EXCEPTION, this, this.commonStrings.CONSTRUCTOR, e);
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
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEWERROR))
         {
            logUtil.put(commonStrings.EXCEPTION, this, this.commonStrings.CONSTRUCTOR, e);
         }
         throw e;
      }
   }
}
