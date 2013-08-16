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
package views.business.context;

import abcs.logic.communication.log.LogUtil;
import abcs.data.tree.dom.document.DomDocumentHelper;
import abcs.logic.communication.log.LogFactory;

import allbinary.logic.visual.transform.ContextTransformer;

import allbinary.logic.visual.transform.data.TransformHttpRequestDocumentFactory;

import allbinary.logic.visual.transform.info.TransformInfoInterface;

import views.HttpComponentView;

public class HttpContextView extends HttpComponentView
{
   public HttpContextView(TransformInfoInterface transformInfoInterface) throws Exception
   {
      super(transformInfoInterface);
      
      if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEW))
      {
         LogUtil.put(LogFactory.getInstance("View Name: " + transformInfoInterface.getName(), this, "Constructor"));
      }

      this.setTransformDocumentInterface(
         TransformHttpRequestDocumentFactory.getInstance(
            this.getPageContext(),
            this.getWeblisketSession()));
   }
   
   public String view() throws Exception
   {
      try
      {
         this.toXmlDoc();
         String success = DomDocumentHelper.toString(this.getDoc());

         String result = new ContextTransformer(
            this.getTransformInfoInterface()).translate(success);

         return result;
      }
      catch(Exception e)
      {
         String error = "Failed to view ContextView";
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEWERROR))
         {
            LogUtil.put(LogFactory.getInstance(error, this, "view", e));
         }
         throw e;
      }
   }
}
